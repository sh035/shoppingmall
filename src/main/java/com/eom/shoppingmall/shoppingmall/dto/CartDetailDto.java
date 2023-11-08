package com.eom.shoppingmall.shoppingmall.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDetailDto {

    private Long cartItemId;
    private String name; // 상품명
    private int price;
    private int count;
    private String imgUrl;

    public CartDetailDto(Long cartItemId, String name, int price, int count, String imgUrl) {
        this.cartItemId = cartItemId;
        this.name = name;
        this.price = price;
        this.count = count;
        this.imgUrl = imgUrl;
    }
}
