package com.sekolah.websekolah.controller;


import com.sekolah.websekolah.entity.Berita;
import com.sekolah.websekolah.entity.Murid;
import com.sekolah.websekolah.exception.AllException;
import com.sekolah.websekolah.service.MuridService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/murid")
public class MuridController {
    private final MuridService muridService;

    @PostMapping("/add")
    public Murid addMurid(@RequestBody Murid murid, Map<String, String> requestMap) throws AllException {
        return muridService.addMurid(murid,requestMap);
    }

    @GetMapping("/all")
    public List<Murid> showAllMurid(@RequestBody Map<String, String> requestMap) throws AllException {
        return muridService.showAllMurid(requestMap);
    }

    @GetMapping("/sortAsc/{field}")
    public List<Murid> showAllMuridByAsc(@PathVariable String field) throws AllException {
        return muridService.showAllMuridAscending(field);
    }
    @GetMapping("/sortDsc/{field}")
    public List<Murid> showAllMuridByDsc(@PathVariable String field) throws AllException {
        return muridService.showAllMuridDescending(field);
    }

    @GetMapping("/cari/{nama}")
    public Murid fetchMuridByNama(@PathVariable("nama") String nama,@RequestBody Map<String, String> requestMap) throws AllException {
        return muridService.fetchMuridByNama(nama,requestMap);
    }

    @GetMapping("/cari/{nis}")
    public Murid fetchMuridByNis(@PathVariable("nis") String nis, @RequestBody Map<String, String> requestMap) throws AllException {
        return muridService.fetchMuridByNis(nis,requestMap);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteMuridById(@PathVariable("id") Long id,@RequestBody Map<String, String> requestMap) throws AllException {
        muridService.deleteMuridById(id,requestMap);
        return "Murid telah dihapus!!";
    }

    @PutMapping("/update/{id}")
    public Murid updateMurid(@PathVariable("id") Long id,@RequestBody Murid murid,Map<String, String> requestMap) throws AllException {
        return muridService.updateMurid(id,murid,requestMap);
    }
}
