package br.com.curso.boot.web.conversor;

import br.com.curso.boot.domain.Cargo;
import br.com.curso.boot.domain.Departamento;
import br.com.curso.boot.domain.Funcionario;
import br.com.curso.boot.service.CargoService;
import br.com.curso.boot.service.DepartamentoService;
import br.com.curso.boot.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCargoConverter implements Converter<String, Cargo> {

    @Autowired
    private CargoService service;

    @Override
    public Cargo convert(String text) {
        if(text.isEmpty()){
            return null;
        }

        Long id = Long.valueOf(text);

        return service.buscarPorId(id);
    }
}
