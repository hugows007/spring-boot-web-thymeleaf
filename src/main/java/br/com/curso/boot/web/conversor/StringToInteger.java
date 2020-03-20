package br.com.curso.boot.web.conversor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToInteger implements Converter<String, Integer> {
    @Override
    public Integer convert(String s) {
        s = s.trim();

        if(s.matches("[0-9]+")){
            return Integer.valueOf(s);
        }

        return null;
    }
}
