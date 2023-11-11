package com.sekolah.websekolah.service;


import com.sekolah.websekolah.entity.Murid;
import com.sekolah.websekolah.exception.AllException;
import com.sekolah.websekolah.repository.MuridRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MuridService {
    private final MuridRepository muridRepository;

    public Murid addMurid(Murid murid, Map<String, String> requestMap) throws AllException {
        log.info("Inside addMurid");
        String userRole = requestMap.get("role");
        if ("owner".equalsIgnoreCase(userRole) || "admin".equalsIgnoreCase(userRole)) {
            if (murid.getNama() == null || murid.getNama().isEmpty()) {
                throw new AllException("nama murid harus di isi !!!");
            }
            if (murid.getNamawali() == null || murid.getNamawali().isEmpty()) {
                throw new AllException("nama wali murid harus di isi !!!");
            }
            if (murid.getNo_telp() == null) {
                throw new AllException("no telp murid harus di isi !!!");
            }
            if (murid.getNis() == null || murid.getNis().isEmpty()) {
                throw new AllException("NIS murid harus di isi !!!");
            }
            if (murid.getTgl_lahir() == null) {
                throw new AllException("tanggal lahir murid harus di isi !!!");
            }
            if (murid.getAlamat() == null || murid.getAlamat().isEmpty()) {
                throw new AllException("Alamat murid harus di isi !!!");
            }


            return muridRepository.save(murid);
        } throw new AllException("Invalid User Role");

    }

    public List<Murid> showAllMurid(Map<String, String> requestMap) throws AllException {
        log.info("Inside showAllMurid");
        String userRole = requestMap.get("role");
        if ("owner".equalsIgnoreCase(userRole) || "admin".equalsIgnoreCase(userRole)) {

            muridRepository.findAll();
        } throw new AllException("Invalid User Role");

    }

    public Murid fetchMuridByNama(String nama, Map<String, String> requestMap) throws AllException {
        log.info("Inside fetchMuridByNama");
        String userRole = requestMap.get("role");
        if ("owner".equalsIgnoreCase(userRole) || "admin".equalsIgnoreCase(userRole)) {
            Optional<Murid> murid = muridRepository.findByNama(nama);
            if (!murid.isPresent()) {
                throw new AllException("Nama murid tidak ditemukan");
            }
            return murid.get();

        } throw new AllException("Invalid User Role");

    }

    public Murid fetchMuridByNis(String nis, Map<String, String> requestMap) throws AllException {
        log.info("Inside fetchMuridByNis");
        String userRole = requestMap.get("role");
        if ("owner".equalsIgnoreCase(userRole) || "admin".equalsIgnoreCase(userRole)) {
            Optional<Murid> murid = muridRepository.findByNis(nis);
            if (!murid.isPresent()) {
                throw new AllException("NIS murid tidak ditemukan");
            }
            return murid.get();

        } throw new AllException("Invalid User Role");

    }

    public void deleteMuridById(Long id, Map<String, String> requestMap) throws AllException {
        log.info("Inside deleteMuridById");
        String userRole = requestMap.get("role");
        if ("owner".equalsIgnoreCase(userRole) || "admin".equalsIgnoreCase(userRole)) {
            boolean exist = muridRepository.existsById(id);
            if (!exist) {
                throw new AllException("murid dengan Id" + id + "tidak ada");
            }
            muridRepository.deleteById(id);

        } throw new AllException("Invalid User Role");

    }

    public Murid updateMurid(Long id, Murid murid, Map<String, String> requestMap) throws AllException {
        log.info("Inside updateMurid");
        String userRole = requestMap.get("role");
        if ("owner".equalsIgnoreCase(userRole) || "admin".equalsIgnoreCase(userRole)) {
            if (murid.getNama() == null || murid.getNama().isEmpty()) {
                throw new AllException("nama murid harus di isi !!!");
            }
            if (murid.getNamawali() == null || murid.getNamawali().isEmpty()) {
                throw new AllException("nama wali murid harus di isi !!!");
            }
            if (murid.getNo_telp() == null) {
                throw new AllException("no telp murid harus di isi !!!");
            }
            if (murid.getNis() == null || murid.getNis().isEmpty()) {
                throw new AllException("NIS murid harus di isi !!!");
            }
            if (murid.getTgl_lahir() == null) {
                throw new AllException("tanggal lahir murid harus di isi !!!");
            }
            if (murid.getAlamat() == null || murid.getAlamat().isEmpty()) {
                throw new AllException("Alamat murid harus di isi !!!");
            }
            if (murid.getUmur() == null) {
                throw new AllException("Umur murid harus di isi !!!");
            }

            Murid updatedMurid = muridRepository.findById(id)
                    .orElseThrow(() -> new AllException("Murid dengan Id" + id + "tidak ada"));

            updatedMurid.setNama(murid.getNama());
            updatedMurid.setNamawali(murid.getNamawali());
            updatedMurid.setNo_telp(murid.getNo_telp());
            updatedMurid.setNis(murid.getNis());
            updatedMurid.setTgl_lahir(murid.getTgl_lahir());
            updatedMurid.setAlamat(murid.getAlamat());
            updatedMurid.setUmur(murid.getUmur());
            updatedMurid.setFoto(murid.getFoto());
            muridRepository.save(updatedMurid);

            return updatedMurid;

        } throw new AllException("Invalid User Role");

    }
}
