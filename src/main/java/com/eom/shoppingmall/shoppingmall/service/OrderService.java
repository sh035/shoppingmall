package com.eom.shoppingmall.shoppingmall.service;

import com.eom.shoppingmall.shoppingmall.dto.OrderDto;
import com.eom.shoppingmall.shoppingmall.entity.Item;
import com.eom.shoppingmall.shoppingmall.entity.Member;
import com.eom.shoppingmall.shoppingmall.entity.Order;
import com.eom.shoppingmall.shoppingmall.entity.OrderItem;
import com.eom.shoppingmall.shoppingmall.repository.ItemRepository;
import com.eom.shoppingmall.shoppingmall.repository.MemberRepository;
import com.eom.shoppingmall.shoppingmall.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    public Long Order(OrderDto orderDto, String email) {
        Item item = itemRepository.findById(orderDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByEmail(email);

        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
        orderItemList.add(orderItem);

        Order order = Order.createOrder(member, orderItemList);
        orderRepository.save(order);

        return order.getId();
    }
}
