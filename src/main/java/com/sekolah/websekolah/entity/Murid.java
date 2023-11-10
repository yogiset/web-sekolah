package com.sekolah.websekolah.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "murid")
public class Murid {
    @Id
    @SequenceGenerator(name = "staff_sequence", sequenceName = "staff_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nama;
    private String namawali;
    private Integer no_telp;
    private String nis;
    private LocalDate tgl_lahir;
    private Integer umur;
    private String alamat;

    public Integer getUmur() {
        return Period.between(this.tgl_lahir,LocalDate.now()).getYears();
    }
}
