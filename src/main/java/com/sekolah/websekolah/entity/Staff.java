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
@Table(name = "staff")
public class Staff {
    @Id
    @SequenceGenerator(name = "staff_sequence", sequenceName = "staff_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nama;
    private String nip;
    private String jabatan;
    private String alamat;
    private LocalDate tgl_lahir;
    private Integer umur;
    @Column(columnDefinition="text")
    private String foto;

    public Integer getUmur() {
        return Period.between(this.tgl_lahir,LocalDate.now()).getYears();
    }
}
