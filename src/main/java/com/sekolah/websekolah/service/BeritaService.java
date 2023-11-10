package com.sekolah.websekolah.service;

import com.sekolah.websekolah.repository.BeritaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BeritaService {
    private final BeritaRepository beritaRepository;

}
