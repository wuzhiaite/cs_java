package com.wuzhiaite.javaweb.common.authority.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wuzhiaite.javaweb.base.entity.TreeEntity;
import com.wuzhiaite.javaweb.base.utils.JsonMapperUtil;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.beetl.ext.fn.Json;
import org.springframework.util.StringUtils;

/**
* <p>
* 
* </p>
*
* @author lpf
* @since 2020-06-01
*/
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserDepartment对象", description="")
@Slf4j
public class UserDepartment extends TreeEntity<UserDepartment> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String departmentName;
    @TableField("isValidate")
    private Boolean isValidate ;

    private String bz;
    @TableField(exist = false)
    private String label;

    private String menus ;
    @TableField(exist = false)
    private List<String> menuList;

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName ;
        this.label = departmentName ;
    }

    public String getMenus()  {
        return this.menus;
    }

    public void setMenus(String menus){

        this.menus = menus ;
    }

    public List<String> getMenuList() {
        if(!StringUtils.isEmpty(this.menus)){
            try {
                this.menuList = JsonMapperUtil.parseValue(this.menus,ArrayList.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }else{
            this.menuList = new ArrayList<>();
        }
        return menuList;
    }

    public void setMenuList(List<String> menuList) {
        this.menuList = menuList;
    }
}
