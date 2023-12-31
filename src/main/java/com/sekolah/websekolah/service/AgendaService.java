package com.sekolah.websekolah.service;

import com.sekolah.websekolah.entity.Agenda;
import com.sekolah.websekolah.exception.AllException;
import com.sekolah.websekolah.repository.AgendaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    }

    public List<Agenda> showAllAgenda(Map<String, String> requestMap) throws AllException {
        log.info("Inside showAllAgenda");

            return agendaRepository.findAll();
    }

    public List<Agenda> showAllAgendaAscending(String field) {
        log.info("Inside showAllAgendaAsccending");

        return agendaRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }
    public List<Agenda> showAllAgendaDescending(String field) {
        log.info("Inside showAllAgendaDesccending");

        return agendaRepository.findAll(Sort.by(Sort.Direction.DESC,field));
    }
    public Page<Agenda> showAllAgendaWithPagination(int offset, int pageSize) {
        log.info("Inside showAllAgendaWithPagination");
        Page<Agenda>pageable = agendaRepository.findAll(PageRequest.of(offset, pageSize));
        return pageable;
    }

    public Page<Agenda> showAllAgendaWithPaginationAscJudul(int offset, int pageSize) {
        log.info("Inside showAllAgendaWithPaginationAscJudul");
        Page<Agenda>pageable = agendaRepository.findAll(PageRequest.of(offset, pageSize,Sort.by("judul").ascending()));
        return pageable;
    }

    public Page<Agenda> showAllAgendaWithPaginationDescJudul(int offset, int pageSize) {
        log.info("Inside showAllAgendaWithPaginationDescJudul");
        Page<Agenda>pageable = agendaRepository.findAll(PageRequest.of(offset, pageSize,Sort.by("judul").descending()));
        return pageable;
    }

    public Agenda fetchAgendaByJudul(String judul, Map<String, String> requestMap) throws AllException {
        log.info("Inside fetchAgendaByJudul");

            Optional<Agenda> agenda = agendaRepository.findByJudul(judul);
            if (!agenda.isPresent()) {
                throw new AllException("Judul Agenda tidak ditemukan");
            }
            return agenda.get();

    }
    public Agenda fetchAgendaByTanggal(LocalDate tanggals, Map<String, String> requestMap) throws AllException {
        log.info("Inside fetchAgendaByTanggal");
            Optional<Agenda> agenda = agendaRepository.findByTanggals(tanggals);
            if (!agenda.isPresent()) {
                throw new AllException("Tanggal Agenda tidak ditemukan");
            }
            return agenda.get();
    }

    public void deleteAgendaById(Long id, Map<String, String> requestMap) throws AllException {
        log.info("Inside deleteAgendaById");

            boolean exist = agendaRepository.existsById(id);
            if (!exist) {
                throw new AllException("Agenda dengan Id" + id + "tidak ada");
            }
            agendaRepository.deleteById(id);
    }

    public Agenda updateAgenda(Long id, Agenda agenda, Map<String, String> requestMap) throws AllException {
        log.info("Inside updateAgenda");

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
    }



}
