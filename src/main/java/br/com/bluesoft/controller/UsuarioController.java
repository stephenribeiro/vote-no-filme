package br.com.bluesoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsuarioController {
    @RequestMapping("/novoUsuario")
    public String form() {
        return "usuario/adicionar";
    }
}
