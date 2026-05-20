package com.example.project_8.web;

import com.example.project_8.entity.MessageEntity;
import com.example.project_8.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PageController {

    private final MessageService messageService;

    public PageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/chat")
    public String chat(@RequestParam String username, HttpSession session, Model model) {
        session.setAttribute("username", username);
        List<MessageEntity> history = messageService.last50();
        model.addAttribute("username", username);
        model.addAttribute("history", history);
        return "chat";
    }

}
