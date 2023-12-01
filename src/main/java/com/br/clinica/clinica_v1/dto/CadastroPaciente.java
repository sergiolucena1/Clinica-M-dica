package com.br.clinica.clinica_v1.dto;

import lombok.Data;

@Data
public class CadastroPaciente {
    private String nomePaciente;
    private String emailPaciente;
    private Long idConsulta;
}
