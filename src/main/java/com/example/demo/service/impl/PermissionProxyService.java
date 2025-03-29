package com.example.demo.service.impl;


import com.example.demo.client.PermissionClient;
import com.example.demo.permission_ms.model.CheckPermissionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service(value = "permissionProxyService")
@RequiredArgsConstructor
public class PermissionProxyService {

    private final PermissionClient permissionClient;

    public boolean  checkPermission(String userId,String permissionName,String productName ) {

        boolean result;
        try {
            result= permissionClient
                    .checkPermission(new CheckPermissionDto(userId, productName,permissionName));
        }catch (Exception e){
            return false;
        }

        return result;
    }


}
