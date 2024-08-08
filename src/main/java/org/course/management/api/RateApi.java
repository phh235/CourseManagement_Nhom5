package org.course.management.api;

import org.course.management.entity.Rate;
import org.course.management.entity.User;
import org.course.management.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rate-api")
public class RateApi {
    @Autowired
    private RateService rateService;

    @GetMapping("/getAllRate")
    public Object doGetAllRate() {
        Map<String, Object> result = new HashMap<>();
        try {
            result = new HashMap<>();
            result.put("success", true);
            result.put("status", "Call Api success");
            result.put("data", rateService.getAllRate());
        } catch (Exception e) {
            result.put("error", false);
            result.put("status", "Call Api error");
            result.put("data", null);
            System.out.println(e);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/postRate")
    public ResponseEntity<?> doPostSaveRate(@RequestBody Rate rate) {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("success", true);
            result.put("status", "Call Api success");
            result.put("data", rateService.doSaveRate(rate));
        } catch (Exception e) {
            result.put("error", false);
            result.put("status", "Call Api error");
            result.put("data", null);
            System.out.println(e);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getRatesByCourseId/{courseId}")
    public ResponseEntity<?> doGetRateById(@PathVariable Integer courseId) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Rate> rates = rateService.getRatesByCourseId(courseId);
            result.put("success", true);
            result.put("status", "Call Api success");
            result.put("data", rates);
        } catch (Exception e) {
            result.put("error", false);
            result.put("status", "Call Api error");
            result.put("data", null);
            System.out.println(e);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/deleteRateById/{id}")
    public ResponseEntity<?> doDeleteRateById(@PathVariable int id) {
        Map<String, Object> result = new HashMap<>();
        try {
            rateService.doGetDelete(id);
            result.put("success", true);
            result.put("status", "Call Api success");
        } catch (Exception e) {
            result.put("error", false);
            result.put("status", "Call Api error");
            result.put("data", null);
            System.out.println(e);
        }
        return ResponseEntity.ok(result);
    }


}
