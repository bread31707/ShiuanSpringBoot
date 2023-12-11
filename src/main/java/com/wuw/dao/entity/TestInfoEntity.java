package com.wuw.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "testinfo")
public class TestInfoEntity {

    @Id
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "starttime")
    private String startTime;
    @Column(name = "value")
    private String value;

}
