package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClienteDTO {
    private Long id;
    private String nombre;
}
