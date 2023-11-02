package com.eom.shoppingmall.shoppingmall.dto;

import com.eom.shoppingmall.shoppingmall.enums.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSearchDto {

    private String searchDateType;

    private ItemSellStatus searchSellStatus;

    private String searchBy;

    private String searchQuery = "";
}
