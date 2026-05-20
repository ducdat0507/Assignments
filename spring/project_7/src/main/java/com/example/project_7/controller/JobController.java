package com.example.project_7.controller;

import com.example.project_7.model.Job;
import com.example.project_7.service.JobService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public String listJobs(Model model) {
        model.addAttribute("jobs", jobService.findAll());
        return "jobs/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("job", new Job());
        return "jobs/add";
    }

    @PostMapping("/add")
    public String addJob(@Valid @ModelAttribute("job") Job job, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "jobs/add";
        }
        jobService.save(job);
        return "redirect:/jobs";
    }

    @GetMapping("/delete/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobService.deleteById(id);
        return "redirect:/jobs";
    }
}
