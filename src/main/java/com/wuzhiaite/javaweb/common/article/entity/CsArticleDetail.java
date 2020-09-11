package com.wuzhiaite.javaweb.common.article.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.wuzhiaite.javaweb.common.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
* <p>
* 
* </p>
*
* @author lpf
* @since 2020-09-09
*/
@Data
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CsArticleDetail对象", description="")
@AllArgsConstructor
@NoArgsConstructor
public class CsArticleDetail extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String designText;

    private String html;

    private String name;

    private String module;


}
