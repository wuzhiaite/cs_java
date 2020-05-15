package com.wuzhiaite.javaweb.module.demo.demo.controller;


import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuzhiaite.javaweb.base.entity.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import com.wuzhiaite.javaweb.module.demo.demo.service.ICmsAccountCommisionInterestService;
import com.wuzhiaite.javaweb.module.demo.demo.entity.CmsAccountCommisionInterest;

/**
* <p>
* 
* </p>
* @author lpf
* @since 2020-05-14
*/
@RestController
@RequestMapping("/api/demo/cms-account-commision-interest")
@Slf4j
public class CmsAccountCommisionInterestController {

    /**
    * 业务处理类
    */
    @Autowired
    private ICmsAccountCommisionInterestService service;

    /**
    * 查找列表数据
    * @param page entity
    * @return
    */
    @PostMapping("/getPageList")
    public ResultObj getPageList(Page page, CmsAccountCommisionInterest entity){
        Page<CmsAccountCommisionInterest> pageList = null;
        try {
            pageList = service.page(page,new QueryWrapper<CmsAccountCommisionInterest>(entity));
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
    public ResultObj getList(@RequestBody(required = false) CmsAccountCommisionInterest entity){
        List<CmsAccountCommisionInterest> list = null;
        try {
            list = service.list(new QueryWrapper<CmsAccountCommisionInterest>(entity));
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
      CmsAccountCommisionInterest result = null;
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
    public ResultObj addOrUpdatePage( CmsAccountCommisionInterest entity){
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
