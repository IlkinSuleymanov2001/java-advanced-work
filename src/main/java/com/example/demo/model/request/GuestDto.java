package com.example.demo.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;


@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class GuestDto {


    String legalId;

    String name;

    String email;

    String phoneNumber;

    Integer  countryCode;





}
