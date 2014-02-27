package br.com.bluesoft.controller;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.bluesoft.dao.JPAUtil;
import br.com.bluesoft.dao.VotoDAO;
import br.com.bluesoft.modelo.Voto;

@Controller
public class VotosController {
    @RequestMapping("/novoVoto")
    public String form() {
        return "voto/adicionar";
    }

    @RequestMapping("/adicionarVoto")
    public String adiciona(Voto voto) {
        EntityManager entityManager = new JPAUtil().getEntityManager();
        VotoDAO votoDAO = new VotoDAO(entityManager);
        votoDAO.adiciona(voto);
        entityManager.close();
        return "voto/computado";
    }

    @RequestMapping("listaVotos")
    public String lista(Model model) {
        EntityManager entityManager = new JPAUtil().getEntityManager();
        VotoDAO votoDAO = new VotoDAO(entityManager);
        model.addAttribute("votos", votoDAO.listaTodos());
        return "voto/lista";
    }
}
