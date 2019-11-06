package com.wuzhiaite.javaweb.module.pagelistconfig.service;

import com.wuzhiaite.javaweb.module.pagelistconfig.mapper.SearchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SearchService {

    @Autowired
    private SearchMapper mapper;

    public List<Map<String,Object>> search(){


        return null;
    }



}
