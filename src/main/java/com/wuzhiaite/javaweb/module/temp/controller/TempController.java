package com.wuzhiaite.javaweb.module.temp.controller;


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
import com.wuzhiaite.javaweb.module.temp.service.ITempService;
import com.wuzhiaite.javaweb.module.temp.entity.Temp;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
* 
* </p>
* @author lpf
* @since 2020-06-17
*/
@RestController
@RequestMapping("/api/temp")
@Slf4j
public class TempController {

    /**
    * 业务处理类
    */
    @Autowired
    private ITempService service;

    /**
    * 查找列表数据
    * @param param
    * @return
    */
    @PostMapping("/getPageList")
    public ResultObj getPageList(@RequestBody Map<String,Object> param){
        Page<Temp> pageList = null;
        try {
            Temp entity = StringUtils.isEmpty(param.get("entity"))
                                ? new Temp()
                                : JSON.parseObject(JSON.toJSONString(param.get("entity")),Temp.class);
            Page page = StringUtils.isEmpty(param.get("page"))
                                ? new Page().setSize(10).setCurrent(1)
                                : JSON.parseObject(JSON.toJSONString(param.get("page")),Page.class);
            QueryWrapper<Temp> wrapper = new QueryWrapper<>(entity);
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
    public ResultObj getList(@RequestBody(required = false) Temp entity){
        List<Temp> list = null;
        try {
            list = service.list(new QueryWrapper<Temp>(entity));
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
      Temp result = null;
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
    public ResultObj addOrUpdatePage(@RequestBody  Temp entity){
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
    public ResultObj batchAddOrUpdate(@RequestBody List<Temp> list){
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
