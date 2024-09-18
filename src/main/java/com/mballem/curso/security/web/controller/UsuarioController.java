package com.mballem.curso.security.web.controller;

import com.mballem.curso.security.domain.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("u")
public class UsuarioController {

    // abrir cadastro de usuario (medico, paciente, secretario)
    @GetMapping("/novo/cadastro/usuario")
    public String cadastroPorAdimParaAdminMedicoPaciente(Usuario usuario) {
        return "usuario/cadastro";
    }

}
