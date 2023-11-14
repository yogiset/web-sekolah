package com.sekolah.websekolah.controller;

import com.sekolah.websekolah.entity.Berita;
import com.sekolah.websekolah.entity.Staff;
import com.sekolah.websekolah.exception.AllException;
import com.sekolah.websekolah.service.StaffService;
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
@RequestMapping(path = "/staff")
public class StaffController {

private final StaffService staffService;

    @PostMapping("/add")
    public Staff addStaff(@RequestBody Staff staff, Map<String, String> requestMap) throws AllException {
        return staffService.addStaff(staff,requestMap);
    }

    @GetMapping("/all")
    public List<Staff> showAllStaff(@RequestBody Map<String, String> requestMap) throws AllException {
        return staffService.showAllStaff(requestMap);
    }

    @GetMapping("/sortAsc/{field}")
    public List<Staff> showAllStaffByAsc(@PathVariable String field) throws AllException {
        return staffService.showAllStafffAscending(field);
    }
    @GetMapping("/sortDsc/{field}")
    public List<Staff> showAllStaffByDsc(@PathVariable String field) throws AllException {
        return staffService.showAllStaffDescending(field);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public ResponseEntity<List<Staff>> showAllStaffPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Staff> staffWithPagination = staffService.showAllStaffWithPagination(offset, pageSize);

        List<Staff> staffList = staffWithPagination.getContent();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(staffWithPagination.getTotalElements()));

        return new ResponseEntity<>(staffList, headers, HttpStatus.OK);
    }
    @GetMapping("/paginationascname/{offset}/{pageSize}")
    public ResponseEntity<List<Staff>> showAllStaffPaginationAndSortAscbyName(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Staff> staffWithPaginationAscName = staffService.showAllStaffWithPaginationAscName(offset, pageSize);

        List<Staff> staffList = staffWithPaginationAscName.getContent();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(staffWithPaginationAscName.getTotalElements()));

        return new ResponseEntity<>(staffList, headers, HttpStatus.OK);
    }
    @GetMapping("/paginationdescname/{offset}/{pageSize}")
    public ResponseEntity<List<Staff>> showAllStaffPaginationAndSortDescbyName(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Staff> staffWithPaginationDescName = staffService.showAllStaffWithPaginationDescName(offset, pageSize);

        List<Staff> staffList = staffWithPaginationDescName.getContent();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(staffWithPaginationDescName.getTotalElements()));

        return new ResponseEntity<>(staffList, headers, HttpStatus.OK);
    }

    @GetMapping("/cari/{nama}")
    public Staff fetchStaffByNama(@PathVariable("nama") String nama,@RequestBody Map<String, String> requestMap) throws AllException {
        return staffService.fetchStaffByNama(nama,requestMap);
    }

    @GetMapping("/cari/{nip}")
    public Staff fetchStaffByNis(@PathVariable("nip") String nip, @RequestBody Map<String, String> requestMap) throws AllException {
        return staffService.fetchStaffByNis(nip,requestMap);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStaffById(@PathVariable("id") Long id,@RequestBody Map<String, String> requestMap) throws AllException {
        staffService.deleteStaffById(id,requestMap);
        return "Staff telah dihapus!!";
    }

    @PutMapping("/update/{id}")
    public Staff updateStaff(@PathVariable("id") Long id,@RequestBody Staff staff,Map<String, String> requestMap) throws AllException {
        return staffService.updateStaff(id,staff,requestMap);
    }
}
