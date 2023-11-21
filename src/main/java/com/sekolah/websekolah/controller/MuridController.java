package com.sekolah.websekolah.controller;


import com.sekolah.websekolah.entity.Berita;
import com.sekolah.websekolah.entity.Murid;
import com.sekolah.websekolah.exception.AllException;
import com.sekolah.websekolah.service.MuridService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Murid> showAllMurid(Map<String, String> requestMap) throws AllException {
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
    @GetMapping("/pagination/{offset}/{pageSize}")
    public ResponseEntity<List<Murid>> showAllMuridPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Murid> muridWithPagination = muridService.showAllMuridWithPagination(offset, pageSize);

        List<Murid> muridList = muridWithPagination.getContent();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(muridWithPagination.getTotalElements()));

        return new ResponseEntity<>(muridList, headers, HttpStatus.OK);
    }
    @GetMapping("/paginationwithascname/{offset}/{pageSize}")
    public ResponseEntity<List<Murid>> showAllMuridPaginationWithAscName(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Murid> muridWithPaginationAscName = muridService.showAllMuridWithPaginationAscName(offset, pageSize);

        List<Murid> muridList = muridWithPaginationAscName.getContent();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(muridWithPaginationAscName.getTotalElements()));

        return new ResponseEntity<>(muridList, headers, HttpStatus.OK);
    }
    @GetMapping("/paginationwithdescname/{offset}/{pageSize}")
    public ResponseEntity<List<Murid>> showAllMuridPaginationWithDescName(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Murid> muridWithPaginationDescName = muridService.showAllMuridWithPaginationDescName(offset, pageSize);

        List<Murid> muridList = muridWithPaginationDescName.getContent();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(muridWithPaginationDescName.getTotalElements()));

        return new ResponseEntity<>(muridList, headers, HttpStatus.OK);
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
