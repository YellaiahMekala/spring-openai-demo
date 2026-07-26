package com.springai.controller;

import com.springai.dto.TripPlan;
import com.springai.service.TripPlannerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/trips")
public class TripPlannerController {

    private final  TripPlannerService tripPlannerService;

    private  TripPlannerController(TripPlannerService tripPlannerService){
        this.tripPlannerService = tripPlannerService;
    }

    @GetMapping("/plan-trip")
    public TripPlan getTripPlans(@RequestParam String message){
        return  tripPlannerService.getTripPlan(message);
    }

    @GetMapping("/trip-spots")
    public List<String> getTripSpots(@RequestParam String message){
        return  tripPlannerService.getBestSpots(message);
    }

    @GetMapping("/trip-guide")
    public Map<String,Object> getTripGuide(@RequestParam String message){
        return  tripPlannerService.getTripGuide(message);
    }

    @GetMapping("/complete-trip-plan")
    public List<TripPlan> getCompleteTripPlan(@RequestParam String message){
        return  tripPlannerService.getCompleteTrip(message);
    }

}
