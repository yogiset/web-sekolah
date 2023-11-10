package com.sekolah.websekolah.service;

import com.sekolah.websekolah.repository.MuridRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MuridService {
    private final MuridRepository muridRepository;

}
