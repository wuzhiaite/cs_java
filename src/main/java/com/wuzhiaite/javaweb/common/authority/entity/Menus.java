package com.wuzhiaite.javaweb.common.authority.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 *
 * @author lpf
 * @since 2020-04-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Menus对象", description="")
@ToString
public class Menus implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String path;

    private String name;

    @TableField(value="name")
    private String label;

    @TableField("iconCls")
    private String iconCls;

    @TableField("realPath")
    private String realPath;

    private Boolean hidden;

    private String fatherId;

    private Boolean isvalidate;

    private Boolean iskeepalive;

    @TableField(exist = false)
    private List<Menus> children;

}
