package com.wuw.service;

import com.wuw.core.AbstractSvcLogic;
import com.wuw.dao.entity.TestInfoEntity;
import com.wuw.dao.repository.TestInfoRepository;
import com.wuw.enums.ResultFields;
import com.wuw.service.request.TestInfoFindReq;
import com.wuw.service.response.TestInfoFindRes;
import com.wuw.vo.Result;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class TestInfoFind extends AbstractSvcLogic<TestInfoFindReq, TestInfoFindRes> {

    @Autowired
    TestInfoRepository testInfoRepository;

    @Override
    public Result<TestInfoFindRes> doSvc(TestInfoFindReq reqModel,TestInfoFindRes resModel) {


        testInfoRepository.findByValue(reqModel.getValue()).forEach(node -> {
            TestInfoFindRes.Info info = new TestInfoFindRes.Info();
            info.setUuid(node.getUuid());
            info.setStartTime(node.getStartTime());
            info.setValue(node.getValue());
            resModel.getInfoList().add(info);
        });
        // 對 list 做排序
        resModel.getInfoList().sort(Comparator.comparing(TestInfoFindRes.Info::getUuid));

        return new Result<>(ResultFields.SUCCEDD, resModel);
    }
}
