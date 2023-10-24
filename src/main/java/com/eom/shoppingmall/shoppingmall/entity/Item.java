package com.eom.shoppingmall.shoppingmall.entity;

import com.eom.shoppingmall.shoppingmall.enums.ItemSellStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Table(name = "item")
@Getter
@Entity
public class Item extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stock;

    @Lob
    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;

    @Builder
    public Item(String name, int price, int stock, String content, ItemSellStatus itemSellStatus) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.content = content;
        this.itemSellStatus = itemSellStatus;
    }
}
