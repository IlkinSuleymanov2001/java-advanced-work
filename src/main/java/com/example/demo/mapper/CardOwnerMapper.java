package com.example.demo.mapper;

import com.example.demo.dao.entity.CardEntity;
import com.example.demo.dao.entity.CardOwnerEntity;
import com.example.demo.model.request.card.CardOwnerRequest;
import com.example.demo.model.request.card.CreateCardRequest;
import com.example.demo.model.response.CardOwnerResponse;
import com.example.demo.model.response.CardResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface CardOwnerMapper {

   CardOwnerEntity toCardOwnerEntity(CardOwnerRequest cardOwnerRequest);

    CardOwnerResponse toCardOwnerResponse(CardOwnerEntity cardOwnerEntity);
}
