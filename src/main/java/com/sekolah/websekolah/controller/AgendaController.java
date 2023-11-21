package com.sekolah.websekolah.controller;

import com.sekolah.websekolah.entity.Agenda;
import com.sekolah.websekolah.exception.AllException;
import com.sekolah.websekolah.service.AgendaService;
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
@RequestMapping(path = "/agenda")
public class AgendaController {
    private final AgendaService agendaService;

    @PostMapping("/add")
    public Agenda addAgenda(@RequestBody Agenda agenda, Map<String, String> requestMap) throws AllException {
        return agendaService.addAgenda(agenda,requestMap);
    }

    @GetMapping("/all")
    public List<Agenda> showAllAgenda(Map<String, String> requestMap) throws AllException {
        return agendaService.showAllAgenda(requestMap);
    }
    @GetMapping("/sortAsc/{field}")
    public List<Agenda> showAllAgendaByAsc(@PathVariable String field) throws AllException {
        return agendaService.showAllAgendaAscending(field);
    }
    @GetMapping("/sortDsc/{field}")
    public List<Agenda> showAllAgendaByDsc(@PathVariable String field) throws AllException {
        return agendaService.showAllAgendaDescending(field);
    }
    @GetMapping("/pagination/{offset}/{pageSize}")
    public ResponseEntity<List<Agenda>> showAllAgendaPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Agenda> agendaWithPagination = agendaService.showAllAgendaWithPagination(offset, pageSize);

        List<Agenda> agendaList = agendaWithPagination.getContent();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(agendaWithPagination.getTotalElements()));

        return new ResponseEntity<>(agendaList, headers, HttpStatus.OK);
    }
    @GetMapping("/paginationwithascjudul/{offset}/{pageSize}")
    public ResponseEntity<List<Agenda>> showAllAgendaPaginationWithAscJudul(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Agenda> agendaWithPaginationAscJudul = agendaService.showAllAgendaWithPaginationAscJudul(offset, pageSize);

        List<Agenda> agendaList = agendaWithPaginationAscJudul.getContent();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(agendaWithPaginationAscJudul.getTotalElements()));

        return new ResponseEntity<>(agendaList, headers, HttpStatus.OK);
    }
    @GetMapping("/paginationwithdescjudul/{offset}/{pageSize}")
    public ResponseEntity<List<Agenda>> showAllAgendaPaginationWithDescJudul(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Agenda> agendaWithPaginationDescJudul = agendaService.showAllAgendaWithPaginationDescJudul(offset, pageSize);

        List<Agenda> agendaList = agendaWithPaginationDescJudul.getContent();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(agendaWithPaginationDescJudul.getTotalElements()));

        return new ResponseEntity<>(agendaList, headers, HttpStatus.OK);
    }

    @GetMapping("/carijudul/{judul}")
    public Agenda fetchAgendaByJudul(@PathVariable("judul") String judul,Map<String, String> requestMap) throws AllException {
        return agendaService.fetchAgendaByJudul(judul,requestMap);
    }

    @GetMapping("/caritanggal/{tanggals}")
    public Agenda fetchAgendaByTanggal(@PathVariable("tanggals")LocalDate tanggals,Map<String, String> requestMap) throws AllException {
        return agendaService.fetchAgendaByTanggal(tanggals,requestMap);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAgendaById(@PathVariable("id") Long id,Map<String, String> requestMap) throws AllException {
        agendaService.deleteAgendaById(id,requestMap);
        return "Agenda telah dihapus!!";
    }

    @PutMapping("/update/{id}")
    public Agenda updateAgenda(@PathVariable("id") Long id,@RequestBody Agenda agenda,Map<String, String> requestMap) throws AllException {
        return agendaService.updateAgenda(id,agenda,requestMap);
    }
}
