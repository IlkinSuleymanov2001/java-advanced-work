package com.example.demo.mapper;

import com.example.demo.dao.entity.CardEntity;
import com.example.demo.dao.entity.CardOwnerEntity;
import com.example.demo.dao.entity.Travel;
import com.example.demo.model.request.TravelRequest;
import com.example.demo.model.request.TravelResponse;
import com.example.demo.model.request.card.CreateCardRequest;
import com.example.demo.model.response.CardResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface TravelMapper {

        Travel  toTravel(TravelRequest travelRequest);

        TravelResponse toTravelResponse(Travel travel);

}
