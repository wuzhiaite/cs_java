package com.wuzhiaite.javaweb.common.dict.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuzhiaite.javaweb.base.entity.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import com.wuzhiaite.javaweb.common.dict.service.IDictKeyListService;
import com.wuzhiaite.javaweb.common.dict.entity.DictKeyList;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
* 
* </p>
* @author lpf
* @since 2020-05-16
*/
@RestController
@RequestMapping("/api/dict")
@Slf4j
public class DictKeyListController {

    /**
    * 业务处理类
    */
    @Autowired
    private IDictKeyListService service;

    /**
    * 查找列表数据
    * @param page entity
    * @return
    */
    @PostMapping("/getPageList")
    public ResultObj getPageList(Page page, DictKeyList entity){
        Page<DictKeyList> pageList = null;
        try {
            pageList = service.page(page,new QueryWrapper<DictKeyList>(entity));
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
    public ResultObj getList(@RequestBody(required = false) DictKeyList entity){
        List<DictKeyList> list = null;
        try {
            list = service.list(new QueryWrapper<DictKeyList>(entity));
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
      DictKeyList result = null;
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
    public ResultObj addOrUpdatePage( DictKeyList entity){
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
    public ResultObj batchAddOrUpdate(@RequestBody List<DictKeyList> list){
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
