package com.sekolah.websekolah.repository;

import com.sekolah.websekolah.entity.Agenda;
import com.sekolah.websekolah.entity.Berita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface BeritaRepository extends JpaRepository<Berita,Long> {

    Optional<Berita> findByJudulberita(String judulberita);

    Optional<Berita> findByTanggalberita(LocalDate tanggalberita);
}
