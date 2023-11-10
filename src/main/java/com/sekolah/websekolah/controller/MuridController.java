package com.sekolah.websekolah.controller;

import com.sekolah.websekolah.service.MuridService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/murid")
public class MuridController {
    private final MuridService muridService;

}
