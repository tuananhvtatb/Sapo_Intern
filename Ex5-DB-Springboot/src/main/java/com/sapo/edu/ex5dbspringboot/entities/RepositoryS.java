package com.sapo.edu.ex5dbspringboot.entities;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_repository")
@Data
public class RepositoryS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String idRepository;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String place;

    @Column()
    private LocalDateTime createdDate;

    @Column()
    private LocalDateTime updatedDate;


}
