package com.sekolah.websekolah.controller;

import com.sekolah.websekolah.entity.Staff;
import com.sekolah.websekolah.exception.AllException;
import com.sekolah.websekolah.service.StaffService;
import lombok.RequiredArgsConstructor;
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
