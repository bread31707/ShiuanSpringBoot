package com.wuw.service;

import com.wuw.core.AbstractSvcLogic;
import com.wuw.dao.repository.TestInfoRepository;
import com.wuw.enums.ResultFields;
import com.wuw.service.request.TestInfoDeleteReq;
import com.wuw.service.response.TestInfoDeleteRes;
import com.wuw.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestInfoDelete extends AbstractSvcLogic<TestInfoDeleteReq, TestInfoDeleteRes> {

    @Autowired
    TestInfoRepository testInfoRepository;

    @Override
    public Result<TestInfoDeleteRes> doSvc(TestInfoDeleteReq reqModel, TestInfoDeleteRes resModel) {

        testInfoRepository.deleteByValue(reqModel.getValue());

        return new Result<>(ResultFields.SUCCEDD, resModel);
    }

}
