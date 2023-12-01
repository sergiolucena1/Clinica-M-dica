package com.br.clinica.clinica_v1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RegistroExame implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="paciente_id")
    private RegistroExame paciente;

    @ManyToOne
    @JoinColumn(name="exame_id")
    private Consulta exame1;

    private Consulta exame2;


    private String tipo;

    private Double resultado;

    private String status;
}
