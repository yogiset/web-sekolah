package com.sekolah.websekolah.repository;

import com.sekolah.websekolah.entity.Berita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeritaRepository extends JpaRepository<Berita,Long> {
}
