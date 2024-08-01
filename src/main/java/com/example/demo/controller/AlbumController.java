package com.example.demo.controller;

import com.example.demo.dto.AlbumDTO;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.service.AlbumService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
@AllArgsConstructor
public class AlbumController {
    @Autowired
    private final AlbumService albumService;

    @GetMapping("/")
    public ResponseEntity<List<AlbumDTO>> getAll(){
        List<AlbumDTO> albumes = albumService.getAllAlbums();
        return ResponseEntity.ok(albumes);
    }

    @GetMapping("/{id}")  //   /api/albums/58
    public ResponseEntity<AlbumDTO> getById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(albumService.getById(id));

        } catch (NotFoundException e){
            return ResponseEntity.notFound().header("ERROR_MSG", e.getMessage()).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AlbumDTO> deleteByID(@PathVariable Long id){
        try {
            if (albumService.delete(id)){
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (NotFoundException e){
            return ResponseEntity.notFound().header("ERROR_MSG", e.getMessage()).build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<AlbumDTO> post(@RequestBody AlbumDTO albumDTO){
        try {
            AlbumDTO albumDTO1 = albumService.create(albumDTO);
            return ResponseEntity.ok(albumDTO1);
        } catch (NotFoundException e){
            return ResponseEntity.notFound().header("ERROR_MSG", e.getMessage()).build();
        } catch (BadRequestException e){
            return ResponseEntity.badRequest().header("ERROR_MSG", e.getMessage()).build();
        }
    }




}
