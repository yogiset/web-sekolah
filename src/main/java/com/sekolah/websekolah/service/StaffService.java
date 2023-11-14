package com.sekolah.websekolah.service;


import com.sekolah.websekolah.entity.Murid;
import com.sekolah.websekolah.entity.Staff;
import com.sekolah.websekolah.exception.AllException;
import com.sekolah.websekolah.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StaffService {

    private final StaffRepository staffRepository;

    public Staff addStaff(Staff staff, Map<String, String> requestMap) throws AllException {
        log.info("Inside addStaff");
        String userRole = requestMap.get("role");
        if ("owner".equalsIgnoreCase(userRole) || "admin".equalsIgnoreCase(userRole)) {
            if (staff.getNama() == null || staff.getNama().isEmpty()) {
                throw new AllException("nama staff harus di isi !!!");
            }
            if (staff.getNip() == null || staff.getNip().isEmpty()) {
                throw new AllException("NIP staff harus di isi !!!");
            }
            if (staff.getJabatan() == null || staff.getJabatan().isEmpty()) {
                throw new AllException("jabatan staff harus di isi !!!");
            }
            if (staff.getAlamat() == null || staff.getAlamat().isEmpty()) {
                throw new AllException("Alamat staff harus di isi !!!");
            }
            if (staff.getTgl_lahir() == null) {
                throw new AllException("tanggal lahir staff harus di isi !!!");
            }


         staffRepository.save(staff);
        } throw new AllException("Invalid User Role");
    }

    public List<Staff> showAllStaff(Map<String, String> requestMap) throws AllException {
        log.info("Inside showAllStaff");
        return staffRepository.findAll();
    }

    public List<Staff> showAllStafffAscending(String field) {
        log.info("Inside showAllStaffAscending");
        return staffRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    public List<Staff> showAllStaffDescending(String field) {
        log.info("Inside showAllStaffDescending");
        return staffRepository.findAll(Sort.by(Sort.Direction.DESC,field));
    }
    public Page<Staff> showAllStaffWithPagination(int offset, int pageSize) {
        log.info("Inside showAllStaffWithPagination");
        Page<Staff>page = staffRepository.findAll(PageRequest.of(offset,pageSize));
        return page;
    }

    public Page<Staff> showAllStaffWithPaginationAscName(int offset, int pageSize) {
        log.info("Inside showAllStaffWithPaginationAscName");
        Page<Staff>page = staffRepository.findAll(PageRequest.of(offset,pageSize,Sort.by("nama").ascending()));
        return page;
    }

    public Page<Staff> showAllStaffWithPaginationDescName(int offset, int pageSize) {
        log.info("showAllStaffWithPaginationDescName");
        Page<Staff>page = staffRepository.findAll(PageRequest.of(offset,pageSize,Sort.by("nama").descending()));
        return page;
    }

    public Staff fetchStaffByNama(String nama, Map<String, String> requestMap) throws AllException {
        log.info("Inside fetchStaffByNama");
            Optional<Staff> staff = staffRepository.findByNama(nama);
            if (!staff.isPresent()) {
                throw new AllException("Nama staff tidak ditemukan");
            }
            return staff.get();
    }

    public Staff fetchStaffByNis(String nip, Map<String, String> requestMap) throws AllException {
        log.info("Inside fetchStaffByNis");
            Optional<Staff> staff = staffRepository.findByNip(nip);
            if (!staff.isPresent()) {
                throw new AllException("NIP staff tidak ditemukan");
            }
            return staff.get();
    }

    public void deleteStaffById(Long id, Map<String, String> requestMap) throws AllException {
        log.info("Inside deleteStaffById");
        String userRole = requestMap.get("role");
        if ("owner".equalsIgnoreCase(userRole) || "admin".equalsIgnoreCase(userRole)) {

            boolean exist = staffRepository.existsById(id);
            if (!exist) {
                throw new AllException("staff dengan Id" + id + "tidak ada");
            }
            staffRepository.deleteById(id);

        } throw new AllException("Invalid User Role");
    }

    public Staff updateStaff(Long id, Staff staff, Map<String, String> requestMap) throws AllException {
        log.info("Inside updateStaff");
        String userRole = requestMap.get("role");
        if ("owner".equalsIgnoreCase(userRole) || "admin".equalsIgnoreCase(userRole)) {

            if (staff.getNama() == null || staff.getNama().isEmpty()) {
                throw new AllException("nama staff harus di isi !!!");
            }
            if (staff.getNip() == null || staff.getNip().isEmpty()) {
                throw new AllException("NIP staff harus di isi !!!");
            }
            if (staff.getJabatan() == null || staff.getJabatan().isEmpty()) {
                throw new AllException("jabatan staff harus di isi !!!");
            }
            if (staff.getAlamat() == null || staff.getAlamat().isEmpty()) {
                throw new AllException("Alamat staff harus di isi !!!");
            }
            if (staff.getTgl_lahir() == null) {
                throw new AllException("tanggal lahir staff harus di isi !!!");
            }
            if (staff.getUmur() == null) {
                throw new AllException("Umur staff harus di isi !!!");
            }

            Staff updatedStaff = staffRepository.findById(id)
                    .orElseThrow(() -> new AllException("Staff dengan Id" + id + "tidak ada"));

            updatedStaff.setNama(staff.getNama());
            updatedStaff.setNip(staff.getNip());
            updatedStaff.setJabatan(staff.getJabatan());
            updatedStaff.setAlamat(staff.getAlamat());
            updatedStaff.setTgl_lahir(staff.getTgl_lahir());
            updatedStaff.setUmur(staff.getUmur());
            updatedStaff.setFoto(staff.getFoto());
            staffRepository.save(updatedStaff);

            return updatedStaff;
        } throw new AllException("Invalid User Role");
    }


}
