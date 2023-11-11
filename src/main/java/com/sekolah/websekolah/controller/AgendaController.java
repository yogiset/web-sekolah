package com.sekolah.websekolah.controller;

import com.sekolah.websekolah.entity.Agenda;
import com.sekolah.websekolah.exception.AllException;
import com.sekolah.websekolah.service.AgendaService;
import lombok.RequiredArgsConstructor;
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
    public List<Agenda> showAllAgenda(@RequestBody Map<String, String> requestMap) throws AllException {
        return agendaService.showAllAgenda(requestMap);
    }

    @GetMapping("/cari/{judul}")
    public Agenda fetchAgendaByJudul(@PathVariable("judul") String judul,@RequestBody Map<String, String> requestMap) throws AllException {
        return agendaService.fetchAgendaByJudul(judul,requestMap);
    }

    @GetMapping("/cari/{tanggals}")
    public Agenda fetchAgendaByTanggal(@PathVariable("tanggals")LocalDate tanggals,@RequestBody Map<String, String> requestMap) throws AllException {
        return agendaService.fetchAgendaByTanggal(tanggals,requestMap);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAgendaById(@PathVariable("id") Long id,@RequestBody Map<String, String> requestMap) throws AllException {
        agendaService.deleteAgendaById(id,requestMap);
        return "Agenda telah dihapus!!";
    }

    @PutMapping("/update/{id}")
    public Agenda updateAgenda(@PathVariable("id") Long id,@RequestBody Agenda agenda,Map<String, String> requestMap) throws AllException {
        return agendaService.updateAgenda(id,agenda,requestMap);
    }
}
