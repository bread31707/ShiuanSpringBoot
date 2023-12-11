package com.wuw.service;

import com.wuw.core.AbstractSvcLogic;
import com.wuw.service.request.ActionTestReq;
import com.wuw.service.response.ActionTestRes;
import com.wuw.utils.ClientBuildUtils;
import com.wuw.utils.ClientUtils;
import com.wuw.vo.Result;
import okhttp3.Request;
import org.springframework.stereotype.Service;

@Service
public class ActionTest extends AbstractSvcLogic<ActionTestReq, ActionTestRes> {
    @Override
    public Result<ActionTestRes> doSvc(ActionTestReq reqModel, ActionTestRes resModel) {

//        String url = "";
//
//        ClientBuildUtils build = new ClientBuildUtils();
//        ClientUtils client = new ClientUtils();
//        client.sendPostRequest(url, reqModel, new Request.Builder());

        return null;
    }
}
