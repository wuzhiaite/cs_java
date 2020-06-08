package com.wuzhiaite.javaweb.common.authority.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.wuzhiaite.javaweb.base.entity.TreeEntity;
import com.wuzhiaite.javaweb.base.service.ITree;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
* <p>
* 
* </p>
*
* @author lpf
* @since 2020-06-08
*/
@Data
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserPermission对象", description="")
@AllArgsConstructor
@NoArgsConstructor
public class UserPermission extends TreeEntity<UserPermission>  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String menuId;
    @TableField(exist = false)
    private String label ;

    private Boolean add;

    private Boolean edit;

    private Boolean delete;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    public void setAdd(Boolean add) {
        if(add == null) {
            add = true;
        }
        this.add = add;
    }

    public void setEdit(Boolean edit) {
        if(edit == null) {
            edit = true;
        }
        this.edit = edit;
    }

    public void setDelete(Boolean delete) {
        if(delete == null) {
            delete = true;
        }
        this.delete = delete;
    }

    public Boolean getAdd() {
        if(add == null) {
            return true;
        }
        return add;
    }

    public Boolean getEdit() {
        if(edit == null) {
            return true;
        }
        return edit;
    }

    public Boolean getDelete() {
        if(delete == null) {
            return true;
        }
        return delete;
    }
}
