package com.example.demo.controller;


import com.example.demo.exception.response.ResponseModel;
import com.example.demo.model.request.OrderTicket;
import com.example.demo.model.request.card.CardRequest;
import com.example.demo.model.request.order.CreateOrderRequest;
import com.example.demo.model.response.CardResponse;
import com.example.demo.model.response.CreatedCardResponse;
import com.example.demo.model.response.OrderResponse;
import com.example.demo.service.impl.TicketServiceHandler;
import com.example.demo.service.inter.CardService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("v1/ticket")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class TicketController {

    TicketServiceHandler ticketService;


    @PostMapping("/order-ticket")
    public Object  orderTicket(@RequestBody  OrderTicket orderTicket) {

        return  ticketService.orderTicket(orderTicket);
    }



}
