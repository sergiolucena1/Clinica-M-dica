package com.br.clinica.clinica_v1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Exame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String tipo;

    private Long status;

    @OneToMany(mappedBy = "exame")
    Set<RegistroExames> registroExames;

    @ManyToOne
    @JoinColumn(name="medico_id")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name="consulta_id")
    private Consulta consulta;
}
