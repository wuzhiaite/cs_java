package com.wuzhiaite.javaweb.common.dict.entity;

    import java.io.Serializable;
    import java.util.Date;
    import java.util.List;

    import com.baomidou.mybatisplus.annotation.TableField;
    import io.swagger.annotations.ApiModel;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;
    import lombok.ToString;

/**
* <p>
    * 
    * </p>
*
* @author lpf
* @since 2020-05-16
*/
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DictKeyList对象", description="")
public class DictKeyList implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dictNameText;

    private String dictName;

    private String bz ;
    @TableField(exist = false)
    private List<DictKeyValueMapping>  dictMapping ;
    @TableField(exist = false)
    private String search ;

    private String id;
    private Date createTime;
    private Date updateTime;
    private String createUser;
    private String updateUser;

}
