package com.example.project_5.controller;

import com.example.project_5.model.Flight;
import com.example.project_5.repository.FlightRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class FlightController {

    private final FlightRepository flightRepository;

    public FlightController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @GetMapping("/flights")
    public String listFlights(Model model) {
        model.addAttribute("flights", flightRepository.findAll());
        return "flights";
    }

    @GetMapping("/flights/new")
    public String newFlightForm(Model model) {
        model.addAttribute("flight", new Flight());
        return "flight_form";
    }

    @PostMapping("/flights")
    public String saveFlight(@ModelAttribute Flight flight) {
        flightRepository.save(flight);
        return "redirect:/flights";
    }
}