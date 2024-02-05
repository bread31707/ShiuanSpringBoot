package com.wuw.service;

import com.wuw.core.AbstractSvcLogic;
import com.wuw.dao.entity.TestInfoEntity;
import com.wuw.dao.repository.TestInfoRepository;
import com.wuw.enums.ResultFields;
import com.wuw.service.request.TestInfoAddReq;
import com.wuw.service.response.TestInfoAddRes;
import com.wuw.utils.DateUtils;
import com.wuw.vo.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestInfoAdd extends AbstractSvcLogic<TestInfoAddReq, TestInfoAddRes> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestInfoAdd.class);

    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    TestInfoRepository testInfoRepository;

    @Override
    public Result<TestInfoAddRes> doSvc(TestInfoAddReq reqModel, TestInfoAddRes resModel) {

        TestInfoEntity testInfoEntity = new TestInfoEntity();
        testInfoEntity.setUuid(httpServletRequest.getAttribute("uuid").toString());
        testInfoEntity.setStartTime(DateUtils.localDateTimeFormat(DateUtils.getNowLocalDateTime(), DateUtils.DefaultDateTimePattern));
        testInfoEntity.setValue(reqModel.getValue());
        testInfoRepository.save(testInfoEntity);

        LOGGER.info("hello");

        return new Result<>(ResultFields.SUCCEDD, resModel);
    }

}
