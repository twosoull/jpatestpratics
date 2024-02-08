package com.jpatestpratics.jpatestpratics.service;

import com.jpatestpratics.jpatestpratics.domain.*;
import com.jpatestpratics.jpatestpratics.form.OrderForm;
import com.jpatestpratics.jpatestpratics.repository.ItemRepository;
import com.jpatestpratics.jpatestpratics.repository.MemberRepository;
import com.jpatestpratics.jpatestpratics.repository.OrderItemRepository;
import com.jpatestpratics.jpatestpratics.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional(rollbackFor = Exception.class)
    public void orderItem(OrderForm form){

                //1.order에 member 넣으며 생성 delivery ORDER로 넣으며 생성
                Member member = memberRepository.findOne(form.getMemberId());
                Order order = new Order();
                order.setOrderStatus(Status.ORDER);
                order.addMember(member);
                orderRepository.save(order);

                //2 orderItem에 order와 item 넣으며 생성
                // item에 stockQuantity에 count 값을 빼고 0 이하가 아닌지 체크, 동시에 값 빼줄 것
                Item item = itemRepository.find(form.getItemId());
                item.orderStock(item , form.getCount());

                //오류 나면 Exception 처리
                OrderItem orderItem = new OrderItem();
                orderItem.setCount(form.getCount());
                Long price = item.getPrice() * form.getCount();
                orderItem.setOrderPrice(price);
                orderItem.addOrder(order);
                orderItem.addItem(item);
                orderItemRepository.save(orderItem);
    }

    public List<Order> findOrders(){
        return orderRepository.findAll();
    }

    @Transactional
    public void cancelOrder(Long id){
        Order order = orderRepository.find(id);
        order.setOrderStatus(Status.CANCLE);
    }
}
