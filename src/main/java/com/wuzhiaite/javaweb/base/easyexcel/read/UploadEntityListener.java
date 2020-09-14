package com.wuzhiaite.javaweb.base.easyexcel.read;

import com.baomidou.mybatisplus.extension.service.IService;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

/**
 * @description 模板插入数据
 * @author lpf
 */
@Slf4j
public class UploadEntityListener<S extends IService<T>,T> extends IUploadDataListener<T> {
    private  S service;
    public UploadEntityListener(S service){
        this.service = service ;
    }

    @Override
    protected void saveData(List<T> list) {
        log.info(list.toString());
        log.info(service.getClass().toString());
//        service.saveBatch(list,BATCH_COUNT);
    }
}
