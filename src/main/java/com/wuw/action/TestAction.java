package com.wuw.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wuw.action.request.TestActionReq;
import com.wuw.action.response.TestActionRes;
import com.wuw.core.AbstractActionLogic;
import com.wuw.enums.ResultFields;
import com.wuw.handler.AppException;
import com.wuw.service.response.ActionTestRes;
import com.wuw.utils.ClientUtils;
import okhttp3.Request;
import org.springframework.stereotype.Component;

@Component
public class TestAction extends AbstractActionLogic<TestActionReq, TestActionRes> {
    @Override
    public TestActionRes doAction(TestActionReq reqModel) {

        TestActionRes resModel = new TestActionRes();
        ObjectMapper objectMapper = new ObjectMapper();

        String url = "http://localhost:8081/SupPost";
        ClientUtils clientUtilsTest = new ClientUtils();

        try {
            String reqModelJson = objectMapper.writeValueAsString(reqModel);
            String actionResponse = clientUtilsTest.sendPostRequest(url, reqModelJson, new Request.Builder());
            ActionTestRes actionTestRes = objectMapper.readValue(actionResponse, ActionTestRes.class);
            resModel.setAemInfoList(actionTestRes.getAemInfoList());

        } catch (JsonProcessingException e) {
            throw new AppException(ResultFields.FAIL_JSON_CONVERT);
        }

        return resModel;
    }
}
