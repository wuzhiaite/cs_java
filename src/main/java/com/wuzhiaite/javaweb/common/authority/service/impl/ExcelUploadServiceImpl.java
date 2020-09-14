package com.wuzhiaite.javaweb.common.authority.service.impl;

import com.wuzhiaite.javaweb.base.easyexcel.read.IUploadDataListener;
import com.wuzhiaite.javaweb.common.authority.service.IExcelUploadService;
import com.wuzhiaite.javaweb.common.common.ComCrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *
 * @author lpf
 */
@Service
public class ExcelUploadServiceImpl extends IUploadDataListener<Map<String,Object>>
        implements IExcelUploadService {


    @Override
    protected void saveData(List list) {




    }




}
