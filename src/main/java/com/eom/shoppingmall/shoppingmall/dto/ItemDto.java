package com.eom.shoppingmall.shoppingmall.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemDto {

    private Long id;
    private String name;
    private Integer price;
    private String content;
    private String itemSellStatus;

    @Builder
    public ItemDto(String name, Integer price, String content) {
        this.name = name;
        this.price = price;
        this.content = content;
    }
}
