package com.wuzhiaite.javaweb.common.activiti.service;

import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricTaskInstance;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 *
 * @description  流程历史数据表
 * @author lpf
 */
public interface IActivitiHistoricService {

    List<HistoricTaskInstance> getHistoricPageList(Map<String, Object> params);

    HistoricDetail getHistoricDetail(Map<String, Object> params);

    void getProccessImage(String processInstanceId, HttpServletResponse response);
}
