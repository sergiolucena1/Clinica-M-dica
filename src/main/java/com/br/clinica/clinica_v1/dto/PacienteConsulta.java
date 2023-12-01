package com.br.clinica.clinica_v1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PacienteConsulta {
    private String nomePaciente;
    private String consulta;
    private String nomeMedico;
    private String tipoEnfermidade;
    private Boolean necessarioExame;
    private String tipoExame;
}
