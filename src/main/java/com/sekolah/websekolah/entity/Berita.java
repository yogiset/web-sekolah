package com.sekolah.websekolah.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "berita")
public class Berita {
    @Id
    @SequenceGenerator(name = "berita_sequence",sequenceName = "berita_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String judul;
    private String tanggal;
    @Column(columnDefinition="text")
    private String image;
    private String deskripsi;
    private String kategori;
    private String created;

}
