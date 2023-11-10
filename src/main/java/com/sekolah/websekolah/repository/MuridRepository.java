package com.sekolah.websekolah.repository;

import com.sekolah.websekolah.entity.Murid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuridRepository extends JpaRepository<Murid,Long> {
}
