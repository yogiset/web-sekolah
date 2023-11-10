package com.sekolah.websekolah.controller;

import com.sekolah.websekolah.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController{

    private final UserService userService;

}
