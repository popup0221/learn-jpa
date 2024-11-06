package jpabook.jpashop.domain.item;

import jpabook.jpashop.exception.NotEnoughStockException;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;


public class ItemTest {
    private Item item;

    @BeforeEach
    void setUp() {
        item = new Item() {
            // Abstract class `Item` requires a concrete subclass for instantiation
        };
        item.setStockQuantity(10);  // 초기 재고를 10으로 설정
    }

    @Test
    public void removeStock_success() {
        // given
        int removeQuantity = 5;

        // when
        item.removeStock(removeQuantity);

        // then
        assertEquals(5, item.getStockQuantity(), "재고가 올바르게 감소해야 합니다.");
    }

    @Test(expected = NotEnoughStockException.class)
    public void removeStock_notEnoughStockException() {
        // given
        int removeQuantity = 15;

        // when
        item.removeStock(removeQuantity);

        // then
        fail("재고가 부족하면 NotEnoughStockException이 발생해야 합니다.");
    }
}