package com.eom.shoppingmall.shoppingmall.entity;

import com.eom.shoppingmall.shoppingmall.dto.ItemFormDto;
import com.eom.shoppingmall.shoppingmall.enums.ItemSellStatus;
import com.eom.shoppingmall.shoppingmall.exception.OutOfStockException;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@ToString
@Table(name = "item")
@Getter
@Setter
@Entity
public class Item extends BaseEntity{

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

    public void updateItem(ItemFormDto itemFormDto) {
        this.name = itemFormDto.getName();
        this.price = itemFormDto.getPrice();
        this.stock = itemFormDto.getStock();
        this.content = itemFormDto.getContent();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }

    public void removeStock(int stock) {
        int restStock = this.stock - stock;
        if (restStock < 0) {
            throw new OutOfStockException("상품의 재고가 부족합니다. (현재 재고 수량: " + this.stock + ")");
        }
        this.stock = restStock;
    }

}
