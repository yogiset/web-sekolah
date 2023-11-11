package com.sekolah.websekolah.repository;

import com.sekolah.websekolah.entity.Berita;
import com.sekolah.websekolah.entity.Murid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface MuridRepository extends JpaRepository<Murid,Long> {
    Optional<Murid> findByNama(String nama);

    Optional<Murid> findByNis(String nis);
}
