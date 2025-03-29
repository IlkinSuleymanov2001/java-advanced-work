package com.example.demo.service.inter;

import com.example.demo.model.request.card.CardRequest;
import com.example.demo.model.request.order.CreateOrderRequest;
import com.example.demo.model.response.CardResponse;
import com.example.demo.model.response.CreatedCardResponse;
import com.example.demo.model.response.OrderResponse;

import java.util.List;

public interface CardService {

   CreatedCardResponse createCard(CardRequest cardRequest, Long ownerId);
   List<CardResponse>  getAllCardsByOwnerId(Long ownerId);

}
