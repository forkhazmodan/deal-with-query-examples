package com.example.howtoquery.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class JacksonConfig {

//  @Bean
//  public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper) {
//    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//
//    MappingJackson2HttpMessageConverter converter =
//        new MappingJackson2HttpMessageConverter(objectMapper);
//    return converter;
//  }

  /**
   * The problem is:
   * com.fasterxml.jackson.databind.JsonMappingException:
   * No serializer found for class dtos.MyDtoNoAccessors
   * and no properties discovered to create BeanSerializer
   * (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) )
   * <p>
   * Possible solutions are:
   * https://stackoverflow.com/questions/28862483/spring-and-jackson-how-to-disable-fail-on-empty-beans-through-responsebody
   * https://www.baeldung.com/jackson-jsonmappingexception
   * https://www.baeldung.com/spring-boot-customize-jackson-objectmapper
   */
//  @Bean
//  public ObjectMapper objectMapper(ObjectMapper objectMapper) {
////    ObjectMapper mapper = new ObjectMapper();
//    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//    return objectMapper;
//  }
}
