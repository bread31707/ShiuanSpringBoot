package com.wuw.filter;

import com.wuw.core.model.SvcInfo;
import com.wuw.utils.DateUtils;
import com.wuw.utils.JacksonUtils;
import com.wuw.utils.UuidUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

public class LogFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        ContentCachingRequestWrapper contentCachingRequestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper contentCachingResponseWrapper = new ContentCachingResponseWrapper(response);

        String uuid = UuidUtils.generateUuid();
        contentCachingRequestWrapper.setAttribute("uuid", uuid);

        LocalDateTime startDateTime = DateUtils.getNowLocalDateTime();
        LOGGER.info("【 REQUEST 】: SvcUuid= {}; Method= {}; Uri= {};",
                uuid, request.getMethod(), request.getRequestURI());

        chain.doFilter(contentCachingRequestWrapper, contentCachingResponseWrapper);

        LocalDateTime endDateTime = DateUtils.getNowLocalDateTime();
        String timeTaken = String.valueOf(DateUtils.between(ChronoUnit.MILLIS, startDateTime, endDateTime));
        String requestBody = getStringValue(contentCachingRequestWrapper.getContentAsByteArray(), request.getCharacterEncoding());
        String responseBody = getStringValue(contentCachingResponseWrapper.getContentAsByteArray(), response.getCharacterEncoding());
        LOGGER.info("【 RESPONSE 】: SvcUuid= {}; StatusCode= {}; RequestBody={}; ResponseBody= {}; TimeTaken= {}",
                uuid, response.getStatus(), requestBody, responseBody, timeTaken);

        contentCachingResponseWrapper.copyBodyToResponse();

    }

    // 後續研究, chatset 的部分
//    private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
//        try {
//            // 將 byte 陣列轉換成字串
//            String rawString;
//            if (!characterEncoding.equals(StandardCharsets.UTF_8)) {
//                rawString = new String(contentAsByteArray, StandardCharsets.UTF_8);
//            } else {
//                rawString = new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
//            }
//        }
//    }
    private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
        // 將 byte 陣列轉換成字串
        String rawString;
        rawString = new String(contentAsByteArray, StandardCharsets.UTF_8);
        // 在字串內進行換行和 tab 的過濾
        return rawString.replaceAll("\\s", "");
    }

 }