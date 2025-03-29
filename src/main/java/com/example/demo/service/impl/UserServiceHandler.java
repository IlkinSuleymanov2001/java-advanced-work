package com.example.demo.service.impl;

import com.example.demo.dao.entity.UserEntity;
import com.example.demo.dao.repository.UserRepository;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.criteria.PageCriteria;
import com.example.demo.model.criteria.UserCriteria;
import com.example.demo.model.request.user.CreateUserRequest;
import com.example.demo.model.request.user.GetUserIdentifierRequest;
import com.example.demo.model.request.user.UpdateUserRequest;
import com.example.demo.model.response.UserResponse;
import com.example.demo.model.response.page.PageableResponse;
import com.example.demo.service.inter.UserService;
import com.example.demo.service.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.demo.exception.util.ExceptionUtil.EXCEPTION;
import static com.example.demo.model.enums.ErrorInfo.USER_ID_AND_EMAIL_ERROR;
import static com.example.demo.model.enums.ErrorInfo.USER_NOT_FOUND;
import static lombok.AccessLevel.PRIVATE;


@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserServiceHandler implements UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    @Override
    public UserResponse createUser(CreateUserRequest createUserRequest) {
        UserEntity userEntity = userMapper.toUser(createUserRequest);

        userRepository.save(userEntity);
        return userMapper.toUserResponse(userEntity);
    }

    @Override
    public PageableResponse<UserResponse> getAllUsers(PageCriteria pageCriteria, UserCriteria userCriteria) {

        PageRequest pageable = PageRequest.of(pageCriteria.getPage(),
                pageCriteria.getCount(),
                Sort.by("id").descending());
        Page<UserEntity> filteredUsers = userRepository.findAll(new UserSpecification(userCriteria), pageable);
        return userMapper.toPageableUserResponse(filteredUsers);
    }

    @Override
    public UserResponse getUser(GetUserIdentifierRequest userIdentity) {

        UserEntity user = findUser(userIdentity);
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserEntity findUser(GetUserIdentifierRequest userIdentity) {
        UserEntity userEntity = null;
        if (userIdentity.getUserId() != null) {
            userEntity = fetchUserById(userIdentity.getUserId());

        } else if (userIdentity.getUserEmail() != null) {
            userEntity = userRepository.findUserByEmail(userIdentity.getUserEmail())
                    .orElseThrow(() -> EXCEPTION.notFoundThrowable(USER_NOT_FOUND));
        } else {
            EXCEPTION.notFound(USER_ID_AND_EMAIL_ERROR);
        }
        return userEntity;
    }


    @Override
    public List<UserResponse> getUsersByName(String name) {
        return userRepository.findAllByName(name).
                stream().
                map(userMapper::toUserResponse).
                toList();
    }

    @Override
    public void deleteAllDeActiveUsers() {
        List<UserEntity> allByActiveAccountIsFalse = userRepository.findAllByActiveAccountIsFalse();
        userRepository.deleteAll(allByActiveAccountIsFalse);
    }


    @Transactional
    @Override
    public UserResponse updateUser(UpdateUserRequest updateUserRequest) {

        UserEntity userEntity = fetchUserById(updateUserRequest.getUserId());
        userEntity.setActiveAccount(updateUserRequest.getStatus());

        return userMapper.toUserResponse(userEntity);
    }

    private UserEntity fetchUserById(Long id) {
        return userRepository.findUserById(id)
                .orElseThrow(() -> EXCEPTION.notFoundThrowable(USER_NOT_FOUND));
    }


}
