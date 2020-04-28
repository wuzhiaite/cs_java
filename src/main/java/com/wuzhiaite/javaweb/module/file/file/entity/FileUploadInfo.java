package com.wuzhiaite.javaweb.module.file.file.entity;

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
* @since 2020-04-28
*/
    @Data
    @ToString
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @ApiModel(value="FileUploadInfo对象", description="")
    public class FileUploadInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fileName;

    private String fileSize;

    private String fileSuffix;

    private String filePath;

    private String fileSign;

    private String creater;

    private String updater;


}
