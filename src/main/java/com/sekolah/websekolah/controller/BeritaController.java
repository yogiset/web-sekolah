package com.sekolah.websekolah.controller;

import com.sekolah.websekolah.service.BeritaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/berita")
public class BeritaController {

    private final BeritaService beritaService;
}
