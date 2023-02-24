/*
 * 项目名称:项目名称
 * 类名称:PsiWarehouseEntity.java
 * 包名称:com.tongyi.modules.psi.entity
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 仓库实体
 *
 * @author 惠州市酷天科技有限公司
 * @date 2022-10-12 01:48:52
 */
@Data
@TableName(value = "psi_warehouse",autoResultMap = true)
public class PsiWarehouseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 位置
     */
    private String location;
    /**
     * 容量
     */
    private BigDecimal capacity;
    /**
     * 状态
     */
    private String status;
    /**
     * 说明
     */
    private String description;
    /**
     * 负责人
     */
    private String master;
    /**
     * 默认仓库
     */
    private Boolean defaulted;

    public enum Status {
        RUN("RUN","启用"),
        STOP("STOP","停用");

        private String code;
        private String name;

        Status(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
