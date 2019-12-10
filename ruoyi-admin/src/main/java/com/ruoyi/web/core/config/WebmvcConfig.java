package com.ruoyi.web.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebmvcConfig implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for ( HttpMessageConverter<?> converter : converters ) {
            if ( converter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter jacksonConverter = (MappingJackson2HttpMessageConverter) converter;
                ObjectMapper objectMapper = jacksonConverter.getObjectMapper();

                //--- register hibernateModule in MappingJackson2HttpMessageConverter.objectMapper
                Hibernate5Module hibernate5Module = new Hibernate5Module();
                hibernate5Module.disable(Hibernate5Module.Feature.FORCE_LAZY_LOADING);
                hibernate5Module.enable(Hibernate5Module.Feature.WRITE_MISSING_ENTITIES_AS_NULL);
                hibernate5Module.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
                objectMapper.registerModule(hibernate5Module);

                //--- other configurations
                jacksonConverter.setPrettyPrint( true );
                objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            }
        }
    }
}
