package com.br.clinica.clinica_v1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.io.Serializable;
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SqlResultSetMapping(
        name="PacientesConsultasMapping",
        classes={
                @ConstructorResult(
                        targetClass=Paciente.class,
                        columns={
                                @ColumnResult(name="id", type=Long.class),
                                @ColumnResult(name="nome")})})
public class Consulta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;
    private Integer duracao;

}
