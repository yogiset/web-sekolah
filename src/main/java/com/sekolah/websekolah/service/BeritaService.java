package com.sekolah.websekolah.service;

import com.sekolah.websekolah.entity.Berita;
import com.sekolah.websekolah.exception.AllException;
import com.sekolah.websekolah.repository.BeritaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BeritaService {
    private final BeritaRepository beritaRepository;

    public Berita addBerita(Berita berita, Map<String, String> requestMap) throws AllException {
       log.info("Inside addBerita");

            if (berita.getJudulberita() == null || berita.getJudulberita().isEmpty()) {
                throw new AllException("Judul harus di isi !!!");
            }
            if (berita.getTanggal() == null || berita.getTanggal().isEmpty()) {
                throw new AllException("teks Tanggal harus di isi !!!");
            }
            if (berita.getTanggalberita() == null) {
                throw new AllException("LocalDate Tanggal harus di isi !!!");
            }
            if (berita.getImage() == null || berita.getImage().isEmpty()) {
                throw new AllException("gambar harus di isi !!");
            }
            if (berita.getDeskripsi() == null || berita.getDeskripsi().isEmpty()) {
                throw new AllException("deskripsi harus di isi !!!");
            }
            if(berita.getKategori() == null || berita.getKategori().isEmpty()) {
                throw  new AllException("Kategori harus di isi !!!");
            }
            if(berita.getCreated() == null || berita.getCreated().isEmpty()){
                throw  new AllException("Created harus di isi !!!");
            }
            return beritaRepository.save(berita);
    }

    public List<Berita> showAllBerita(Map<String, String> requestMap) throws AllException {
        log.info("Inside showAllBerita");
        return beritaRepository.findAll();
    }

    public List<Berita> showAllBeritaAscending(String field) {
        log.info("Inside showAllBeritaAscending");
        return beritaRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    public List<Berita> showAllBeritaDescending(String field) {
        log.info("Inside showAllBeritaDescending");
        return beritaRepository.findAll(Sort.by(Sort.Direction.DESC,field));
    }
    public List<Berita> showAllBeritaAscendingLimit(String field, int pageSize) {
        log.info("Inside showAllBeritaAscendingLimit");
        Pageable pageable = PageRequest.of(0, pageSize, Sort.by(Sort.Direction.ASC, field));
        return beritaRepository.findAll(pageable).getContent();
    }

    public List<Berita> showAllBeritaDescendingLimit(String field, int pageSize) {
        log.info("Inside showAllBeritaDescendingLimit");
        Pageable pageable = PageRequest.of(0, pageSize, Sort.by(Sort.Direction.DESC, field));
        return beritaRepository.findAll(pageable).getContent();
    }
    public Page<Berita> showAllBeritaWithPagination(int offset, int pageSize) {
        log.info("Inside showAllBeritaWithPagination");
        Page<Berita> pageable = beritaRepository.findAll(PageRequest.of(offset,pageSize));
        return pageable;
    }

    public Page<Berita> showAllBeritaWithPaginationAscJudul(int offset, int pageSize) {
        log.info("Inside showAllBeritaWithPaginationAscJudul");
        Page<Berita> pageable = beritaRepository.findAll(PageRequest.of(offset,pageSize,Sort.by("judulberita").ascending()));
        return pageable;
    }

    public Page<Berita> showAllBeritaWithPaginationDescJudul(int offset, int pageSize) {
        log.info("Inside showAllBeritaWithPaginationDescJudul");
        Page<Berita> pageable = beritaRepository.findAll(PageRequest.of(offset,pageSize,Sort.by("judulberita").descending()));
        return pageable;
    }

    public Page<Berita> showAllBeritaWithPaginationAscTanggalBerita(int offset, int pageSize) {
        log.info("Inside showAllBeritaWithPaginationAscTanggalBerita");
        Page<Berita> pageable = beritaRepository.findAll(PageRequest.of(offset,pageSize,Sort.by("tanggalberita").ascending()));
        return pageable;
    }
    public Page<Berita> showAllBeritaWithPaginationDescTanggalBerita(int offset, int pageSize) {
        log.info("Inside showAllBeritaWithPaginationDescTanggalBerita");
        Page<Berita> pageable = beritaRepository.findAll(PageRequest.of(offset,pageSize,Sort.by("tanggalberita").descending()));
        return pageable;
    }

    public Berita fetchBeritaByJudul(String judulberita, Map<String, String> requestMap) throws AllException {
        log.info("Inside fetchBeritaByJudul");
            Optional<Berita> berita = beritaRepository.findByJudulberita(judulberita);
            if (!berita.isPresent()) {
                throw new AllException("Judul Berita tidak ditemukan");
            }
            return berita.get();
    }
    public Berita fetchBeritaByTanggal(LocalDate tanggalberita, Map<String, String> requestMap) throws AllException {
        log.info("Inside fetchBeritaByTanggal");
            Optional<Berita> berita = beritaRepository.findByTanggalberita(tanggalberita);
            if (!berita.isPresent()) {
                throw new AllException("Tanggal Berita tidak ditemukan");
            }
            return berita.get();
    }

    public void deleteBeritaById(Long id, Map<String, String> requestMap) throws AllException {
        log.info("Inside deleteBeritaById");

            boolean exist = beritaRepository.existsById(id);
            if (!exist) {
                throw new AllException("berita dengan Id" + id + "tidak ada");
            }
            beritaRepository.deleteById(id);

    }

    public Berita updateBerita(Long id, Berita berita, Map<String, String> requestMap) throws AllException {
        log.info("Inside updateBerita");

            if (berita.getJudulberita() == null || berita.getJudulberita().isEmpty()) {
                throw new AllException("Judul harus di isi !!!");
            }
            if (berita.getTanggal() == null || berita.getTanggal().isEmpty()) {
                throw new AllException("teks Tanggal harus di isi !!!");
            }
            if (berita.getTanggalberita() == null) {
                throw new AllException("LocalDate Tanggal harus di isi !!!");
            }
            if (berita.getImage() == null || berita.getImage().isEmpty()) {
                throw new AllException("gambar harus di isi !!");
            }
            if (berita.getDeskripsi() == null || berita.getDeskripsi().isEmpty()) {
                throw new AllException("deskripsi harus di isi !!!");
            }
            if(berita.getKategori() == null || berita.getKategori().isEmpty()) {
                throw  new AllException("Kategori harus di isi !!!");
            }
            if(berita.getCreated() == null || berita.getCreated().isEmpty()){
                throw  new AllException("Created harus di isi !!!");
            }

            Berita updatedBerita = beritaRepository.findById(id)
                    .orElseThrow(() -> new AllException("Berita dengan Id" + id + "tidak ada"));

            updatedBerita.setJudulberita(berita.getJudulberita());
            updatedBerita.setTanggal(berita.getTanggal());
            updatedBerita.setTanggalberita(berita.getTanggalberita());
            updatedBerita.setImage(berita.getImage());
            updatedBerita.setDeskripsi(berita.getDeskripsi());
            updatedBerita.setKategori(berita.getKategori());
            updatedBerita.setCreated(berita.getCreated());
            beritaRepository.save(updatedBerita);

            return updatedBerita;
    }



}
