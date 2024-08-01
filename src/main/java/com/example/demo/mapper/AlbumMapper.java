package com.example.demo.mapper;

import com.example.demo.domain.Album;
import com.example.demo.dto.AlbumDTO;

public class AlbumMapper {
    public static AlbumDTO toDto(Album album){

        return AlbumDTO.builder()
                .albumId(album.getAlbumId())
                .title(album.getTitle())
                .nombreArtista(album.getArtist().getName())
                .build();
    }
}
