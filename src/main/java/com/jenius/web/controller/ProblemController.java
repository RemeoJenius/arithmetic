package com.jenius.web.controller;

import com.jenius.web.mate.Problem;
import com.jenius.web.service.impl.ProblemServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liyongjun on 17/4/16.
 */
@Controller
public class ProblemController {
    @Resource
    private ProblemServiceImpl problemServiceImpl;

    @RequestMapping("problemList/{i}")
    @ResponseBody
    public Map<String,Object> problemList(@PathVariable("i")int i){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("problemList",problemServiceImpl.getProblemList((i-1)*3));
        map.put("pages",problemServiceImpl.getProblemListPagesNumber());
        return map;
    }
}
