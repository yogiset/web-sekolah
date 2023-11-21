package com.sekolah.websekolah.controller;


import com.sekolah.websekolah.entity.Berita;
import com.sekolah.websekolah.exception.AllException;
import com.sekolah.websekolah.service.BeritaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Berita> showAllBerita(Map<String, String> requestMap) throws AllException {
        return beritaService.showAllBerita(requestMap);
    }

    @GetMapping("/sortAsc/{field}")
    public List<Berita> showAllBeritaByAsc(@PathVariable String field) throws AllException {
        return beritaService.showAllBeritaAscending(field);
    }
    @GetMapping("/sortDsc/{field}")
    public List<Berita> showAllBeritaByDsc(@PathVariable String field) throws AllException {
        return beritaService.showAllBeritaDescending(field);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public ResponseEntity<List<Berita>> showAllBeritaPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Berita> beritaWithPagination = beritaService.showAllBeritaWithPagination(offset, pageSize);

        List<Berita> beritaList = beritaWithPagination.getContent();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(beritaWithPagination.getTotalElements()));

        return new ResponseEntity<>(beritaList, headers, HttpStatus.OK);
    }
    @GetMapping("/paginationascjudul/{offset}/{pageSize}")
    public ResponseEntity<List<Berita>> showAllBeritaPaginationAndSortAscbyJudul(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Berita> beritaWithPaginationAscbyJudul = beritaService.showAllBeritaWithPaginationAscJudul(offset, pageSize);

        List<Berita> beritaList = beritaWithPaginationAscbyJudul.getContent();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(beritaWithPaginationAscbyJudul.getTotalElements()));

        return new ResponseEntity<>(beritaList, headers, HttpStatus.OK);
    }
    @GetMapping("/paginationdescjudul/{offset}/{pageSize}")
    public ResponseEntity<List<Berita>> showAllBeritaPaginationAndSortDescbyJudul(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Berita> beritaWithPaginationDescbyJudul = beritaService.showAllBeritaWithPaginationDescJudul(offset, pageSize);

        List<Berita> beritaList = beritaWithPaginationDescbyJudul.getContent();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(beritaWithPaginationDescbyJudul.getTotalElements()));

        return new ResponseEntity<>(beritaList, headers, HttpStatus.OK);
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
