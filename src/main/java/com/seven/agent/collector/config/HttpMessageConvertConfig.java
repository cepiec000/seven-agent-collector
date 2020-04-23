package com.seven.agent.collector.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import java.nio.charset.Charset;

@Configuration
public class HttpMessageConvertConfig {
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverts(){
        FastJsonHttpMessageConverter fastConverter=new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig=new FastJsonConfig();
        SerializerFeature[] serializerFeatures=new SerializerFeature[]{

        };
        fastJsonConfig.setSerializerFeatures(serializerFeatures);
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        fastConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter=fastConverter;
        return new HttpMessageConverters(converter);
    }
}
