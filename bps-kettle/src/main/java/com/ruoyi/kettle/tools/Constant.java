package com.ruoyi.kettle.tools;


import org.apache.commons.lang.StringUtils;
import org.pentaho.di.core.Const;
import org.pentaho.di.core.logging.LogLevel;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Constant extends Const {
    public static final String VERSION = "7.1.0.0-12";
    public static final String DEFAULT_ENCODING = "UTF-8";
    public static final String DEFAULT_TIMEZONE = "GMT+8";
    public static final String UKETTLE = "resource/xtl.properties";
    public static final String VARIABLE_JOB_ID = "GLOBAL_JOB_ID";
    public static final String VARIABLE_TRANS_ID = "GLOBAL_TRANS_ID";
    public static final String VARIABLE_JOB_MONITOR_ID = "GLOBAL_JOB_MONITOR_ID";
    public static final String VARIABLE_TRANS_MONITOR_ID = "GLOBAL_TRANS_MONITOR_ID";
    public static final String DEFAULT_REPO_ID = "1326379690046259200";
    public static final String TYPE_JOB = "job";
    public static final String TYPE_TRANS = "transformation";
    public static final String JOB_FILE_TYPE = "file";
    public static final String JOB_REPO_TYPE = "db";
    public static final String TRANS_FILE_TYPE = "file";
    public static final String TRANS_REPO_TYPE = "db";
    public static final String TYPE_JOB_SUFFIX = ".kjb";
    public static final String TYPE_TRANS_SUFFIX = ".ktr";
    public static final String STARTS_WITH_USD = "$";
    public static final String STARTS_WITH_PARAM = "-param:";
    public static final String SPLIT_PARAM = "-param:";
    public static final String SPLIT_EQUAL = "=";
    public static final String SPLIT_USD = "$";
    public static final String KETTLE_REPO = "repo";
    public static final String JOB_PREFIX = "JOB";
    public static final String JOB_GROUP_PREFIX = "JOB_GROUP";
    public static final String TRIGGER_PREFIX = "TRIGGER";
    public static final String TRIGGER_GROUP_PREFIX = "TRIGGER_GROUP";
    public static final String QUARTZ_SEPARATE = "@";
    public static final String RUNSTATUS_SEPARATE = "-";
    public static String KETTLE_HOME;
    public static String KETTLE_PLUGIN;
    public static String KETTLE_SCRIPT;
    public static LogLevel KETTLE_LOGLEVEL;
    public static Properties props;

    public Constant() {
    }

    public static String get(String key) {
        return props.getProperty(key);
    }

    public static void set(Properties p) {
        props = p;
    }

    public static Properties readProperties() {
        Properties p = new Properties();

        try {
            p.load(new FileInputStream(Constant.class.getResource("/").getPath().replace("%20", " ") + "resource/xtl.properties"));
        } catch (Exception var2) {
            var2.printStackTrace();
        }

        return p;
    }

    public static LogLevel logger(int level) {
        LogLevel logLevel = null;
        if ("3".equals(level + "")) {
            logLevel = LogLevel.BASIC;
        } else if ("4".equals(level + "")) {
            logLevel = LogLevel.DETAILED;
        } else if ("1".equals(level + "")) {
            logLevel = LogLevel.ERROR;
        } else if ("5".equals(level + "")) {
            logLevel = LogLevel.DEBUG;
        } else if ("2".equals(level + "")) {
            logLevel = LogLevel.MINIMAL;
        } else if ("6".equals(level + "")) {
            logLevel = LogLevel.ROWLEVEL;
        } else if ("0".endsWith(level + "")) {
            logLevel = LogLevel.NOTHING;
        } else {
            logLevel = KETTLE_LOGLEVEL;
        }

        return logLevel;
    }

    public static LogLevel logger(String code) {
        LogLevel logLevel = null;
        if ("Basic".equalsIgnoreCase(code)) {
            logLevel = LogLevel.BASIC;
        } else if ("Detail".equalsIgnoreCase(code)) {
            logLevel = LogLevel.DETAILED;
        } else if ("Error".equalsIgnoreCase(code)) {
            logLevel = LogLevel.ERROR;
        } else if ("Debug".equalsIgnoreCase(code)) {
            logLevel = LogLevel.DEBUG;
        } else if ("Minimal".equalsIgnoreCase(code)) {
            logLevel = LogLevel.MINIMAL;
        } else if ("Rowlevel".equalsIgnoreCase(code)) {
            logLevel = LogLevel.ROWLEVEL;
        } else if ("Nothing".equalsIgnoreCase(code)) {
            logLevel = LogLevel.NOTHING;
        }

        return logLevel;
    }

    private static String uKettle() {
        String classPath = Constant.class.getResource("/").getPath().replace("%20", " ");
        String iQuartz = "";
        String index = "WEB-INF";
        if (classPath.indexOf("target") > 0) {
            index = "target";
        }

        if ("\\".equals(FILE_SEPARATOR)) {
            iQuartz = classPath.substring(1, classPath.indexOf(index));
            iQuartz = iQuartz.replace("/", "\\");
        }

        if ("/".equals(FILE_SEPARATOR)) {
            iQuartz = classPath.substring(0, classPath.indexOf(index));
            iQuartz = iQuartz.replace("\\", "/");
        }

        return iQuartz;
    }

    public static Map<String, String> getQuartzBasic(String name, String path) {
        Map<String, String> quartzBasic = new HashMap<String, String>();
        StringBuilder jobName = new StringBuilder();
        jobName.append("JOB").append("@").append(name).append("@").append(path);
        StringBuilder jobGroupName = new StringBuilder();
        jobGroupName.append("JOB_GROUP").append("@").append("@").append(name).append("@").append(path);
        String triggerName = StringUtils.replace(jobName.toString(), "JOB", "TRIGGER");
        String triggerGroupName = StringUtils.replace(jobGroupName.toString(), "JOB_GROUP", "TRIGGER_GROUP");
        quartzBasic.put("jobName", jobName.toString());
        quartzBasic.put("jobGroupName", jobGroupName.toString());
        quartzBasic.put("triggerName", triggerName);
        quartzBasic.put("triggerGroupName", triggerGroupName);
        return quartzBasic;
    }
}
