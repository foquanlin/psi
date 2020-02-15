package com.platform.modules.gen.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * 名称：ResultMap <br>
 * 描述：查询表信息返回的BaseResultMap<br>
 *
 * @author lipengjun
 * @date 2019-01-24 14:20
 */
@Data
public class ResultMapEntity {
    /**
     * 表名称
     */
    @TableId
    private String tableName;
    /**
     * engine
     */
    private String engine;
    /**
     * 表注释
     */
    private String tableComment;
    /**
     * 创建时间
     */
    private Date createTime;
}
