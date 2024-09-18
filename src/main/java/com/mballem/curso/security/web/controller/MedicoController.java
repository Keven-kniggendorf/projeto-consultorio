package com.mballem.curso.security.web.controller;

import com.mballem.curso.security.domain.Medico;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("medicos")
public class MedicoController {

    // abrir pagina de dados pessoais de medico para cadastro
    @RequestMapping({"/dados"})
    public String abrirPorMedico(Medico medico, ModelMap model) {
        return "medico/cadastro";
    }


}
