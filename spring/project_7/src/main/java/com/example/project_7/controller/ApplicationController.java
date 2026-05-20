package com.example.project_7.controller;

import com.example.project_7.model.Application;
import com.example.project_7.model.Job;
import com.example.project_7.service.ApplicationService;
import com.example.project_7.service.JobService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final JobService jobService;

    public ApplicationController(ApplicationService applicationService, JobService jobService) {
        this.applicationService = applicationService;
        this.jobService = jobService;
    }

    @GetMapping("/apply")
    public String showApplyForm(@RequestParam(required = false) Long jobId, Model model) {
        model.addAttribute("application", new Application());
        model.addAttribute("jobs", jobService.findAll());
        model.addAttribute("selectedJobId", jobId);
        return "applications/apply";
    }

    @PostMapping("/apply")
    public String apply(@RequestParam Long jobId, @Valid @ModelAttribute("application") Application application, BindingResult bindingResult, Model model) {
        Job job = jobService.findById(jobId).orElse(null);
        if (job == null) {
            bindingResult.rejectValue("job", "NotFound", "Selected job not found");
        } else {
            application.setJob(job);
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("jobs", jobService.findAll());
            return "applications/apply";
        }

        applicationService.save(application);
        return "applications/confirmation";
    }

    @GetMapping("/job/{jobId}")
    public String viewApplicationsForJob(@PathVariable Long jobId, Model model) {
        Job job = jobService.findById(jobId).orElse(null);
        if (job == null) {
            return "redirect:/jobs";
        }
        model.addAttribute("job", job);
        model.addAttribute("applications", applicationService.findByJob(job));
        return "applications/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteApplication(@PathVariable Long id) {
        applicationService.deleteById(id);
        return "redirect:/jobs";
    }
}
