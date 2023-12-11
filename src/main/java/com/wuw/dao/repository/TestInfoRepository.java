package com.wuw.dao.repository;

import com.wuw.dao.entity.TestInfoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestInfoRepository extends CrudRepository<TestInfoEntity, String> {

    List<TestInfoEntity> findByValue(String value);

    @Transactional
    void deleteByValue(String value);

}
