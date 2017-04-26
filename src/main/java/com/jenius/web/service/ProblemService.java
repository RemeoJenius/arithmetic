package com.jenius.web.service;

import com.jenius.web.mate.Problem;

import java.util.List;

/**
 * Created by liyongjun on 17/4/16.
 */
public interface ProblemService {
    List<Problem> getProblemList(int i);

    int getProblemListPagesNumber();
}
