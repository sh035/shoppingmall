package com.eom.shoppingmall.shoppingmall.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainItemDto {

    private Long id;

    private String name;

    private String content;

    private String imgUrl;

    private Integer price;

    @QueryProjection
    public MainItemDto(Long id, String name, String content, String imgUrl, Integer price) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.imgUrl = imgUrl;
        this.price = price;
    }
}
