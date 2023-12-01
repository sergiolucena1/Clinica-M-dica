package com.br.clinica.clinica_v1.service;

import com.br.clinica.clinica_v1.dto.ExamesPacienteDto;
import com.br.clinica.clinica_v1.dto.HistoriocoPacienteDTO;
import com.br.clinica.clinica_v1.dto.RegistroPacienteExamesDto;
import com.br.clinica.clinica_v1.entity.RegistroExame;
import com.br.clinica.clinica_v1.repository.RegistroExameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class RegistroExameService {
    @Autowired
    private RegistroExameRepository repository;

    static final Double gradesAvgToApprove = 7.0;

    public RegistroExame create(RegistroExame registroExame) {
        return repository.save(registroExame);
    }

    public HistoriocoPacienteDTO getHistoricoFromPaciente(Long exameId) {
        List<RegistroExame> registroExame = repository.findByExameId(exameId);

        HistoriocoPacienteDTO historicoPacienteDto = new HistoriocoPacienteDTO();

        List<ExamesPacienteDto> examesPacienteDtoList = new ArrayList<>();

        if (!registroExame.isEmpty()) {

            registroExame.stream().forEach(d -> {

                historicoPacienteDto.setConsulta(d.getExame().getConsulta());
                historicoPacienteDto.setNomePaciente(d.getCpf().getPaciente().getNome());

                ExamesPacienteDto examesPacienteDto = new ExamesPacienteDto();

                examesPacienteDto.setNome(d.getExame().getNome());
                examesPacienteDto.setExame1(d.getExame1());
                examesPacienteDto.setExame2(d.getExame2());
                if ((d.getExame1() != null && d.getExame2() != null)) {
                    examesPacienteDto.setMedia(d.getNota1() + d.getNota2() / 2);
                } else {
                    examesPacienteDto.setMedia(null);
                }

                examesPacienteDto.setStatus(d.getStatus());

                examesPacienteDtoList.add(examesPacienteDto);

            });

            historicoPacienteDto.setExanesPacienteDtos(examesPacienteDtoList);

            return historiocoPacienteDTO;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Esse paciente não possui cpf cadastrado.");
    }

    public void updateExames(RegistroPacienteExamesDto examesOnlyDto, Long PacienteId, Long exameId) {
        Optional<RegistroExame> registroExame = Optional.ofNullable(repository.
                findByCpfAndExameId(PacienteId, exameId));

        boolean needUpdate = false;

        if (StringUtils.hasLength(examesOnlyDto.getExame1().toString())) {
            registroExame.ifPresent(cpfPaciente -> cpfPaciente.setExame1(examesOnlyDto.getExame1()));
            needUpdate = true;
        }

        if (StringUtils.hasLength(examesOnlyDto.getExame2().toString())) {
            registroExame.ifPresent(cpfPaciente -> cpfPaciente.setExame2(examesOnlyDto.getExame2()));
            needUpdate = true;
        }

        if (needUpdate) {
            if (registroExame.get().getExame1() != null && registroExame.get().getExame2() != null) {
                if (registroExame.get().getExame1() + registroExame.get().getExame2() / 2 >= gradesAvgToApprove) {
                    registroExame.ifPresent(r -> r.setStatus("REALIZADO"));
                } else {
                    registroExame.ifPresent(cpfPaciente -> cpfPaciente.setStatus("PENDENTE"));
                }

            }
            repository.save(registroExame.get());
        }

    }
}
}
