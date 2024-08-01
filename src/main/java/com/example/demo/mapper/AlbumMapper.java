package com.example.demo.mapper;

import com.example.demo.domain.Album;
import com.example.demo.domain.Artist;
import com.example.demo.dto.AlbumDTO;

public class AlbumMapper {
    public static AlbumDTO toDto(Album album){

        return AlbumDTO.builder()
                .albumId(album.getAlbumId())
                .title(album.getTitle())
                .artistaId(album.getArtist().getArtistId())
                .build();
    }

    public static Album toEntity(AlbumDTO dto, Artist artist){
        return Album.builder()
                .title(dto.getTitle())
                .artist(artist)
                .build();
    }
}
