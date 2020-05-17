package com.wuzhiaite.javaweb.common.dict.entity;

    import java.time.LocalDate;
    import java.io.Serializable;
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


}
