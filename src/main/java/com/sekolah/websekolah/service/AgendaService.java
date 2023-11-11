package com.sekolah.websekolah.service;

import com.sekolah.websekolah.entity.Agenda;
import com.sekolah.websekolah.exception.AllException;
import com.sekolah.websekolah.repository.AgendaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgendaService {

    private final AgendaRepository agendaRepository;


    public Agenda addAgenda(Agenda agenda, Map<String, String> requestMap) throws AllException {
        log.info("Inside addAgenda");
        String userRole = requestMap.get("role");
        if ("owner".equalsIgnoreCase(userRole) || "admin".equalsIgnoreCase(userRole)) {

            if (agenda.getJudul() == null || agenda.getJudul().isEmpty()) {
                throw new AllException("Judul harus di isi !!!");
            }
            if (agenda.getTempat() == null || agenda.getTempat().isEmpty()) {
                throw new AllException("Tempat harus di isi !!!");
            }
            if (agenda.getTanggal() == null || agenda.getTanggal().isEmpty()) {
                throw new AllException("teks Tanggal harus di isi !!!");
            }
            if (agenda.getTanggals() == null) {
                throw new AllException("LocalDate Tanggal harus di isi !!!");
            }
            if (agenda.getDeskripsi() == null || agenda.getDeskripsi().isEmpty()) {
                throw new AllException("deskripsi harus di isi !!!");
            }


            return agendaRepository.save(agenda);

        } throw new AllException("Invalid User Role");
    }

    public List<Agenda> showAllAgenda(Map<String, String> requestMap) throws AllException {
        log.info("Inside showAllAgenda");

        String userRole = requestMap.get("role");
        if ("owner".equalsIgnoreCase(userRole) || "admin".equalsIgnoreCase(userRole)) {

            return agendaRepository.findAll();

        } throw new AllException("Invalid User Role");
    }

    public Agenda fetchAgendaByJudul(String judul, Map<String, String> requestMap) throws AllException {
        log.info("Inside fetchAgendaByJudul");

        String userRole = requestMap.get("role");
        if ("owner".equalsIgnoreCase(userRole) || "admin".equalsIgnoreCase(userRole)) {
            Optional<Agenda> agenda = agendaRepository.findByJudul(judul);
            if (!agenda.isPresent()) {
                throw new AllException("Judul Agenda tidak ditemukan");
            }
            return agenda.get();
        } throw new AllException("Invalid User Role");
    }

    public Agenda fetchAgendaByTanggal(LocalDate tanggals, Map<String, String> requestMap) throws AllException {
        log.info("Inside fetchAgendaByTanggal");

        String userRole = requestMap.get("role");
        if ("owner".equalsIgnoreCase(userRole) || "admin".equalsIgnoreCase(userRole)) {
            Optional<Agenda> agenda = agendaRepository.findByTanggals(tanggals);
            if (!agenda.isPresent()) {
                throw new AllException("Tanggal Agenda tidak ditemukan");
            }
            return agenda.get();


        } throw new AllException("Invalid User Role");
    }

    public void deleteAgendaById(Long id, Map<String, String> requestMap) throws AllException {
        log.info("Inside deleteAgendaById");

        String userRole = requestMap.get("role");
        if ("owner".equalsIgnoreCase(userRole) || "admin".equalsIgnoreCase(userRole)) {
            boolean exist = agendaRepository.existsById(id);
            if (!exist) {
                throw new AllException("Agenda dengan Id" + id + "tidak ada");
            }
            agendaRepository.deleteById(id);


        } throw new AllException("Invalid User Role");
    }

    public Agenda updateAgenda(Long id, Agenda agenda, Map<String, String> requestMap) throws AllException {
        log.info("Inside updateAgenda");

        String userRole = requestMap.get("role");
        if ("owner".equalsIgnoreCase(userRole) || "admin".equalsIgnoreCase(userRole)) {

            if (agenda.getJudul() == null || agenda.getJudul().isEmpty()) {
                throw new AllException("Judul harus di isi !!!");
            }
            if (agenda.getTempat() == null || agenda.getTempat().isEmpty()) {
                throw new AllException("Tempat harus di isi !!!");
            }
            if (agenda.getTanggal() == null || agenda.getTanggal().isEmpty()) {
                throw new AllException("teks Tanggal harus di isi !!!");
            }
            if (agenda.getTanggals() == null) {
                throw new AllException("LocalDate Tanggal harus di isi !!!");
            }
            if (agenda.getDeskripsi() == null || agenda.getDeskripsi().isEmpty()) {
                throw new AllException("deskripsi harus di isi !!!");
            }

            Agenda updatedAgenda = agendaRepository.findById(id)
                    .orElseThrow(() -> new AllException("Agenda dengan Id" + id + "tidak ada"));

            updatedAgenda.setJudul(agenda.getJudul());
            updatedAgenda.setTempat(agenda.getTempat());
            updatedAgenda.setTanggal(agenda.getTanggal());
            updatedAgenda.setTanggals(agenda.getTanggals());
            updatedAgenda.setDeskripsi(agenda.getDeskripsi());
            agendaRepository.save(updatedAgenda);

            return updatedAgenda;

        } throw new AllException("Invalid User Role");
    }
}
