package com.example.demo.controller;


import com.example.demo.exception.response.ResponseModel;
import com.example.demo.model.request.TravelRequest;
import com.example.demo.model.request.order.CreateOrderRequest;
import com.example.demo.model.response.OrderResponse;
import com.example.demo.service.impl.TravelServiceHandler;
import com.example.demo.service.inter.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("v1/travel")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class TravelController {

    TravelServiceHandler travelServiceHandler;

    @PostMapping("/create")
    public Object  createOrder(@RequestBody TravelRequest travelRequest) {
       return  travelServiceHandler.createTravel(travelRequest);
    }


}
