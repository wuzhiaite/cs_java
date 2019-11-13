package com.wuzhiaite.javaweb.module.pagelistconfig.service.search.check.entity;

import com.wuzhiaite.javaweb.module.pagelistconfig.service.search.check.CheckFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Descript 测试接口包装类
 * @author  lpf
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ParamCheckWapper {

    private CheckFilter paramCheck;
    private String name;

}
