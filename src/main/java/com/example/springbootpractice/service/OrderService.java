package com.example.springbootpractice.service;

import com.example.springbootpractice.domain.Delivery;
import com.example.springbootpractice.domain.Member;
import com.example.springbootpractice.domain.Order;
import com.example.springbootpractice.domain.OrderItem;
import com.example.springbootpractice.domain.item.Item;
import com.example.springbootpractice.repository.ItemRepository;
import com.example.springbootpractice.repository.MemberRepository;
import com.example.springbootpractice.repository.OrderRepository;
import com.example.springbootpractice.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = Delivery.create(member.getAddress());

        OrderItem orderItem = OrderItem.create(item, item.getPrice(), count);

        Order order = Order.create(member, delivery, orderItem);
        orderRepository.save(order);

        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAllByString(orderSearch);
    }
}
