package br.com.curso.boot.service;

import br.com.curso.boot.domain.Cargo;
import br.com.curso.boot.util.PaginacaoUtil;

import java.util.List;

public interface CargoService {
    void salvar(Cargo cargo);

    void editar(Cargo cargo);

    void excluir(Long id);

    Cargo buscarPorId(Long id);

    List<Cargo> buscarTodos();

    PaginacaoUtil<Cargo> buscarPorPagina(int pagina, String direcao);

    boolean cargoTemFuncionarios(Long id);
}
