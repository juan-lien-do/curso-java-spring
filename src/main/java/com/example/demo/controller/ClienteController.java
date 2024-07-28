package com.example.demo.controller;

import com.example.demo.domain.Cliente;
import com.example.demo.dto.ClienteDTO;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/clientes")
@AllArgsConstructor
public class ClienteController {
    @Autowired
    private final ClienteService clienteService;

    @GetMapping("")
    public ResponseEntity<ClienteDTO> getClienteByNombre(@RequestParam String nombre) {
        try {
            ClienteDTO clienteDto = clienteService.getClienteByName(nombre);
            return ResponseEntity.ok(clienteDto);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().header("ERROR_MSG", e.getMessage()).build();
        }
    }

    @PostMapping("")
    public ResponseEntity<ClienteDTO> postCliente(@RequestBody ClienteDTO clienteBody) {
        try {
            ClienteDTO clienteDTO = clienteService.saveCliente(clienteBody);
            return ResponseEntity.status(201).body(clienteDTO);
        } catch (BadRequestException e){
            return ResponseEntity.badRequest().header("ERROR_MSG", e.getMessage()).build();
        }
    }
}
