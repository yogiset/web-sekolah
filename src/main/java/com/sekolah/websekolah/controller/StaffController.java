package com.sekolah.websekolah.controller;

import com.sekolah.websekolah.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/staff")
public class StaffController {

private final StaffService staffService;

}
