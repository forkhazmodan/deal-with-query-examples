package com.example.howtoquery.utils.resolver;

import com.example.howtoquery.contracts.Patchable;
import com.example.howtoquery.service.ProgrammaticallyValidatingService;
import com.example.howtoquery.utils.patch.parsed.PatchContainer;
import com.example.howtoquery.utils.patch.parser.JsonPatchParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;

@Validated
public class PatchRequestArgumentResolver implements HandlerMethodArgumentResolver {

  private ProgrammaticallyValidatingService programmaticallyValidatingService;

  public PatchRequestArgumentResolver(ProgrammaticallyValidatingService programmaticallyValidatingService) {
    this.programmaticallyValidatingService = programmaticallyValidatingService;
  }

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    try {
      var mightListClass = Class.forName(parameter.getParameterType().getName());

      if (!Collection.class.isAssignableFrom(mightListClass)) {
        return false;
      }

      ParameterizedType genericParameterType = (ParameterizedType) parameter.getGenericParameterType();
      ParameterizedType mightPatchContainerType = (ParameterizedType) genericParameterType.getActualTypeArguments()[0];
      Class<?> mightPatchContainerClass = Class.forName(mightPatchContainerType.getRawType().getTypeName());

      if (!PatchContainer.class.isAssignableFrom(mightPatchContainerClass)) {
        return false;
      }

//      ParameterizedType mightPatchableType = (ParameterizedType) mightPatchContainerType.getActualTypeArguments()[0];
//      Class<?> mightPatchableClass = Class.forName(mightPatchableType.getRawType().getTypeName());
//
//      if (!Patchable.class.isAssignableFrom(mightPatchableClass)) {
//        return false;
//      }

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    return true;
  }

  @Override
  public Object resolveArgument(MethodParameter parameter,
                                ModelAndViewContainer mavContainer,
                                NativeWebRequest webRequest,
                                WebDataBinderFactory binderFactory) throws Exception {

    HttpServletRequest request = (HttpServletRequest) (webRequest.getNativeRequest());
    String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
    ObjectMapper mapper = new ObjectMapper();
    JsonNode actualObj = mapper.readTree(body);

    programmaticallyValidatingService.validateJsonNode(actualObj);

    Map<String, PatchContainer<Patchable>> stringPatchContainerMap = JsonPatchParser.parsePatchRequest(actualObj);
    return new ArrayList<>(stringPatchContainerMap.values());
  }
}
