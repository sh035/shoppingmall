package com.eom.shoppingmall.shoppingmall.repository;

import com.eom.shoppingmall.shoppingmall.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {
    List<Item> findByName(String name);

    List<Item> findByNameOrContent(String name, String content);

    List<Item> findByPriceLessThan(Integer price);

    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    @Query(value = "select i from Item i where i.content like %:content% order by i.price desc", nativeQuery = true)
    List<Item> findByItemDetail(@Param("content") String content);
}
