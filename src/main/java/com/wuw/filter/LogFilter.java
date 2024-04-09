package com.wuw.filter;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wuw.core.model.SvcBeginLogModel;
import com.wuw.core.model.SvcEndLogModel;
import com.wuw.filter.wrapper.SvcRequestWrapper;
import com.wuw.filter.wrapper.SvcResponseWrapper;
import com.wuw.utils.DateUtils;
import com.wuw.utils.JacksonUtils;
import com.wuw.utils.UuidUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
public class LogFilter extends OncePerRequestFilter {

    private JacksonUtils jacksonUtils = new JacksonUtils();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        SvcRequestWrapper svcRequestWrapper = new SvcRequestWrapper(request);
        SvcResponseWrapper svcResponseWrapper = new SvcResponseWrapper(response);

        String uuid = UuidUtils.generateUuid();
        svcRequestWrapper.setAttribute("uuid", uuid);
        LocalDateTime startDateTime = DateUtils.getNowLocalDateTime();
        String remote = request.getRemoteAddr() + request.getRemotePort();
        String requestURI = request.getRequestURI();
        String url = request.getScheme()+"://" + request.getLocalAddr() + ":" + request.getLocalPort() + request.getRequestURI();

        writeBeginLog(buildSvcBeginModel(uuid, DateUtils.localDateTimeFormat(startDateTime, DateUtils.DefaultDateTimePattern), request.getMethod(), svcRequestWrapper.getBody(), remote, url));

        chain.doFilter(svcRequestWrapper, svcResponseWrapper);

        LocalDateTime endDateTime = DateUtils.getNowLocalDateTime();
        String runTime = String.valueOf(DateUtils.between(ChronoUnit.MILLIS, startDateTime, endDateTime));

        writeEndLog(buildSvcEndModel(uuid, DateUtils.localDateTimeFormat(endDateTime, DateUtils.DefaultDateTimePattern), String.valueOf(response.getStatus()), svcResponseWrapper.getBody4String(), runTime));

    }

    private SvcBeginLogModel buildSvcBeginModel(String uuid, String startDateTime, String method, String reqbody, String remoto, String url) {
        SvcBeginLogModel model = new SvcBeginLogModel();
        model.setUuid(uuid);
        model.setStartDateTime(startDateTime);
        model.setMethod(method);
        model.setReqBody(reqbody);
        model.setRemoto(remoto);
        model.setUrl(url);
        return model;
    }

    private SvcEndLogModel buildSvcEndModel(String uuid, String endDateTime, String resCode, String resBody, String runTime) {
        SvcEndLogModel model = new SvcEndLogModel();
        model.setUuid(uuid);
        model.setEndDateTime(endDateTime);
        model.setResCode(resCode);
        model.setResBody(resBody);
        model.setRunTime(runTime);
        return model;
    }

    private void writeBeginLog(SvcBeginLogModel model) {
        ObjectNode objectNode = jacksonUtils.getObjectNode();
        objectNode.put("uuid", model.getUuid());
        objectNode.put("startDateTime", model.getStartDateTime());
        objectNode.put("method", model.getMethod());
        jacksonUtils.readTree(model.getReqBody()).ifPresent(data ->{
            objectNode.set("reqBody", data);
        });
        objectNode.put("remoto", model.getRemoto());
        objectNode.put("url", model.getUrl());
        log.info("【SvcRequest】：" + objectNode);
    }

    private void writeEndLog(SvcEndLogModel model) {
        ObjectNode objectNode = jacksonUtils.getObjectNode();
        objectNode.put("uuid", model.getUuid());
        objectNode.put("endDateTime", model.getEndDateTime());
        objectNode.put("resCode", model.getResCode());
        jacksonUtils.readTree(model.getResBody()).ifPresent(data -> {
            objectNode.set("resBody", data);
        });
        objectNode.put("runTIme", model.getRunTime());
        log.info("【SvcResponse】：" + objectNode);
    }

}

//        ContentCachingRequestWrapper contentCachingRequestWrapper = new ContentCachingRequestWrapper(request);
//        ContentCachingResponseWrapper contentCachingResponseWrapper = new ContentCachingResponseWrapper(response);
//
//        String uuid = UuidUtils.generateUuid();
//        contentCachingRequestWrapper.setAttribute("uuid", uuid);
//
//        LocalDateTime startDateTime = DateUtils.getNowLocalDateTime();
//        log.info("【 REQUEST 】: SvcUuid= {}; Method= {}; Uri= {};",
//                uuid, request.getMethod(), request.getRequestURI());
//
//        chain.doFilter(contentCachingRequestWrapper, contentCachingResponseWrapper);
//
//        LocalDateTime endDateTime = DateUtils.getNowLocalDateTime();
//        String timeTaken = String.valueOf(DateUtils.between(ChronoUnit.MILLIS, startDateTime, endDateTime));
//        String requestBody = getStringValue(contentCachingRequestWrapper.getContentAsByteArray(), request.getCharacterEncoding());
//        String responseBody = getStringValue(contentCachingResponseWrapper.getContentAsByteArray(), response.getCharacterEncoding());
//        log.info("【 RESPONSE 】: SvcUuid= {}; StatusCode= {}; RequestBody={}; ResponseBody= {}; TimeTaken= {}",
//                uuid, response.getStatus(), requestBody, responseBody, timeTaken);
//
//        contentCachingResponseWrapper.copyBodyToResponse();
//
//    }
//
//    // 後續研究, chatset 的部分
////    private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
////        try {
////            // 將 byte 陣列轉換成字串
////            String rawString;
////            if (!characterEncoding.equals(StandardCharsets.UTF_8)) {
////                rawString = new String(contentAsByteArray, StandardCharsets.UTF_8);
////            } else {
////                rawString = new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
////            }
////        }
////    }
//    private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
//        // 將 byte 陣列轉換成字串
//        String rawString;
//        rawString = new String(contentAsByteArray, StandardCharsets.UTF_8);
//        // 在字串內進行換行和 tab 的過濾
//        return rawString.replaceAll("\\s", "");
//    }
// }