package org.course.management.service;

import org.course.management.entity.Rate;

import java.util.List;

public interface RateService {
    List<Rate> getAllRate();

    Rate doSaveRate(Rate rate);

    List<Rate> getRatesByCourseId(int courseId);

    void doGetDelete(int id);

    Double doGetAverageRate(int courseId);

}

