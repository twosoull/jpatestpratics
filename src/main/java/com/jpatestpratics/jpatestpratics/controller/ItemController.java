package com.jpatestpratics.jpatestpratics.controller;

import com.jpatestpratics.jpatestpratics.domain.Book;
import com.jpatestpratics.jpatestpratics.domain.Item;
import com.jpatestpratics.jpatestpratics.domain.Movie;
import com.jpatestpratics.jpatestpratics.form.ItemForm;
import com.jpatestpratics.jpatestpratics.service.ItemService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items")
    public String list(Model model){
        log.info("list");

        List<Item> items = itemService.findItems();

        model.addAttribute("items",items);

        return "items/itemList";
    }

    @GetMapping("/items/{id}/edit")
    public String editForm(@PathVariable(name = "id") Long id, Model model){
        log.info("editForm");

        Book item = (Book)itemService.findItem(id);
        ItemForm form = new ItemForm();
        form.setId(item.getId());
        form.setAuthor(item.getAuthor());
        form.setName(item.getName());
        form.setIsbn(item.getIsbn());
        form.setStockQuantity(item.getStockQuantity());
        form.setPrice(item.getPrice());

        model.addAttribute("form", form);

        return "items/updateItemForm";
    }

    @PostMapping("/items/{id}/edit")
    public String edit(ItemForm itemForm, BindingResult result){
        log.info("edit");

        Book book = new Book();
        book.setId(itemForm.getId());
        book.setName(itemForm.getName());
        book.setStockQuantity(itemForm.getStockQuantity());
        book.setPrice(itemForm.getPrice());
        book.setAuthor(itemForm.getAuthor());
        book.setIsbn(itemForm.getIsbn());

        itemService.editItem(book);

        return "redirect:/items";
    }

    @GetMapping("/items/new")
    public String createForm(Model model){
        log.info("createForm");
        model.addAttribute("form",new ItemForm());

        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(ItemForm itemform, BindingResult result){
        log.info("create");

        Book book = new Book();
        book.setAuthor(itemform.getAuthor());
        book.setIsbn(itemform.getIsbn());
        book.setPrice(itemform.getPrice());
        book.setStockQuantity(itemform.getStockQuantity());
        book.setName(itemform.getName());

        itemService.saveItem(book);

        return "redirect:/";
    }

}
