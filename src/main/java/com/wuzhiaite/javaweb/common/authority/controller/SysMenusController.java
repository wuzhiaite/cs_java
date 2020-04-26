package com.wuzhiaite.javaweb.common.authority.controller;

import com.wuzhiaite.javaweb.base.entity.ResultObj;
import com.wuzhiaite.javaweb.common.common.BaseController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController("/sys/menus/")
public class SysMenusController extends BaseController {

    /**
     *
     * @return
     */



    @PostMapping("/getmenuslist")
    public ResultObj getMenusList(){
        List<Map<String, Object>> search = null;
        try {

        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj(search);
    }






}
