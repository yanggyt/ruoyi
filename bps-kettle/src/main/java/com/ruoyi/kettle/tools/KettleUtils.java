package com.ruoyi.kettle.tools;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

public class KettleUtils {

    /**
     * 执行ktr文件
     *
     * @param filename
     * @param params
     * @return
     */
    public static void runKtr(String filename, Map<String, String> params, String dirPath) {
        try {
            KettleEnvironment.init();
            TransMeta tm = new TransMeta(dirPath + File.separator + filename);
            Trans trans = new Trans(tm);
            if (params != null) {
                Iterator<Map.Entry<String, String>> entries = params.entrySet().iterator();
                while (entries.hasNext()) {
                    Map.Entry<String, String> entry = entries.next();
                    trans.setParameterValue(entry.getKey(), entry.getValue());
                }
            }
            trans.execute(null);
            trans.waitUntilFinished();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行kjb文件
     *
     * @param filename
     * @param params
     * @return
     */
    public static void runKjb(String filename, Map<String, String> params, String dirPath) {
        try {
            KettleEnvironment.init();
            JobMeta jm = new JobMeta(dirPath + File.separator + filename, null);
            Job job = new Job(null, jm);
            if (params != null) {
                Iterator<Map.Entry<String, String>> entries = params.entrySet().iterator();
                while (entries.hasNext()) {
                    Map.Entry<String, String> entry = entries.next();
                    job.setVariable(entry.getKey(), entry.getValue());
                }
            }
            job.start();
            job.waitUntilFinished();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ClassPathResource classPathResource = new ClassPathResource("kettle");
        System.out.println("classPathResource:" + classPathResource.getFile().getPath());
        runKtr("D:\\etl\\kone.ktr", null, classPathResource.getFile().getPath());
    }
}