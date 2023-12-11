package com.wuw.controller;

import com.wuw.core.AbstractSvcController;
import com.wuw.service.TestService;
import com.wuw.service.request.TestReq;
import com.wuw.service.response.TestRes;
import com.wuw.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController extends AbstractSvcController<TestReq, TestRes> {

    @Autowired
    TestService testService;

    @Override
    @PostMapping("/TestService")
    public Result<TestRes> service(@RequestBody TestReq reqModel){
        return testService.doSvc(reqModel, new TestRes());
    }

}
