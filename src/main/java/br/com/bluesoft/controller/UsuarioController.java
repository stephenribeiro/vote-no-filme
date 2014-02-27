package br.com.bluesoft.controller;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.bluesoft.dao.JPAUtil;
import br.com.bluesoft.dao.UsuarioDAO;
import br.com.bluesoft.modelo.Usuario;

@Controller
public class UsuarioController {
    @RequestMapping("/novoUsuario")
    public String form() {
        return "usuario/adicionar";
    }

    @RequestMapping("/adicionarUsuario")
    public String adiciona(Usuario usuario) {
        EntityManager entityManager = new JPAUtil().getEntityManager();
        UsuarioDAO usuarioDAO = new UsuarioDAO(entityManager);
        usuarioDAO.adiciona(usuario);
        entityManager.close();
        return "voto/lista";
    }
}
