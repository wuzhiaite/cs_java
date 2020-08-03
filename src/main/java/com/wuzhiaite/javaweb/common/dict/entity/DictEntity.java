package com.wuzhiaite.javaweb.common.dict.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="字典映射实体类", description="")
public class DictEntity {

    private String dictNameText;

    private String dictName;

    private String dictKey;

    private String dictValue;

    @TableField(exist = false)
    private String value;
    @TableField(exist = false)
    private String label;

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey;
        this.value = dictKey ;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
        this.label = dictValue;
    }
}
