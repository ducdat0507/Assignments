package com.example.project_1.controllers;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.project_1.entities.RequestHistory;
import com.example.project_1.repositories.RequestHistoryRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/track")
public class TrackingController {

    private final RequestHistoryRepository requestHistoryRepository;

    public TrackingController(RequestHistoryRepository requestHistoryRepository) {
        this.requestHistoryRepository = requestHistoryRepository;
    }


    @GetMapping
    public String track(HttpServletRequest request) {
        RequestHistory requestHistory = new RequestHistory();
        requestHistory.setEndpoint(request.getRequestURI());
        requestHistory.setMethod(request.getMethod());
        requestHistory.setClientIp(request.getRemoteAddr());
        requestHistory.setTimestamp(LocalDateTime.now());
        requestHistoryRepository.save(requestHistory);
        return "OK";
    }
}
