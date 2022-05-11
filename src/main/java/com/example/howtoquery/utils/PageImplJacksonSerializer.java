package com.example.howtoquery.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

@JsonComponent
public class PageImplJacksonSerializer extends JsonSerializer<PageImpl<?>> {

    @Autowired
    private HttpServletRequest request;

    public PageImplJacksonSerializer(){
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    public void serialize(PageImpl page, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        var links = PaginationMetadata.generatePaginationLinks(page, request);

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("links", links);
        jsonGenerator.writeFieldName("records");
        // https://github.com/mefernandez/spring-jpa-lazy-projections
        serializerProvider.defaultSerializeValue(page.getContent(), jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}