package com.eom.shoppingmall.shoppingmall.repository.custom;

import com.eom.shoppingmall.shoppingmall.dto.ItemSearchDto;
import com.eom.shoppingmall.shoppingmall.dto.MainItemDto;
import com.eom.shoppingmall.shoppingmall.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}
