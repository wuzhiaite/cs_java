package com.wuzhiaite.javaweb.common.authority.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuzhiaite.javaweb.base.entity.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.Map;
import com.wuzhiaite.javaweb.common.authority.service.IUserDepartmentService;
import com.wuzhiaite.javaweb.common.authority.entity.UserDepartment;
import org.springframework.web.bind.annotation.RestController;
import com.wuzhiaite.javaweb.module.per.entity.DepartmentMenusPermission;
import com.wuzhiaite.javaweb.module.per.service.IDepartmentMenusPermissionService ;


/**
* <p>
* 
* </p>
* @author lpf
* @since 2020-06-01
*/
@RestController
@RequestMapping("/department")
@Slf4j
public class UserDepartmentController {

    /**
    * 业务处理类
    */
    @Autowired
    private IUserDepartmentService service;
    @Autowired
    private IDepartmentMenusPermissionService departmentMenusPermissionService ;

    /**
    * 查找列表数据
    * @param param
    * @return
    */
    @PostMapping("/getPageList")
    public ResultObj getPageList(@RequestBody Map<String,Object> param){
        Page<UserDepartment> pageList = null;
        try {
            UserDepartment entity = StringUtils.isEmpty(param.get("entity"))
                                ? new UserDepartment()
                                : JSON.parseObject(JSON.toJSONString(param.get("entity")),UserDepartment.class);
            Page page = StringUtils.isEmpty(param.get("page"))
                                ? new Page().setSize(10).setCurrent(1)
                                : JSON.parseObject(JSON.toJSONString(param.get("page")),Page.class);
            QueryWrapper<UserDepartment> wrapper = new QueryWrapper<>(entity);
            pageList = service.page(page,wrapper);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj(pageList);
    }

    /**
    * 查找列表
    * @param entity
    * @return
    */
    @PostMapping("/getList")
    public ResultObj getList(@RequestBody(required = false) UserDepartment entity){
        List<UserDepartment> list = null;
        try {
            list = service.depList(entity);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj(list);
    }

    /**
    * 通过ID获取
    * @param id
    * @return
    */
    @PostMapping("/getPageById/{id}")
    public ResultObj getPageById(@PathVariable String id){
      UserDepartment result = null;
        try {
            result = service.getById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj(result);
    }

    /**
    * 保存或修改数据
    * @param entity
    * @return
    */
    @PostMapping("/addOrUpdatePage")
    public ResultObj addOrUpdatePage(@RequestBody  UserDepartment entity){
        boolean flag = false;
        try {
            flag = service.saveOrUpdate(entity);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj(flag);
    }
    /**
    * 批量保存或修改数据
    * @param list
    * @return
    */
    @PostMapping("/batchAddOrUpdate")
    public ResultObj batchAddOrUpdate(@RequestBody List<UserDepartment> list){
        boolean flag = false;
        try {
            flag = service.saveOrUpdateBatch(list);
        } catch (Exception e) {
            log.error(e.getMessage());
             return ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj(flag);
    }
    /**
    * 通过ID移除
    * @param id
    * @return
    */
    @PostMapping("/removeById/{id}")
    public ResultObj removeById(@PathVariable String id){
        boolean flag = false ;
        try {
            flag = service.removeById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultObj.failObj(e.getMessage());
        }
        return ResultObj.successObj(flag);
    }

 }
