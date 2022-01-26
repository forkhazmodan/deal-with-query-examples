package com.example.howtoquery.utils;

import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.*;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
// https://question-it.com/questions/4463502/spring-data-jpa-limit-pagesize-kak-ustanovit-maxsize
public class MyPageableHandlerMethodArgumentResolver extends PageableHandlerMethodArgumentResolver  {

    private static final Integer LIMIT_DEFAULT = 4;
    private static final Integer OFFSET_DEFAULT = 0;
    private static final SortHandlerMethodArgumentResolver DEFAULT_SORT_RESOLVER = new SortHandlerMethodArgumentResolver();
    private SortArgumentResolver sortResolver;

    public MyPageableHandlerMethodArgumentResolver() {
        this((SortArgumentResolver)null);
    }

    public MyPageableHandlerMethodArgumentResolver(SortHandlerMethodArgumentResolver sortResolver) {
        this((SortArgumentResolver)sortResolver);
    }

    public MyPageableHandlerMethodArgumentResolver(@Nullable SortArgumentResolver sortResolver) {
        this.sortResolver = (SortArgumentResolver)(sortResolver == null ? DEFAULT_SORT_RESOLVER : sortResolver);
    }

    public Pageable resolveArgument(MethodParameter methodParameter,
                                    @Nullable ModelAndViewContainer mavContainer,
                                    NativeWebRequest webRequest,
                                    @Nullable WebDataBinderFactory binderFactory) {
        String limit = webRequest.getParameter("limit");
        String offset = webRequest.getParameter("offset");
        String pageSize = limit;
        String page = getPage(limit, offset);
        Sort sort = this.sortResolver.resolveArgument(methodParameter, mavContainer, webRequest, binderFactory);
        Pageable pageable = this.getPageable(methodParameter, page, pageSize);
        return (Pageable)(sort.isSorted() ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort) : pageable);
    }

    private String getPage(String limit, String offset) {

        Integer tempLimit = limit != null && !"".equals(limit) && Integer.parseInt(limit) > 0
                ? Integer.parseInt(limit)
                : LIMIT_DEFAULT;

        Integer tempOffset = offset != null && !"".equals(offset) && Integer.parseInt(offset) >= 0
                ? Integer.parseInt(offset)
                : OFFSET_DEFAULT;

        Integer calculatedPage = tempOffset / tempLimit;

        return String.valueOf(calculatedPage);
    }
}
