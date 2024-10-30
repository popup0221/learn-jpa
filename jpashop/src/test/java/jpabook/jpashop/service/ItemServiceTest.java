package jpabook.jpashop.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jpabook.jpashop.domain.item.Book; // Book 클래스를 import
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.ItemRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    private Book item; // Book 타입으로 변경

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        item = new Book(); // Book 인스턴스 생성
        item.setName("Test Book");
        item.setPrice(100);
        item.setStockQuantity(10);
    }

    @Test
    public void saveItem() {
        // given
        when(itemRepository.save(any(Book.class))).thenReturn(item);

        // when
        itemService.saveItem(item);

        // then
        verify(itemRepository, times(1)).save(item);
    }

    @Test
    public void findItems() {
        // given
        when(itemRepository.findAll()).thenReturn(Collections.singletonList(item));

        // when
        List<Book> foundItems = itemService.finItems();

        // then
        assertThat(foundItems).containsExactly(item);
    }

    @Test
    public void findOne() {
        // given
        Long itemId = 1L;
        when(itemRepository.findOne(itemId)).thenReturn(item);

        // when
        Book foundItem = (Book) itemService.findOne(itemId); // 캐스팅 필요

        // then
        assertThat(foundItem).isEqualTo(item);
    }

    @Test
    public void removeStock_notEnoughStock() {
        // given
        item.setStockQuantity(5);
        
        // when & then
        assertThatThrownBy(() -> item.removeStock(10))
            .isInstanceOf(NotEnoughStockException.class)
            .hasMessageContaining("need more stock");
    }

    @Test
    public void addStock() {
        // given
        item.setStockQuantity(5);
        
        // when
        item.addStock(3);

        // then
        assertThat(item.getStockQuantity()).isEqualTo(8);
    }
}
