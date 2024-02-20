package com.wuw.service;

import com.wuw.action.TestAction;
import com.wuw.action.request.TestActionReq;
import com.wuw.action.response.TestActionRes;
import com.wuw.core.AbstractSvcLogic;
import com.wuw.enums.ResultFields;
import com.wuw.service.request.ActionTestReq;
import com.wuw.service.response.ActionTestRes;
import com.wuw.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActionTest extends AbstractSvcLogic<ActionTestReq, ActionTestRes> {

    @Autowired
    TestAction testAction;

    @Override
    public Result<ActionTestRes> doSvc(ActionTestReq reqModel, ActionTestRes resModel){

        TestActionRes testActionRes = testAction.doAction(buildTestActionReq());
        resModel.setAemInfoList(testActionRes.getAemInfoList());

        return new Result<>(ResultFields.SUCCEDD, resModel);
    }

    private TestActionReq buildTestActionReq(){
        TestActionReq model = new TestActionReq();
        return model;
    }

}
