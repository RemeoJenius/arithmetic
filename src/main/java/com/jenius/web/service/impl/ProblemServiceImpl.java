package com.jenius.web.service.impl;

import com.jenius.web.dao.ProblemDao;
import com.jenius.web.mate.Problem;
import com.jenius.web.service.ProblemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liyongjun on 17/4/16.
 */
@Service
public class ProblemServiceImpl implements ProblemService{
    @Resource(name = "problemDao")
    private ProblemDao problemDao;
    public List<Problem> getProblemList(int i) {
        return problemDao.getProblemList(i);
    }

    public int getProblemListPagesNumber() {
        return problemDao.getProblemListPagesNumber();
    }
}
