package com.jenius.web.dao;

import com.jenius.web.mate.Problem;

import java.util.List;

/**
 * Created by liyongjun on 17/4/16.
 */
public interface ProblemDao {
    List<Problem> getProblemList(int i);

    int getProblemListPagesNumber();

}
