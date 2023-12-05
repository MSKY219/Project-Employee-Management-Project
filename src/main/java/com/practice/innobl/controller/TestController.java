package com.practice.innobl.controller;

import com.practice.innobl.dto.TestDto;
import com.practice.innobl.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    // 확인용
    @GetMapping("/test")
    public String testController () {
        return "test";
    }

    // 전체조회
    @GetMapping("/testUser")
    public String getUser(Model model) {
        List<TestDto> test1 = testService.getUserList();

        model.addAttribute("userList", test1);
        return "user";
    }

    // 등록확인
    @PostMapping("/testSaveUser")
    public String saveUser(@ModelAttribute TestDto inputUser) {
        System.out.println("testDto = " + inputUser);
         testService.testSaveUser(inputUser);
        return "redirect:/testUser";
    }
}
