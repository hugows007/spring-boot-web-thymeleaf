package br.com.curso.boot.service;

import br.com.curso.boot.domain.Cargo;
import br.com.curso.boot.domain.Departamento;
import br.com.curso.boot.util.PaginacaoUtil;

import java.util.List;

public interface DepartamentoService {
    void salvar(Departamento departamento);

    void editar(Departamento departamento);

    void excluir(Long id);

    Departamento buscarPorId(Long id);

    List<Departamento> buscarTodos();

    boolean departamentoTemCargos(Long id);
}
