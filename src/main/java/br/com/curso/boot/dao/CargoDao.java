package br.com.curso.boot.dao;

import br.com.curso.boot.domain.Cargo;
import br.com.curso.boot.util.PaginacaoUtil;

import java.util.List;

public interface CargoDao {
    void save(Cargo cargo);

    void update(Cargo cargo);

    void delete(Long id);

    Cargo findById(Long id);

    List<Cargo> findAll();

    PaginacaoUtil<Cargo> buscaPaginada(int pagina, String direcao);
}
