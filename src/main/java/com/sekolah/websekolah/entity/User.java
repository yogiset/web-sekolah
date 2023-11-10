package com.sekolah.websekolah.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "userr")
public class User {
    @Id
    @SequenceGenerator(name = "staff_sequence", sequenceName = "staff_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private Integer no_telp;
    private String role;
    private String status;
    private Instant created;
}
