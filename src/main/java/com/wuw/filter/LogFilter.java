package com.wuw.filter;

import com.wuw.core.model.SvcInfo;
import com.wuw.enums.ResultFields;
import com.wuw.handler.AppException;
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
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class LogFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {


        ContentCachingRequestWrapper contentCachingRequestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper contentCachingResponseWrapper = new ContentCachingResponseWrapper(response);

        long startTime = System.currentTimeMillis();

        chain.doFilter(contentCachingRequestWrapper, contentCachingResponseWrapper);

        long timeTaken = System.currentTimeMillis() - startTime;

        String requestBody = getStringValue(contentCachingRequestWrapper.getContentAsByteArray(), request.getCharacterEncoding());
        String responseBody = getStringValue(contentCachingResponseWrapper.getContentAsByteArray(), response.getCharacterEncoding());

        LOGGER.info("Filter Logs : METHOD= {}; REQUSETURI = {}; REQUSETBODY = {}; RESPONSECODE = {}; RESPONSEBODY = {}; TIMETAKEN = {}",
               request.getMethod(), request.getRequestURI(), requestBody, response.getStatus(), responseBody, timeTaken);

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
//
//            // 在字串內進行換行和 tab 的過濾
//            return rawString.replaceAll("\\s", "");
//        } catch (UnsupportedEncodingException e) {
//            throw new AppException(ResultFields.FAIL_ENCODING);
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
