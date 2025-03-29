package com.example.demo.model.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime ;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class UserResponse {

    Long id;
    String name;
    Integer age;
    String email;
    LocalDateTime  createdAt;
    LocalDateTime  updatedAt;
    String birthPlace;
    Boolean activeAccount;

}
