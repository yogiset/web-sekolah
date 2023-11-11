package com.sekolah.websekolah.repository;

import com.sekolah.websekolah.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Long> {
    Optional<Staff> findByNama(String nama);

    Optional<Staff> findByNip(String nip);
}
