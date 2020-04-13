package br.com.curso.boot.web.controller;

import br.com.curso.boot.domain.Cargo;
import br.com.curso.boot.domain.Departamento;
import br.com.curso.boot.service.CargoService;
import br.com.curso.boot.service.DepartamentoService;
import br.com.curso.boot.util.PaginacaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cargos")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping("/cadastrar")
    public String cadastrar(Cargo cargo) {
        return "cargo/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap modelMap,
                         @RequestParam(value = "page", defaultValue = "1") Integer page,
                         @RequestParam(value = "dir", defaultValue = "asc") String dir) {

        PaginacaoUtil<Cargo> pageCargo = cargoService.buscarPorPagina(page, dir);

        modelMap.addAttribute("pageCargo", pageCargo);
        return "cargo/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "cargo/cadastro";
        }

        cargoService.salvar(cargo);
        attributes.addFlashAttribute("success", "Cargo inserido com sucesso.");
        return "redirect:/cargos/cadastrar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap modelMap) {
        modelMap.addAttribute("cargo", cargoService.buscarPorId(id));
        return "cargo/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "cargo/cadastro";
        }

        cargoService.editar(cargo);
        attributes.addFlashAttribute("success", "Cargo editado com sucesso");
        return "redirect:/cargos/cadastrar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap modelMap) {
        if (cargoService.cargoTemFuncionarios(id)) {
            modelMap.addAttribute("fail", "Cargo não removido. Possui funcionário(s) vinculado(s)");
        } else {
            cargoService.excluir(id);
            modelMap.addAttribute("success", "Cargo removido com sucesso");
        }

        return listar(modelMap, null, null);
    }

    @ModelAttribute("departamentos")
    public List<Departamento> listaDeDepartamentos() {
        return departamentoService.buscarTodos();
    }
}
