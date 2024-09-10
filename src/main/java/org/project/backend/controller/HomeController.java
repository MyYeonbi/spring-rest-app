package org.project.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    @GetMapping("/")  // 루트 경로에 대한 매핑
    public String home(Model model) {
        model.addAttribute("message", "Welcome to baenang");
        return "index";  // 템플릿 이름을 반환 (index.html)
    }
}
