package com.example.howtoquery.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
        jsonGenerator.writeObjectField("records", page.getContent());
        jsonGenerator.writeObjectField("links", links);
        jsonGenerator.writeEndObject();
    }
}