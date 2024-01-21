package com.wuw.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wuw.core.AbstractSvcLogic;
import com.wuw.enums.ResultFields;
import com.wuw.service.request.ActionTestReq;
import com.wuw.service.response.ActionTestRes;
import com.wuw.utils.ClientUtils;
import com.wuw.vo.Result;
import okhttp3.Request;
import org.springframework.stereotype.Service;

@Service
public class ActionTest extends AbstractSvcLogic<ActionTestReq, ActionTestRes> {
    @Override
    public Result<ActionTestRes> doSvc(ActionTestReq reqModel, ActionTestRes resModel){

        String url = "http://localhost:8081/SupPost";
        String json = "{}";
        String test = "";
        ClientUtils clientUtilsTest = new ClientUtils();
        String actionResponse = clientUtilsTest.sendPostRequest(url, json, new Request.Builder());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ActionTestRes actionTestRes = objectMapper.readValue(actionResponse, ActionTestRes.class);
            resModel.setAemInfoList(actionTestRes.getAemInfoList());

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return new Result<>(ResultFields.SUCCEDD, resModel);
    }
}
