package com.auca.collegefeemanagement.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auca.collegefeemanagement.service.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")

public class DashboardController {
    
  @Autowired
    private DashboardService dashboardService;

    @GetMapping("/summary")
    public Map<String, Long> getSummary() {
        return dashboardService.getSummary();
    }
}