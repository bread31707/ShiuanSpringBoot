package com.wuw.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wuw.core.model.ActionBeginModel;
import com.wuw.core.model.ActionEndModel;
import com.wuw.core.model.ActionReqModel;
import com.wuw.utils.DateUtils;
import com.wuw.utils.JacksonUtils;
import com.wuw.utils.UuidUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Aspect
@Component
@Slf4j
public class ActionLogAspect {

    @Autowired
    HttpServletRequest httpServletRequest;

    String uuid = UuidUtils.generateUuid();
    JacksonUtils jacksonUtils = new JacksonUtils();

    @Pointcut("execution(* com.wuw.core.AbstractActionLogic.doAction(com.wuw.core.model.ActionReqModel))")
    public void doPoint() {}

//    @AfterThrowing(pointcut = "doPoint()", throwing = "e")
//    public void actionLogThrowing(JoinPoint joinPoint, Throwable e) {
//        writeExEndLog(httpServletRequest.getAttribute("uuid").toString(), uuid, e);
//    }

    @Around("doPoint() && args(reqModel)")
    public Object around(ProceedingJoinPoint joinPoint, ActionReqModel reqModel) throws Throwable{
        String svcUuid = httpServletRequest.getAttribute("uuid").toString();
        LocalDateTime startDateTime = DateUtils.getNowLocalDateTime();

        try {
            writeBeginLog(buildClientBeginModel(svcUuid, uuid, startDateTime, reqModel));
            Object process = joinPoint.proceed();
            writeEndLog(buildClientEndModel(svcUuid, uuid, startDateTime, process));
            return process;
        }catch(Throwable t) {
            writeExEndLog(httpServletRequest.getAttribute("uuid").toString(), uuid, t);
            throw t;
        }
    }

    private ActionBeginModel buildClientBeginModel(String svcUuid, String uuid, LocalDateTime startDateTime, ActionReqModel clientReqBody) throws JsonProcessingException {
        ActionBeginModel model = new ActionBeginModel();
        model.setSvcUuid(svcUuid);
        model.setUuid(uuid);
        model.setStartDateTime(DateUtils.localDateTimeFormat(startDateTime, DateUtils.DefaultDateTimePattern));
        model.setRequestBody(jacksonUtils.getObjectMapper().writeValueAsString(clientReqBody));
        return model;
    }

    private ActionEndModel buildClientEndModel(String svcUuid, String uuid, LocalDateTime startDateTime, Object clientResData) throws JsonProcessingException {
        LocalDateTime endDateTime = DateUtils.getNowLocalDateTime();
        ActionEndModel model = new ActionEndModel();
        model.setSvcUuid(svcUuid);
        model.setUuid(uuid);
        model.setEndDateTime(DateUtils.localDateTimeFormat(endDateTime, DateUtils.DefaultDateTimePattern));
        model.setResponseBody(jacksonUtils.getObjectMapper().writeValueAsString(clientResData));
        model.setRunTime(String.valueOf(DateUtils.between(ChronoUnit.MILLIS, startDateTime, endDateTime)));
        return model;
    }

    private void writeBeginLog(ActionBeginModel model) {
        ObjectNode objectNode = jacksonUtils.getObjectNode();
        objectNode.put("svcUuid", model.getSvcUuid());
        objectNode.put("uuid", model.getUuid());
        objectNode.put("startDateTime", model.getStartDateTime());
        jacksonUtils.readTree(model.getRequestBody()).ifPresent(data -> {
        objectNode.set("reqBody", data);
        });
        log.info("【ActionRequest】: " + objectNode);
    }

    private void writeEndLog(ActionEndModel model) {
        ObjectNode objectNode = jacksonUtils.getObjectNode();
        objectNode.put("svcUuid", model.getSvcUuid());
        objectNode.put("uuid", model.getUuid());
        objectNode.put("endDateTime", model.getEndDateTime());
        jacksonUtils.readTree(model.getResponseBody()).ifPresent(data -> {
            objectNode.set("resBody", data);
        });
        objectNode.put("runTime", model.getRunTime());
        log.info("【ActionResponse】: " + objectNode);
    }

    private void writeExEndLog(String svcUuid, String uuid, Throwable e) {
        ObjectNode objectNode = jacksonUtils.getObjectNode();
        objectNode.put("svcUuid", svcUuid);
        objectNode.put("uuid", uuid);
        objectNode.put("exception", e.getMessage());
        log.info("【ActionResponse】: " + objectNode);
    }

}
