package com.example.demo.service;

import com.example.demo.domain.Album;
import com.example.demo.dto.AlbumDTO;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.mapper.AlbumMapper;
import com.example.demo.repository.AlbumRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AlbumService {
    @Autowired
    private final AlbumRepository albumRepository;

    public List<AlbumDTO> getAllAlbums() {
        List<Album> lista = albumRepository.findAll();
        return lista.stream()
                .map(AlbumMapper::toDto)
                .toList();
    }

    public AlbumDTO getById(Long id) throws NotFoundException {
        Optional<Album> album = albumRepository.findById(id);
        if (album.isEmpty()) {
            throw new NotFoundException("No se encontró el album");
        }

        return AlbumMapper.toDto(album.get());
    }
}
