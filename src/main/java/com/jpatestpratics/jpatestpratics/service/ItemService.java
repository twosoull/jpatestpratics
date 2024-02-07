package com.jpatestpratics.jpatestpratics.service;

import com.jpatestpratics.jpatestpratics.domain.Book;
import com.jpatestpratics.jpatestpratics.domain.Item;
import com.jpatestpratics.jpatestpratics.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();

    }

    @Transactional
    public void editItem(Book item) {
        Book findItem = (Book)itemRepository.find(item.getId());

        findItem.setName(item.getName());
        findItem.setPrice(item.getPrice());
        findItem.setStockQuantity(item.getStockQuantity());
        findItem.setIsbn(item.getIsbn());
        findItem.setAuthor(item.getAuthor());

        System.out.println("확인");
    }

    public Item findItem(Long id) {
        return itemRepository.find(id);

    }
}
