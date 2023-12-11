package com.wuw.controller;

import com.wuw.core.AbstractSvcController;
import com.wuw.service.TestInfoDelete;
import com.wuw.service.request.TestInfoDeleteReq;
import com.wuw.service.response.TestInfoDeleteRes;
import com.wuw.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestInfoDeleteController extends AbstractSvcController<TestInfoDeleteReq, TestInfoDeleteRes> {

    @Autowired
    TestInfoDelete testInfoDelete;

    @Override
    @PostMapping("/TestInfoDelete")
    public Result<TestInfoDeleteRes> service(@RequestBody TestInfoDeleteReq request) {
        return testInfoDelete.doSvc(request, new TestInfoDeleteRes());
    }
}
