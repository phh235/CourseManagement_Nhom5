package org.course.management.service.impl;

import org.course.management.entity.Rate;
import org.course.management.repository.RateRepository;
import org.course.management.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    private RateRepository rateRepository;

    @Override
    public List<Rate> getAllRate() {
        return rateRepository.findAll();
    }

    @Override
    public Rate doSaveRate(Rate rate) {
        return rateRepository.saveAndFlush(rate);
    }

    @Override
    public List<Rate> getRatesByCourseId(int courseId) {
        return rateRepository.findByCourseId(courseId);
    }

    @Override
    public void doGetDelete(int id) {
        rateRepository.deleteById(id);
    }

    @Override
    public Double doGetAverageRate(int courseId) {
        return rateRepository.getAverageStar(courseId);
    }

}

