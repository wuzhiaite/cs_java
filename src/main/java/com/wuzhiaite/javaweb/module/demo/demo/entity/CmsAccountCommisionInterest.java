package com.wuzhiaite.javaweb.module.demo.demo.entity;

    import java.time.LocalDate;
    import com.baomidou.mybatisplus.annotation.TableField;
    import java.io.Serializable;
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
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
* @since 2020-05-14
*/
    @Data
    @ToString
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @ApiModel(value="CmsAccountCommisionInterest对象", description="")
    public class CmsAccountCommisionInterest implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "账户号码")
    private String accountid;

            @ApiModelProperty(value = "备注")
    private String remark;

            @ApiModelProperty(value = "方案类型")
    private String brokageType;

            @ApiModelProperty(value = "生效日期")
    private String commisionActiveDate;

            @ApiModelProperty(value = "优惠期开始日期")
    private String commisionPromotionStart;

            @ApiModelProperty(value = "优惠期结束日期	")
    private String commisionPromotionEnd;

            @ApiModelProperty(value = "开通市场")
    private String market;

            @ApiModelProperty(value = "交易类型")
    private String tradeType;

            @ApiModelProperty(value = "交易佣金")
    private Double typeRate;

            @ApiModelProperty(value = "	最低收费")
    private String typeCurrencyLow;

            @ApiModelProperty(value = "利率生效日期")
    private String interestActiveDate;

            @ApiModelProperty(value = "利率优惠期开始日期")
    private String interestPromotionStart;

            @ApiModelProperty(value = "利率优惠期结束日期")
    private String interestPromotionEnd;

            @ApiModelProperty(value = "利率币种")
    private String currency;

            @ApiModelProperty(value = "货币利率")
    private String interestCurrencyRate;

    private LocalDate fdCreateTime;

    private LocalDate fdUpdateTime;

        @TableField("FD_CREATER")
    private String fdCreater;

        @TableField("FD_UPDATER")
    private String fdUpdater;

        @TableField("FD_ID")
    private String fdId;


}
