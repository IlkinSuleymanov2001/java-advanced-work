package com.example.demo.mapper;

import com.example.demo.dao.entity.*;
import com.example.demo.model.request.CreatedTicketResponse;
import com.example.demo.model.request.card.CreateCardRequest;
import com.example.demo.model.response.CardResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface TicketMapper {


    // Create a new Ticket using Guest and Travel objects
    @Mapping(target = "guest", source = "guest")
    @Mapping(target = "travel", source = "travel")
    @Mapping(target = "id", ignore = true)
    Ticket createTicket(Guest guest, Travel travel);



}
