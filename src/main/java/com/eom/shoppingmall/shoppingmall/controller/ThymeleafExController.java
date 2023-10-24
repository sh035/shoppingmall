package com.eom.shoppingmall.shoppingmall.controller;

import com.eom.shoppingmall.shoppingmall.dto.ItemDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafExController {

    @GetMapping("/ex02")
    public String thymeleafExample02(Model model) {
        ItemDto itemDto = ItemDto.builder()
                .content("상품 상세 설명")
                .name("테스트 상품 1")
                .price(10000)
                .build();

        model.addAttribute("itemDto", itemDto);
        return "thymeleafEx/thymeleafEx02";
    }
    @GetMapping("/ex07")
    public String thymeleafExample07(){
        return "thymeleafEx/thymeleafEx07";
    }
}
