package com.sekolah.websekolah.repository;

import com.sekolah.websekolah.entity.Agenda;
import com.sekolah.websekolah.entity.Berita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface BeritaRepository extends JpaRepository<Berita,Long> {

    Optional<Berita> findByJudul(String judul);

    Optional<Berita> findByTanggals(LocalDate tanggals);
}
