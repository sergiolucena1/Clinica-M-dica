package com.br.clinica.clinica_v1.controller;

@RestController
@RequestMapping("/cadastro-paciente")
public class CadastroPacienteController {

    @Autowired
    registro service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MatriculaAluno> create(@RequestBody MatriculaAluno matriculaAluno) {
        MatriculaAluno matriculaAlunoCreated = service.create(matriculaAluno);

        return ResponseEntity.status(201).body(matriculaAlunoCreated);
    }

    @PatchMapping("/trancar-matricula/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void patchStatus(@PathVariable Long id) throws Exception {
        service.trancarMatricula(id);
    }

    @GetMapping("/estudante/media/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Double retornaMediaAlunos() {

        return service.retonaMediaAluno();
    }

    @GetMapping(value = "/estudante/status/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('DIRETOR')")
    public List<Aluno> retornaAlunosStatus(@PathVariable String status) {

        return service.retonaAlunosStatus(status);
    }
}
