/*
 * 项目名称:项目名称
 * 类名称:PsiCostTypeEntity.java
 * 包名称:com.tongyi.modules.psi.entity
 * @author 惠州市酷天科技有限公司
 * @date 2023-03-28 00:14:03
 * Copyright (c) 2019-2021 惠州市酷天科技有限公司
 */
package com.tongyi.modules.psi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tongyi.common.utils.StringUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * 收支类型实体
 *
 * @author 惠州市酷天科技有限公司
 * @date 2023-03-28 00:14:03
 */
@Data
@TableName(value = "psi_cost_type",autoResultMap = true)
public class PsiCostTypeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private String id;
    /**
     * 收支类型
     */
    private String type;
    /**
     * 名称
     */
    private String name;
    /**
     * 是否启用
     */
    private Boolean status;
    /**
     * 是否计入利润
     */
    private Boolean profited;

    public enum Type {
        IN("IN","收入"),
        OUT("OUT","支出");

        private String code;
        private String name;

        Type(String code, String name) {
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
        public String newNo(){
            return StringUtils.generateOrderNumber(this.code);
        }
    }
}
