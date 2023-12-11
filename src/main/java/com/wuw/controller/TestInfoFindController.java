package com.wuw.controller;

import com.wuw.core.AbstractSvcController;
import com.wuw.service.TestInfoFind;
import com.wuw.service.request.TestInfoFindReq;
import com.wuw.service.response.TestInfoFindRes;
import com.wuw.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestInfoFindController extends AbstractSvcController<TestInfoFindReq, TestInfoFindRes> {

    @Autowired
    TestInfoFind testInfoFind;

    @Override
    @PostMapping("TestInfoFind")
    public Result<TestInfoFindRes> service(@RequestBody TestInfoFindReq request) {
        return testInfoFind.doSvc(request, new TestInfoFindRes());
    }
}
