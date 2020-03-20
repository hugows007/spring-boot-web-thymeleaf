package br.com.curso.boot.web.validator;

import br.com.curso.boot.domain.Funcionario;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

public class FuncionarioValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Funcionario.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Funcionario funcionario = (Funcionario) object;

        LocalDate entrada = funcionario.getDataEntrada();

        if(funcionario.getDataSaida() != null){
            if(funcionario.getDataSaida().isBefore(entrada)){
                errors.rejectValue("dataSaida", "PosteriorDataEntrada.funcionario.dataSaida");
            }
        }

    }
}
