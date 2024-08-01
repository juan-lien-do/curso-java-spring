package com.example.demo.service;

import com.example.demo.domain.Album;
import com.example.demo.domain.Artist;
import com.example.demo.dto.AlbumDTO;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.mapper.AlbumMapper;
import com.example.demo.repository.AlbumRepository;
import com.example.demo.repository.ArtistRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AlbumService {
    @Autowired
    private final AlbumRepository albumRepository;
    @Autowired
    private final ArtistRepository artistRepository;

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

    @Transactional(readOnly = false)
    public Boolean delete(Long id) throws NotFoundException {
        if (albumRepository.existsById(id)) {
            albumRepository.deleteById(id);
        } else {
            throw new NotFoundException("No se encontró el album");
        }

        return !albumRepository.existsById(id);
    }

    @Transactional(readOnly = false)
    public AlbumDTO create(AlbumDTO body) throws NotFoundException, BadRequestException {
        if (body.getTitle().length() < 5){
            throw new BadRequestException("El nombre del album es muy corto");
        }

        Optional<Artist> artistOpt = artistRepository.findById(body.getArtistaId());
        if (artistOpt.isEmpty()){
            throw new NotFoundException("No se encontró el artista");
        }
        else {
            Album album1 = AlbumMapper.toEntity(body, artistOpt.get());
            Album album2 = albumRepository.save(album1);

            return AlbumMapper.toDto(album2);
        }

    }

}
