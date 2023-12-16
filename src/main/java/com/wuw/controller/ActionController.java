package com.wuw.controller;

import com.wuw.core.AbstractSvcController;
import com.wuw.service.ActionTest;
import com.wuw.service.request.ActionTestReq;
import com.wuw.service.response.ActionTestRes;
import com.wuw.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ActionController extends AbstractSvcController<ActionTestReq, ActionTestRes> {

    @Autowired
    ActionTest actionTest;
    @Override
    @PostMapping("/ActionTest")
    public Result<ActionTestRes> service(@RequestBody ActionTestReq request) {
        return actionTest.doSvc(request, new ActionTestRes());
    }
}
