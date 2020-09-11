package com.wuzhiaite.javaweb.common.authority.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.*;

import com.baomidou.mybatisplus.annotation.TableId;
import com.wuzhiaite.javaweb.base.entity.TreeEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;

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
public class Menus extends TreeEntity implements Serializable {

    private static final String MENU_PERMISSION = "permissions";
    private static final String CAN_ADD = "can_add";
    private static final String CAN_EDIT = "can_edit";
    private static final String CAN_DELETE = "can_delete";


    private static final long serialVersionUID = 1L;

    private String path;

    private String name;

    @TableField(exist = false)
    private String label;
    @TableField(exist = false)
    private String value;

    @TableField("iconCls")
    private String iconCls;

    @TableField("realPath")
    private String realPath;

    private Boolean hidden;

    private Boolean isvalidate;

    private Boolean iskeepalive;
    @TableField(exist = false)
    private Map<String,Object> meta = new HashMap<>();
    @TableField(exist = false)
    private Boolean canAdd = false;
    @TableField(exist = false)
    private Boolean canEdit = false;
    @TableField(exist = false)
    private Boolean canDelete = false;

    public void setName(String name) {
        this.name = name;
        this.label = name ;
    }

    @Override
    public void setId(String id){
        this.id = id;
        this.value = id ;
    }

    public Boolean getCanAdd() {
        return canAdd;
    }

    public void setCanAdd(Boolean canAdd) {
        this.addPermissions(canAdd,CAN_ADD);
        this.canAdd = canAdd;
    }


    public Boolean getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(Boolean canEdit) {
        this.addPermissions(canEdit,CAN_EDIT);
        this.canEdit = canEdit;
    }

    public Boolean getCanDelete() {
        return canDelete;
    }

    public void setCanDelete(Boolean canDelete) {
        this.addPermissions(canDelete,CAN_DELETE);
        this.canDelete = canDelete;
    }

    /**
     * 设置默认权限列表
     * @return
     */
    private HashSet<String> getPermissions(){
        if(StringUtils.isEmpty(this.meta.get(MENU_PERMISSION))){
            this.meta.put(MENU_PERMISSION,new HashSet<String>());
        }
        return (HashSet<String>) this.meta.get(MENU_PERMISSION);
    }

    /**
     *  增加权限
     * @param type
     */
    private void addPermissions(Boolean flag , String type) {
        if(!StringUtils.isEmpty(flag) && flag && !StringUtils.isEmpty(type)){
            HashSet<String> permissions = this.getPermissions();
            if (!permissions.contains(type)) {
                permissions.add(type);
            }
            this.setPermissions(permissions);
        }
    }

    /**
     * 设置权限
     * @param permissions
     */
    private void setPermissions(HashSet<String> permissions) {
        this.meta.put(MENU_PERMISSION,permissions);
    }




}
