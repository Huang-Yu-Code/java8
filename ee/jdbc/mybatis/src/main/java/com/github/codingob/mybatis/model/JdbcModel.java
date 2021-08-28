package com.github.codingob.mybatis.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * jdbc_model
 * @author 
 */
@Data
public class JdbcModel implements Serializable {
    private Long id;

    private String name;

    private Boolean gender;

    private Long money;

    private LocalDateTime createTime;

    private Boolean delete;

    private static final long serialVersionUID = 1L;
}