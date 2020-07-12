package com.wuzhiaite.javaweb.common.activiti.service.impl;

import com.wuzhiaite.javaweb.base.utils.MapUtil;
import com.wuzhiaite.javaweb.base.utils.SpringSecurityUtil;
import com.wuzhiaite.javaweb.common.activiti.service.IActivitiHistoricService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricDetailQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @description 流程历史数据
 * @author lpf
 */
@Service
public class ActivitiHistoricServiceImpl  implements IActivitiHistoricService {

    @Autowired
    private HistoryService historyService;

    /**
     *  获取历史列表数据
     * @param params
     * @return
     */
    @Override
    public List<HistoricTaskInstance> getHistoricPageList(Map<String, Object> params) {
        Integer pageNum = MapUtil.getInteger(params, "pageNum");
        Integer pageSize = MapUtil.getInteger(params, "pageSize");

        HistoricTaskInstanceQuery historicTaskInstanceQuery = historyService.createHistoricTaskInstanceQuery();
        String currentUserName = SpringSecurityUtil.getCurrentUserName();
        historicTaskInstanceQuery.taskAssignee(currentUserName);

        String processDefinitionId = "";
        if(StringUtils.isEmpty(processDefinitionId = MapUtil.getString(params,"processDefinitionId"))){
            historicTaskInstanceQuery.processDefinitionId(processDefinitionId);
        }
        String processDefinitionkey = "";
        if(StringUtils.isEmpty(processDefinitionkey = MapUtil.getString(params,"processDefinitionkey"))){
            historicTaskInstanceQuery.processDefinitionKey(processDefinitionkey);
        }


        List<HistoricTaskInstance> list = historicTaskInstanceQuery.listPage(pageNum,pageSize);

        return list;
    }

    /**
     * 查找历史记录的明细数据
     * @param params
     */
    @Override
    public HistoricDetail getHistoricDetail(Map<String, Object> params) {
        HistoricDetailQuery historicDetailQuery = historyService.createHistoricDetailQuery();

        historicDetailQuery.processInstanceId(MapUtil.getString(params,"processInstanceId"));

        HistoricDetail historicDetail = historicDetailQuery.formProperties().singleResult();

        return historicDetail;
    }


}
