package br.com.bluesoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller de usuário
 * 
 * @author stephen.ribeiro
 */
@Controller
public class UsuarioController {
    /**
     * @return
     */
    @RequestMapping("/novoUsuario")
    public String form() {
        return "usuario/adicionar";
    }
}
