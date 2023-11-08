package com.eom.shoppingmall.shoppingmall.service;

import com.eom.shoppingmall.shoppingmall.dto.CartItemDto;
import com.eom.shoppingmall.shoppingmall.entity.CartItem;
import com.eom.shoppingmall.shoppingmall.entity.Item;
import com.eom.shoppingmall.shoppingmall.entity.Member;
import com.eom.shoppingmall.shoppingmall.enums.ItemSellStatus;
import com.eom.shoppingmall.shoppingmall.repository.CartItemRepository;
import com.eom.shoppingmall.shoppingmall.repository.ItemRepository;
import com.eom.shoppingmall.shoppingmall.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CartServiceTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CartService cartService;

    @Autowired
    CartItemRepository cartItemRepository;

    public Item saveItem() {
        Item item = new Item();
        item.setName("테스트 상품");
        item.setPrice(10000);
        item.setContent("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStock(100);
        return itemRepository.save(item);
    }

    public Member saveMember() {
        Member member = new Member();
        member.setEmail("test@test.com");

        return memberRepository.save(member);
    }

    @Test
    @DisplayName("장바구니 담기 테스트")
    public void addCart() {
        Item item = saveItem();
        Member member = saveMember();

        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setCount(5);
        cartItemDto.setItemId(item.getId());

        Long cartItemId = cartService.addCart(cartItemDto, member.getEmail());

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);

        assertEquals(item.getId(), cartItem.getItem().getId());
        assertEquals(cartItemDto.getCount(), cartItem.getCount());
    }

}