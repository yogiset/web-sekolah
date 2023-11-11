package com.sekolah.websekolah.controller;

import com.sekolah.websekolah.entity.User;
import com.sekolah.websekolah.exception.AllException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/user")
public interface UserController {

    @PostMapping(path = "/createaccount")
    public ResponseEntity<String> createAccount(@RequestBody(required = true) Map<String,String> requestMap);
    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody(required = true) Map<String,String> requestMap);

    @GetMapping(path = "/all")
    public List<User> listUser();

    @PostMapping(path = "/forgotpassword")
    ResponseEntity<String>forgotPassword(@RequestBody Map<String,String> requestMap);

    @PostMapping("/changepassword")
    ResponseEntity<String> changePassword(@RequestBody Map<String, String> requestMap,String userEmail);

    @GetMapping("/cari/{name}")
    public User fetchUserByName(@PathVariable("name") String name,@RequestBody Map<String, String> requestMap) throws AllException;

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable("id") Long id,@RequestBody Map<String, String> requestMap) throws AllException;

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable("id") Long id,
                                   @RequestBody User user,Map<String, String> requestMap) throws AllException;

}
