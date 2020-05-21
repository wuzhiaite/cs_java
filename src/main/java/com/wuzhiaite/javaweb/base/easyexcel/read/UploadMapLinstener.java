package com.wuzhiaite.javaweb.base.easyexcel.read;


import com.wuzhiaite.javaweb.common.common.ComCrudServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 *
 */
@Slf4j
public class UploadMapLinstener<S extends ComCrudServiceImpl> extends IUploadDataListener<Map<String,Object>> {

    private  S service;
    public UploadMapLinstener(S service){
        this.service = service ;
    }

    @Override
    protected void saveData(List<Map<String, Object>> list) {
        log.info(list.toString());
        log.info(service.getClass().toString());
        service.saveBatch(list,BATCH_COUNT);
    }
}
