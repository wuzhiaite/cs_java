package com.wuzhiaite.javaweb.flowable.service;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

@Service
public class MyService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Transactional
    public void startProcess() {
        runtimeService.startProcessInstanceByKey("oneTaskProcess");
    }

    @Transactional
    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        Base64.Decoder decoder = Base64.getDecoder();
        String str = "eyJxMSI6NCwicTIiOjQsInEzIjo0LCJxNCI6NSwicTUiOjQsInE2Ijo1LCJxNyI6NSwicTgiOjUsInE5IjpbWzAsMCwwXSxbMCwwLDBdLFswLDAsMF0sWzAsMCwwXSxbMSwxLDFdLFsxLDEsMV0sWzEsMSwxXSxbMSwxLDFdLFsxLDIsMV0sWzEsMiwxXSxbMSwxLDFdLFsxLDEsMV0sWzEsMSwxXV19";
        byte[] decode = decoder.decode(str);
        System.out.println(new String(decode,"UTF-8"));
    }


}
