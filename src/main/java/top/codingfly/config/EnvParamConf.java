package top.codingfly.config;

import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class EnvParamConf {
    private static Logger logger = LoggerFactory.getLogger(EnvParamConf.class);

    public static boolean useEnvParam = false;
    public static Map<String, String> env = System.getenv();
    public static Map<String, String> javaProperties = new HashMap();
    public static Map<String, String> cmdParams = new HashMap();

    static {
        Properties properties = System.getProperties();
        Set<Object> keySet = properties.keySet();
        logger.info(JSON.toJSONString(properties, SerializerFeature.PrettyFormat));
        for (Object key:keySet) {
            if ((key instanceof String)==false) {
                continue;
            }
            if ("useEnvParam".equals(key)) {
                useEnvParam = true;
                continue;
            }
            Object value = properties.get(key);
            if (value instanceof String) {
                javaProperties.put((String)key, (String)value);
                if ("sun.java.command".equals(key)) {
                    extractCmdParams((String)value);
                }
            }
        }
    }

    public static void extractCmdParams(String commandStr) {
        if (commandStr==null || commandStr.trim().length()==0) {
            return;
        }
        String[] keyValues = commandStr.trim().split(" ");
        for (String keyValue:keyValues) {
            if (keyValue.startsWith("--")==false || keyValue.contains("=")==false) {
                continue;
            }
            keyValue = keyValue.substring(2);
            String[] arr = keyValue.split("=");
            if (arr.length==1) {
                continue;
            }
            cmdParams.put(arr[0], arr[1]);
        }
    }

    public static String getParam(String key) {
        String value = env.get(key);
        if (value!=null) {
            return value;
        }
        value = cmdParams.get(key);
        if (value!=null) {
            return value;
        }
        value = javaProperties.get(key);
        if (value!=null) {
            return value;
        }
        try {
            throw new RuntimeException(key+"参数不存在");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            logger.error(key+"参数不存在，直接中断程序");
            System.exit(0);
            return null;
        }
    }

    public static Integer getIntParam(String key) {
        String value = getParam(key);
        if (NumberUtil.isInteger(value)==false) {
            try {
                throw new RuntimeException(key+"不是整型参数");
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage(), e);
                logger.error(key+"不是整型参数，直接中断程序");
                System.exit(0);
                return null;
            }
        }
        return Integer.parseInt(value);
    }
}
