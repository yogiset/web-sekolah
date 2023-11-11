package com.sekolah.websekolah.repository;

import com.sekolah.websekolah.entity.Agenda;
import com.sekolah.websekolah.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda,Long> {
    Optional<Agenda> findByJudul(String judul);

    Optional<Agenda> findByTanggals(LocalDate tanggals);
}
