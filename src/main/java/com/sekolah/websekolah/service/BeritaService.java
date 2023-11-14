package com.sekolah.websekolah.service;

import com.sekolah.websekolah.entity.Berita;
import com.sekolah.websekolah.exception.AllException;
import com.sekolah.websekolah.repository.BeritaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        String userRole = requestMap.get("role");
        if ("owner".equalsIgnoreCase(userRole) || "admin".equalsIgnoreCase(userRole)) {

            if (berita.getJudul() == null || berita.getJudul().isEmpty()) {
                throw new AllException("Judul harus di isi !!!");
            }
            if (berita.getTanggal() == null || berita.getTanggal().isEmpty()) {
                throw new AllException("teks Tanggal harus di isi !!!");
            }
            if (berita.getTanggals() == null) {
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

        } throw new AllException("Invalid User Role");
    }

    public List<Berita> showAllBerita(Map<String, String> requestMap) throws AllException {
        log.info("Inside showAllBerita");
        String userRole = requestMap.get("role");
        if ("owner".equalsIgnoreCase(userRole) || "admin".equalsIgnoreCase(userRole)) {

        return beritaRepository.findAll();

        } throw new AllException("Invalid User Role");
    }

    public List<Berita> showAllBeritaAscending(String field) {
        return beritaRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    public List<Berita> showAllBeritaDescending(String field) {
        return beritaRepository.findAll(Sort.by(Sort.Direction.DESC,field));
    }

    public Berita fetchBeritaByJudul(String judul, Map<String, String> requestMap) throws AllException {
        log.info("Inside fetchBeritaByJudul");
        String userRole = requestMap.get("role");
        if ("owner".equalsIgnoreCase(userRole) || "admin".equalsIgnoreCase(userRole)) {
            Optional<Berita> berita = beritaRepository.findByJudul(judul);
            if (!berita.isPresent()) {
                throw new AllException("Judul Berita tidak ditemukan");
            }
            return berita.get();

        } throw new AllException("Invalid User Role");
    }

    public Berita fetchBeritaByTanggal(LocalDate tanggals, Map<String, String> requestMap) throws AllException {
        log.info("Inside fetchBeritaByTanggal");

        String userRole = requestMap.get("role");
        if ("owner".equalsIgnoreCase(userRole) || "admin".equalsIgnoreCase(userRole)) {
            Optional<Berita> berita = beritaRepository.findByTanggals(tanggals);
            if (!berita.isPresent()) {
                throw new AllException("Tanggal Berita tidak ditemukan");
            }
            return berita.get();
        } throw new AllException("Invalid User Role");
    }

    public void deleteBeritaById(Long id, Map<String, String> requestMap) throws AllException {
        log.info("Inside deleteBeritaById");

        String userRole = requestMap.get("role");
        if ("owner".equalsIgnoreCase(userRole) || "admin".equalsIgnoreCase(userRole)) {
            boolean exist = beritaRepository.existsById(id);
            if (!exist) {
                throw new AllException("berita dengan Id" + id + "tidak ada");
            }
            beritaRepository.deleteById(id);

        } throw new AllException("Invalid User Role");
    }

    public Berita updateBerita(Long id, Berita berita, Map<String, String> requestMap) throws AllException {
        log.info("Inside updateBerita");

        String userRole = requestMap.get("role");
        if ("owner".equalsIgnoreCase(userRole) || "admin".equalsIgnoreCase(userRole)) {
            if (berita.getJudul() == null || berita.getJudul().isEmpty()) {
                throw new AllException("Judul harus di isi !!!");
            }
            if (berita.getTanggal() == null || berita.getTanggal().isEmpty()) {
                throw new AllException("teks Tanggal harus di isi !!!");
            }
            if (berita.getTanggals() == null) {
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

            updatedBerita.setJudul(berita.getJudul());
            updatedBerita.setTanggal(berita.getTanggal());
            updatedBerita.setTanggals(berita.getTanggals());
            updatedBerita.setImage(berita.getImage());
            updatedBerita.setDeskripsi(berita.getDeskripsi());
            updatedBerita.setKategori(berita.getKategori());
            updatedBerita.setCreated(berita.getCreated());
            beritaRepository.save(updatedBerita);

            return updatedBerita;


        } throw new AllException("Invalid User Role");
    }


}
