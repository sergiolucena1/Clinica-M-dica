package com.br.clinica.clinica_v1.dto;

import lombok.Data;

@Data
public class ExamesPacienteDto {
    private String nome;
    private Double nota1;
    private Double nota2;
    private Double media;
    private String status;

}
