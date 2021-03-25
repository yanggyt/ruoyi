package com.ruoyi.content.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Jackson处理类，处理null字符串
 *
 * @author zhanghe
 */
public class ObjectMappingCustomer extends ObjectMapper {

    private static final long serialVersionUID = -1066355039918898238L;

    public ObjectMappingCustomer() {
        super();
        // 空值处理为空串
        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator jg, SerializerProvider sp)
                    throws IOException, JsonProcessingException {
                jg.writeString("");

            }
        });
    }

}