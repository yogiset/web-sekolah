package com.sekolah.websekolah.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "agenda")
public class Agenda {
    @Id
    @SequenceGenerator(name = "agenda_sequence",sequenceName = "agenda_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String judul;
    private String tanggal;
    private String tempat;
    private String deskripsi;
}
