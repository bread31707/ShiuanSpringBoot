package com.wuw.service;

import com.wuw.core.AbstractSvcLogic;
import com.wuw.service.request.ActionTestReq;
import com.wuw.service.response.ActionTestRes;
import com.wuw.utils.ClientUtils;
import com.wuw.vo.Result;
import okhttp3.Request;
import org.springframework.stereotype.Service;

@Service
public class ActionTest extends AbstractSvcLogic<ActionTestReq, ActionTestRes> {
    @Override
    public Result<ActionTestRes> doSvc(ActionTestReq reqModel, ActionTestRes resModel) {

        String url = "http://localhost:8081/SupPost";
        String json = "{}";
        String test = "";
        ClientUtils clientUtilsTest = new ClientUtils();
        String actionResponse = clientUtilsTest.sendPostRequest(url, json, new Request.Builder());
        System.out.println(actionResponse);

        return null;
    }
}
