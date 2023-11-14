package com.sekolah.websekolah.controller;

import com.sekolah.websekolah.constant.ApiConstant;
import com.sekolah.websekolah.entity.User;
import com.sekolah.websekolah.exception.AllException;
import com.sekolah.websekolah.service.UserService;
import com.sekolah.websekolah.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
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
    public List<User> listUser(Map<String,String> requestMap) throws AllException {

        return userService.listUser(requestMap);

    }
    @Override
    public List<User> showAllUserByAsc(String field) throws AllException {
        return userService.showAllUserByAsccending(field);
    }

    @Override
    public List<User> showAllUserByDsc(String field) throws AllException {
        return userService.showAllUserByDescending(field);
    }

    @Override
    public ResponseEntity<List<User>> showAllProductPagination(int offset, int pageSize) {
        Page<User> userWithPagination = userService.showAllUserWithPagination(offset, pageSize);

        List<User> userList = userWithPagination.getContent();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(userWithPagination.getTotalElements()));

        return new ResponseEntity<>(userList, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<User>> showAllProductPaginationAndSortAscbyUsername(int offset, int pageSize) {
        Page<User> userWithPaginationAscUsername = userService.showAllUserWithPaginationAscUsername(offset, pageSize);

        List<User> userList = userWithPaginationAscUsername.getContent();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(userWithPaginationAscUsername.getTotalElements()));

        return new ResponseEntity<>(userList, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<User>> showAllProductPaginationAndSortDescbyUsername(int offset, int pageSize) {
        Page<User> userWithPaginationDescUsername = userService.showAllUserWithPaginationDescUsername(offset, pageSize);

        List<User> userList = userWithPaginationDescUsername.getContent();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(userWithPaginationDescUsername.getTotalElements()));

        return new ResponseEntity<>(userList, headers, HttpStatus.OK);
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
