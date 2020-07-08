package com.ruoyi.minio;

import com.ruoyi.common.core.domain.AjaxResult;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.commons.collections4.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.DoubleStream;

@Controller
@RequestMapping("/minio")
public class MimioController {

    @Autowired
    MinioClient minioClient;

    @Value("${minio.bucketName}")
    private String bucketName;

    @Value("${minio.startfile}")
    private String startfile;


    @RequestMapping("/index")
    public String index(String path,String openfile,ModelMap mmap) throws IOException, InvalidResponseException,
            InvalidKeyException, NoSuchAlgorithmException, InternalException, XmlParserException,
            InvalidBucketNameException, InsufficientDataException, ErrorResponseException {

        mmap.put("dirlist", getDirList(path));

        if(openfile==null)
            mmap.put("openfile",startfile);
        else
            mmap.put("openfile",openfile);

        return "minio/index";

    }


    @RequestMapping("/getdirlist")
    @ResponseBody
    public ArrayList<Map<String,Object>> getDirList(String dir) throws IOException, InvalidResponseException,
            InvalidKeyException, NoSuchAlgorithmException, ErrorResponseException,
            XmlParserException, InvalidBucketNameException, InsufficientDataException,
            InternalException {

        return listObjects(dir);

    }

    @RequestMapping("/save")
    @ResponseBody
    public Object save(String filepath,String content) throws IOException, InvalidKeyException,
            InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException,
            InternalException, XmlParserException, InvalidBucketNameException, ErrorResponseException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8.name()));
        minioClient.putObject(bucketName,filepath,byteArrayInputStream,new PutObjectOptions(byteArrayInputStream.available(), -1));

        return AjaxResult.success("保存成功");
    }

    @RequestMapping("/getfile")
    @ResponseBody
    public Object getFile(String filepath) throws IOException, InvalidResponseException,
            InvalidKeyException, NoSuchAlgorithmException, ErrorResponseException,
            XmlParserException, InvalidBucketNameException, InsufficientDataException,
            InternalException {

        InputStream inputStream = fileIo(filepath);
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        String str = result.toString(StandardCharsets.UTF_8.name());
        return AjaxResult.success("success",str);

    }

    public InputStream fileIo(String objname) throws IOException,
            InvalidKeyException, InvalidResponseException,
            InsufficientDataException, NoSuchAlgorithmException,
            InternalException, XmlParserException, InvalidBucketNameException,
            ErrorResponseException {

        InputStream object = minioClient.getObject(bucketName, objname);

        return object;
    }

    //获取指定文件列表
    public ArrayList<Map<String,Object>> listObjects(String prefix) throws IOException, InvalidKeyException,
            InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException,
            InternalException, XmlParserException, InvalidBucketNameException,
            ErrorResponseException {

        Iterable<Result<Item>> results = minioClient.listObjects(bucketName, prefix, false);
        ArrayList<Map<String,Object>> items = new ArrayList<>();

        for(Result<Item> i:results){
            Item item = i.get();
            HashMap<String, Object> data = new HashMap<>();
            data.put("isDir",item.isDir());
            data.put("objname", item.objectName());
            String[] split = item.objectName().split("/");
            data.put("name",split[split.length-1]);
            data.put("lastModified",!item.isDir()?item.lastModified():null);
            data.put("size",item.size());
            items.add(data);
        }
        Collections.reverse(items);
        return items;

    }

}
