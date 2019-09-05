package com.ruoyi.common.utils.checkImgPath;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author solo
 * @date 2019/09/05
 */
public class CheckImgPath {
    public static void main(String[] args) {
        String imgPath="/public/image/111.PNG1";
        String format="/public/image";
        System.out.println("结果："+checkImgPath(imgPath,format));
    }

    /**
     *
     * @param imgPath  上传图片路径
     * @param format   格式
     * @return
     */
    public static boolean checkImgPath(String imgPath,String format){
        int length = format.length();
        String checkImg =  imgPath.substring(0,length);
        //验证开头
        if (!format.equals(checkImg)){
            return false;
        }
        //验证后缀
        if(
                !(imgPath.endsWith(".jpg") ||
                        imgPath.endsWith(".JPG") ||
                        imgPath.endsWith(".png") ||
                        imgPath.endsWith(".PNG") ||
                        imgPath.endsWith(".gif") ||
                        imgPath.endsWith(".GIF"))
        ){
            return false;
        }
        return true;
    }

    /**
     * 判断是否是真图片
     * @param file
     * @return
     */
    public static boolean isImageFile(File file) {
        ImageInputStream iis = null;
        try {
            iis = ImageIO.createImageInputStream(file); // resFile为需被
            Iterator iter = ImageIO.getImageReaders(iis);
            if (!iter.hasNext()) {// 文件不是图片
                return false;
            }
            BufferedImage bi = ImageIO.read(file);
            if(bi == null){
                return false;
            }
            return true;
        } catch (IOException e) {
            return false;
        }finally {
            try {
                if (iis!=null){
                    iis.close();
                }
            } catch (IOException e) {

            }
        }
    }

    public static boolean isICON(File file) {
        FileInputStream iis = null;
        try {
            iis = new FileInputStream(file);
            byte[] bufHeaders = readInputStreamAt(iis,0,8);
            byte[] markBuf = {0, 0, 1, 0, 1, 0, 32, 32};
            return compare(bufHeaders, markBuf);
        } catch (IOException e) {
            return false;
        }finally {
            try {
                if (iis!=null){
                    iis.close();
                }
            } catch (IOException e) {

            }
        }
    }


    /**
     * 标示一致性比较
     * @param buf  待检测标示
     * @param markBuf 标识符字节数组
     * @return 返回false标示标示不匹配
     */
    private static boolean compare(byte[] buf, byte[] markBuf) {
        for (int i = 0; i < markBuf.length; i++) {
            byte b = markBuf[i];
            byte a = buf[i];

            if(a!=b){
                return false;
            }
        }
        return true;
    }
    /**
     *
     * @param fis 输入流对象
     * @param skiplength 跳过位置长度
     * @param length 要读取的长度
     * @return 字节数组
     * @throws IOException
     */
    private static byte[] readInputStreamAt(FileInputStream fis, long skiplength, int length) throws IOException
    {
        byte[] buf = new byte[length];
        fis.skip(skiplength);  //
        int read = fis.read(buf,0,length);
        return buf;
    }
}
