package com.eom.shoppingmall.shoppingmall.repository;

import com.eom.shoppingmall.shoppingmall.entity.Item;
import com.eom.shoppingmall.shoppingmall.entity.QItem;
import com.eom.shoppingmall.shoppingmall.enums.ItemSellStatus;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@SpringBootTest
@Transactional
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @PersistenceContext
    EntityManager em;

    public void createItemList() {
        for (int i = 1; i <= 10; i++) {
            Item item = Item.builder()
                    .name("테스트상품" + i)
                    .price(10000 + i)
                    .content("테스트 상품 상세 설명" + i)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .stock(100 + i)
                    .build();
            Item savedItem = itemRepository.save(item);
        }
    }

    public void createItemList2() {
        for (int i = 1; i <= 5; i++) {
            Item item = Item.builder()
                    .name("테스트상품" + i)
                    .price(10000 + i)
                    .content("테스트 상품 상세 설명" + i)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .stock(100 + i)
                    .build();
            itemRepository.save(item);
        }

        for (int i = 6; i <= 10; i++) {
            Item item = Item.builder()
                    .name("테스트상품" + i)
                    .price(10000 + i)
                    .content("테스트 상품 상세 설명" + i)
                    .itemSellStatus(ItemSellStatus.SOLD_OUT)
                    .stock(0)
                    .build();
            itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest() {
        Item item = Item.builder()
                .name("테스트상품")
                .price(10000)
                .content("테스트 상품 상세 설명")
                .itemSellStatus(ItemSellStatus.SELL)
                .stock(100)
                .build();
        Item savedItem = itemRepository.save(item);
        System.out.println(savedItem.toString());
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByNameTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByName("테스트 상품1");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("상품명, 상품상세설명 or 테스트")
    public void findByNameOrContentTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByNameOrContent("테스트 상품1,", "테스트 상품 상세 설명5");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("가격 LessThan 테스트")
    public void findByPriceLessThanTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThan(10005);
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("가격 내림차순 조회 테스트")
    public void findByPriceLessThanOrderByPriceDesc() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }

//    @Test
//    @DisplayName("@Query를 이용한 상품 조회 테스트")
//    public void findByItemDetailTest() {
//        this.createItemList();
//        List<Item> itemList = itemRepository.findByItemDetail("테스트 상품 상세 설명");
//        for (Item item : itemList) {
//            System.out.println(item.toString());
//        }
//    }

    @Test
    @DisplayName("Querydsl 조회 테스트1")
    public void queryDslTest() {
        this.createItemList();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QItem qItem = QItem.item;
        JPAQuery<Item> query = queryFactory.selectFrom(qItem)
                .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(qItem.content.like("%" + "테스트 상품 상세 설명" + "%"))
                .orderBy(qItem.price.desc());

        List<Item> itemList = query.fetch();

        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("상품 querydsl 조회 테스트2")
    public void queryDslTest2() {
        this.createItemList2();

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QItem item = QItem.item;
        String content = "테스트 상품 상세 설명";
        int price = 10000;
        String itemSellStat = "SELL";

        booleanBuilder.and(item.content.like("%" + content + "%"));
        booleanBuilder.and(item.price.gt(price));

        if (StringUtils.equals(itemSellStat, ItemSellStatus.SELL)) {
            booleanBuilder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
        }

        Pageable pageable = PageRequest.of(0, 5);
        Page<Item> itemPagingResult = itemRepository.findAll(booleanBuilder, pageable);
        System.out.println("total elements = " + itemPagingResult.getTotalElements());

        List<Item> resultItemList = itemPagingResult.getContent();
        for (Item resultItem : resultItemList) {
            System.out.println(resultItem.toString());
        }
    }
}