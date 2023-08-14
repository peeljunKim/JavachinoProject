package com.example.demo.activity.controller;

import java.text.DecimalFormat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.activity.dto.ActivityDto;

@Controller
public class PayController {

    // 돈 .0은 생략됨
    public String formatCurrency(double amount) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        return "₩" + decimalFormat.format(amount) + "원";
    }
}