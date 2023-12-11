package com.wuw.controller;

import com.wuw.core.AbstractSvcController;
import com.wuw.service.TestInfoAdd;
import com.wuw.service.request.TestInfoAddReq;
import com.wuw.service.response.TestInfoAddRes;
import com.wuw.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestInfoAddController extends AbstractSvcController<TestInfoAddReq, TestInfoAddRes> {

    @Autowired
    TestInfoAdd testInfoAdd;

    @Override
    @PostMapping("/TestInfoAdd")
    public Result<TestInfoAddRes> service(@RequestBody TestInfoAddReq request) {
        return testInfoAdd.doSvc(request, new TestInfoAddRes());
    }
}
