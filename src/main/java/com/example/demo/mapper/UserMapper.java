package com.example.demo.mapper;

import com.example.demo.dao.entity.UserEntity;
import com.example.demo.model.request.user.CreateUserRequest;
import com.example.demo.model.response.page.PageableResponse;
import com.example.demo.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toUser(CreateUserRequest createUserRequest);

    UserEntity toUser(UserResponse userResponse);

//    @Mapping(source = "createdAt", target = "createdAt")
//    @Mapping(source = "updatedAt", target = "updatedAt")
//    @Mapping(source = "age", target = "age")
//    @Mapping(source = "activeAccount", target = "activeAccount")
    UserResponse toUserResponse(UserEntity userEntity);

    UserEntity updateUser(CreateUserRequest createUserRequest, @MappingTarget UserEntity userEntity);


    default PageableResponse<UserResponse> toPageableUserResponse(Page<UserEntity> userEntityPage){

         PageableResponse<UserResponse> pageableResponse = new PageableResponse();
         pageableResponse.setTotalElements(userEntityPage.getTotalElements());
         pageableResponse.setData(userEntityPage.stream().map(this::toUserResponse).toList());
         pageableResponse.setHasNextPage(userEntityPage.hasNext());
         pageableResponse.setLastPageNumber(userEntityPage.getNumber());

         return pageableResponse;
     }

}
