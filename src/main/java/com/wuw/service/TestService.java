package com.wuw.service;

import com.wuw.core.AbstractSvcLogic;
import com.wuw.enums.ResultFields;
import com.wuw.service.request.TestReq;
import com.wuw.service.response.TestRes;
import com.wuw.vo.Result;
import org.springframework.stereotype.Service;

@Service
public class TestService extends AbstractSvcLogic<TestReq, TestRes> {

    @Override
    public Result<TestRes> doSvc(TestReq reqModel,TestRes resModel) {

        resModel.setTest(reqModel.getTestStr());

        return new Result<>(ResultFields.SUCCEDD, resModel);
    }

}
