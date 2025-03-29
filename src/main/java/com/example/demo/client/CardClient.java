package com.example.demo.client;


import com.example.demo.client.decoder.CustomErrorDecoder;
import com.example.demo.exception.response.ResponseModel;
import com.example.demo.model.client.CardOwnerResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-card-owner", url = "http://localhost:8080",
        configuration = CustomErrorDecoder.class)
public interface CardClient {

    @GetMapping("internal/v1/card/owner/{id}")
    ResponseModel<CardOwnerResponseDto> getCardOwner(@PathVariable Long id);

}
