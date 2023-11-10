package com.sekolah.websekolah.controller;

import com.sekolah.websekolah.service.AgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/agenda")
public class AgendaController {
    private final AgendaService agendaService;


}
