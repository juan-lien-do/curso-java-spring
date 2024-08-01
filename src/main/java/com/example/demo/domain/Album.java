package com.example.demo.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "albums")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
