package com.ruoyi.content.utils;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.ruoyi.content.exception.BusinessException;
import com.ruoyi.content.exception.ParameterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class JsonUtil {

    private final static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static ObjectMapper jacksonMapper = new ObjectMapper();

    /**
     * 把json字符串转换为JavaBean (1)普通对象转换：toJson(Student) (2)List转换：toJson(List)
     * (3)Map转换:toJson(Map) 我们发现不管什么类型，都可以直接传入这个方法
     * <p>
     * /** 将Object对象转换成json串，原始方法
     *
     * @param object Java对象
     * @return
     */
    public static String objectToJson(Object object) {
        try {
            if (jacksonMapper == null) {
                jacksonMapper = new ObjectMapper();
            }
            return jacksonMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将Object对象转换成json串，把null换成""
     *
     * @param object Java对象
     * @return
     */
    public static String objectToJackson(Object object) {
        ObjectMappingCustomer jacksonMapper = new ObjectMappingCustomer();
        try {
            return jacksonMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将Object对象转换成json串，自动去掉null和""的字段
     *
     * @param object
     * @return
     */
    public static String objectToJacksonNotNull(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        try {
            return jacksonMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把json字符串转换为JavaBean对象
     *
     * @param json   字符串
     * @param object Java对象类型
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T jackson2Object(String json, Class<?> object) {
        T t = null;
        if (jacksonMapper == null) {
            jacksonMapper = new ObjectMapper();
        }
        try {
            jacksonMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            t = (T) jacksonMapper.readValue(json, object);
        } catch (JsonParseException e) {
            logger.error("把json字符串转换为JavaBean对象异常", e);
            logger.info("传入JSON：" + json);
            throw new ParameterException("jackson转化对象失败");
        } catch (JsonMappingException e) {
            logger.error("把json字符串转换为JavaBean对象异常", e);
            logger.info("传入JSON：" + json);
            throw new ParameterException("jackson转化对象失败");
        } catch (IOException e) {
            logger.error("把json字符串转换为JavaBean对象异常", e);
            logger.info("传入JSON：" + json);
            throw new ParameterException("jackson转化对象失败");
        }
        return t;
    }

    /**
     * 把Json转成Map
     *
     * @param json JSON字符串
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Map jackson2Map(String json) {
        if (jacksonMapper == null) {
            jacksonMapper = new ObjectMapper();
        }
        try {
            jacksonMapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
            jacksonMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            return jacksonMapper.readValue(json, Map.class);
        } catch (JsonParseException e) {
            logger.error("把Json转成Map异常", e);
            logger.info("传入JSON：" + json);
            throw new ParameterException("把Json转成Map失败");
        } catch (JsonMappingException e) {
            logger.error("把Json转成Map异常", e);
            logger.info("传入JSON：" + json);
            throw new ParameterException("把Json转成Map失败");
        } catch (IOException e) {
            logger.error("把Json转成Map异常", e);
            logger.info("传入JSON：" + json);
            throw new ParameterException("把Json转成Map失败");
        }
    }

    /**
     * 转换集合对象
     *
     * @param json json数据
     * @param obj  java对象类型
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T JsonToList(String json, Class<?> obj) {
        if (jacksonMapper == null) {
            jacksonMapper = new ObjectMapper();
        }
        List<T> lst = null;
        try {
            jacksonMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            lst = (List<T>) jacksonMapper.readValue(json, getCollectionType(List.class, obj));
        } catch (JsonParseException e) {
            logger.error("转换集合对象异常", e);
            logger.info("传入JSON：" + json);
            throw new ParameterException("转换集合对象失败");
        } catch (JsonMappingException e) {
            logger.error("转换集合对象异常", e);
            logger.info("传入JSON：" + json);
            throw new ParameterException("转换集合对象失败");
        } catch (IOException e) {
            logger.error("转换集合对象异常", e);
            logger.info("传入JSON：" + json);
            throw new ParameterException("转换集合对象失败");
        }
        return (T) lst;
    }

    /**
     * 转换集合对象
     *
     * @param json
     * @param objectList 集合类型
     * @param obj        java对象类型
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T JsonToCollectionType(String json, Class<?> objectList, Class<?> obj) {
        if (jacksonMapper == null) {
            jacksonMapper = new ObjectMapper();
        }
        List<T> lst = null;
        try {
            jacksonMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            lst = (List<T>) jacksonMapper.readValue(json, getCollectionType(objectList, obj));
        } catch (JsonParseException e) {
            logger.error("转换集合对象异常", e);
            logger.info("传入JSON：" + json);
            throw new ParameterException("转换集合对象失败");
        } catch (JsonMappingException e) {
            logger.error("转换集合对象异常", e);
            logger.info("传入JSON：" + json);
            throw new ParameterException("转换集合对象失败");
        } catch (IOException e) {
            logger.error("转换集合对象异常", e);
            logger.info("传入JSON：" + json);
            throw new ParameterException("转换集合对象失败");
        }
        return (T) lst;
    }

    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        if (jacksonMapper == null) {
            jacksonMapper = new ObjectMapper();
        }
        return jacksonMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * 方法json节点
     *
     * @return
     * @throws Exception
     */
    public static JsonNode createJsonNode() throws Exception {
        if (jacksonMapper == null) {
            jacksonMapper = new ObjectMapper();
        }
        return jacksonMapper.createObjectNode();
    }

    /**
     * 方法json节点
     *
     * @param data
     * @return
     * @throws IOException
     * @throws @throws     Exception
     */
    public static JsonNode getJsonNode(String data) throws IOException {
        if (jacksonMapper == null) {
            jacksonMapper = new ObjectMapper();
        }
        return jacksonMapper.readTree(data);
    }

    /**
     * 对象转换位Json
     *
     * @param object
     * @param properties
     * @return
     * @throws BusinessException
     */
    public static String objectToJackson(Object object, Set<String> properties) throws BusinessException {
        ObjectMapper mapper = new ObjectMapper();
        FilterProvider filters = new SimpleFilterProvider().addFilter("headFilter",
                SimpleBeanPropertyFilter.serializeAllExcept(properties));
        mapper.setFilterProvider(filters);
        mapper.addMixIn(object.getClass(), HeadFilterMixIn.class);
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            throw new BusinessException("jackson转化异常");
        }
    }

    public static String jsonNode2String(Map<String, String> map) throws IOException {
        if (jacksonMapper == null) {
            jacksonMapper = new ObjectMapper();
        }
        JsonNodeFactory factory = new JsonNodeFactory(false);
        ObjectNode objectNode = factory.objectNode();
        for (Entry<String, String> entry : map.entrySet()) {
            objectNode.put(entry.getKey(), entry.getValue());
        }
        return jacksonMapper.writeValueAsString(objectNode);
    }

    @JsonFilter("headFilter")
    private static interface HeadFilterMixIn {
    }
}
