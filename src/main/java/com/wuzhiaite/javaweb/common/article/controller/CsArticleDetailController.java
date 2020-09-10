package com.wuzhiaite.javaweb.common.article.controller;


import com.wuzhiaite.javaweb.common.article.entity.CsArticleDetail;
import com.wuzhiaite.javaweb.common.article.service.ICsArticleDetailService;
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
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
* 
* </p>
* @author lpf
* @since 2020-09-09
*/
@RestController
@RequestMapping("/article/")
@Slf4j
public class CsArticleDetailController {

    /**
    * 业务处理类
    */
    @Autowired
    private ICsArticleDetailService service;

    /**
    * 查找列表数据
    * @param param
    * @return
    */
    @PostMapping("/getPageList")
    public ResultObj getPageList(@RequestBody Map<String,Object> param){
        CsArticleDetail entity = StringUtils.isEmpty(param.get("entity"))
                            ? new CsArticleDetail()
                            : JSON.parseObject(JSON.toJSONString(param.get("entity")),CsArticleDetail.class);
        Page page = StringUtils.isEmpty(param.get("page"))
                            ? new Page().setSize(10).setCurrent(1)
                            : JSON.parseObject(JSON.toJSONString(param.get("page")),Page.class);
        QueryWrapper<CsArticleDetail> wrapper = new QueryWrapper<>(entity);
        Page<CsArticleDetail> pageList = service.page(page,wrapper);
        return ResultObj.successObj(pageList);
    }

    /**
    * 查找列表
    * @param entity
    * @return
    */
    @PostMapping("/getList")
    public ResultObj getList(@RequestBody(required = false) CsArticleDetail entity){
        List<CsArticleDetail> list  = service.list(new QueryWrapper<CsArticleDetail>(entity));
        return ResultObj.successObj(list);
    }



    /**
    * 通过ID获取
    * @param id
    * @return
    */
    @PostMapping("/getBlogById/{id}")
    public ResultObj getPageById(@PathVariable String id){
        CsArticleDetail result = service.getById(id);
        return ResultObj.successObj(result);
    }

    /**
    * 保存或修改数据
    * @param entity
    * @return
    */
    @PostMapping("/addOrUpdatePage")
    public ResultObj addOrUpdatePage(@RequestBody  CsArticleDetail entity){
        boolean flag =  service.saveOrUpdate(entity);
        return ResultObj.successObj(flag);
    }
    /**
    * 批量保存或修改数据
    * @param list
    * @return
    */
    @PostMapping("/batchAddOrUpdate")
    public ResultObj batchAddOrUpdate(@RequestBody List<CsArticleDetail> list){
        boolean flag = service.saveOrUpdateBatch(list);
        return ResultObj.successObj(flag);
    }
    /**
    * 通过ID移除
    * @param id
    * @return
    */
    @PostMapping("/removeById/{id}")
    public ResultObj removeById(@PathVariable String id){
        boolean flag = service.removeById(id);
        return ResultObj.successObj(flag);
    }




 }
