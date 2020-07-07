package com.wuzhiaite.javaweb.activiti;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution execution) {
        String eventName = execution.getEventName();
        log.info("-----------------------MyListener {}--------------------",eventName);



    }
}
