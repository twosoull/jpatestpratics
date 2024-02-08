package com.jpatestpratics.jpatestpratics.controller;

import com.jpatestpratics.jpatestpratics.domain.Item;
import com.jpatestpratics.jpatestpratics.domain.Member;
import com.jpatestpratics.jpatestpratics.domain.Order;
import com.jpatestpratics.jpatestpratics.form.OrderForm;
import com.jpatestpratics.jpatestpratics.form.OrderSearch;
import com.jpatestpratics.jpatestpratics.service.ItemService;
import com.jpatestpratics.jpatestpratics.service.MemberService;
import com.jpatestpratics.jpatestpratics.service.OrderService;
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
public class OrderController {

    private final MemberService memberService;
    private final ItemService itemService;
    private final OrderService orderService;

    @GetMapping("/order")
    public String createForm(Model model){
        log.info("create form");
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members",members);
        model.addAttribute("items",items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String create(OrderForm form , BindingResult result, Model model){
        log.info("create");
        if(result.hasErrors()){return "redirect:/";}

        try{
            orderService.orderItem(form);
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/";
        }
        return "redirect:/";
    }

    @GetMapping("/orders")
    public String list(Model model, OrderSearch orderSearch){
        log.info("list");

        List<Order> orders = orderService.findOrders();
        model.addAttribute("orders", orders);
        model.addAttribute("orderSearch", orderSearch);

        return "order/orderList";
    }

    @PostMapping("/orders/{id}/cancel")
    public String update(@PathVariable("id") Long id){
        log.info("update");

        orderService.cancelOrder(id);

        return "redirect:/orders";
    }

}
