package com.sekolah.websekolah.controller;


import com.sekolah.websekolah.entity.Berita;
import com.sekolah.websekolah.exception.AllException;
import com.sekolah.websekolah.service.BeritaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/berita")
public class BeritaController {

    private final BeritaService beritaService;

    @PostMapping("/add")
    public Berita addBerita(@RequestBody Berita berita, Map<String, String> requestMap) throws AllException {
        return beritaService.addBerita(berita,requestMap);
    }

    @GetMapping("/all")
    public List<Berita> showAllBerita(@RequestBody Map<String, String> requestMap) throws AllException {
        return beritaService.showAllBerita(requestMap);
    }

    @GetMapping("/cari/{judul}")
    public Berita fetchBeritaByJudul(@PathVariable("judul") String judul,@RequestBody Map<String, String> requestMap) throws AllException {
        return beritaService.fetchBeritaByJudul(judul,requestMap);
    }

    @GetMapping("/cari/{tanggals}")
    public Berita fetchBeritaByTanggal(@PathVariable("tanggals") LocalDate tanggals, @RequestBody Map<String, String> requestMap) throws AllException {
        return beritaService.fetchBeritaByTanggal(tanggals,requestMap);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBeritaById(@PathVariable("id") Long id,@RequestBody Map<String, String> requestMap) throws AllException {
        beritaService.deleteBeritaById(id,requestMap);
        return "Berita telah dihapus!!";
    }

    @PutMapping("/update/{id}")
    public Berita updateBerita(@PathVariable("id") Long id,@RequestBody Berita berita,Map<String, String> requestMap) throws AllException {
        return beritaService.updateBerita(id,berita,requestMap);
    }
}
