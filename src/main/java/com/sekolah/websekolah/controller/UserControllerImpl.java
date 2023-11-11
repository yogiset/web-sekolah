package com.sekolah.websekolah.controller;

import com.sekolah.websekolah.constant.ApiConstant;
import com.sekolah.websekolah.entity.User;
import com.sekolah.websekolah.exception.AllException;
import com.sekolah.websekolah.service.UserService;
import com.sekolah.websekolah.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController{

    private final UserService userService;

    @Override
    public ResponseEntity<String> createAccount(Map<String, String> requestMap) {
        try {
            return userService.createAccount(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return UserUtils.getResponseEntity(ApiConstant.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try {
            return userService.login(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return UserUtils.getResponseEntity(ApiConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public List<User> listUser() {

        return userService.listUser();

    }

    @Override
    public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
        try {
            return userService.forgotPassword(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return UserUtils.getResponseEntity(ApiConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap,String userEmail) {
        try {

            return userService.changePassword(requestMap,userEmail);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return UserUtils.getResponseEntity(ApiConstant.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public User fetchUserByName(String name,Map<String, String> requestMap) throws AllException {
        return userService.fetchUserByName(name,requestMap);
    }


    @Override
    public String deleteUserById(Long id,Map<String, String> requestMap) throws AllException {
        userService.deleteUserById(id,requestMap);
        return "Data Karyawan telah dihapus!!";
    }

    @Override
    public User updateUser(Long id, User user,Map<String, String> requestMap) throws AllException {
        return userService.updateUser(id,user,requestMap);
    }
}
