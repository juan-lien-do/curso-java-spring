package com.example.demo.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "albums")
@Getter
@Setter
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "albumid")
    private Long albumId;
    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "artistid")
    private Artist artist;
}
