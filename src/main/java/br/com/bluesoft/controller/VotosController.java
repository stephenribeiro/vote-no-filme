package br.com.bluesoft.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.bluesoft.dao.FilmeDAO;
import br.com.bluesoft.dao.VotoDAO;
import br.com.bluesoft.modelo.CombinacaoFilme;
import br.com.bluesoft.modelo.CombinadorDeFilmes;
import br.com.bluesoft.modelo.ComputadorDeVotos;
import br.com.bluesoft.modelo.Filme;
import br.com.bluesoft.modelo.Usuario;
import br.com.bluesoft.modelo.Voto;

@Transactional
@Controller
public class VotosController {

    private final FilmeDAO filmeDAO;

    private final VotoDAO votoDAO;

    private final CombinadorDeFilmes combinadorDeFilmes;

    private final ComputadorDeVotos computadorDeVotos;

    private List<CombinacaoFilme> combinacoes;

    @Autowired
    public VotosController(FilmeDAO filmeDAO, VotoDAO votoDAO, CombinadorDeFilmes combinadorDeFilmes, ComputadorDeVotos computadorDeVotos) {
        this.filmeDAO = filmeDAO;
        this.votoDAO = votoDAO;
        this.combinadorDeFilmes = combinadorDeFilmes;
        this.computadorDeVotos = computadorDeVotos;
        this.combinacoes = this.obtemCombinacoesPossiveis();
    }

    @RequestMapping("/votar")
    public String votar(Model model, HttpSession session) {
        List<Filme> filmesSelecionados = this.obtemOuCriaFilmesSelecionados(session);
        filmesSelecionados.clear();
        return "redirect:novoVoto";
    }

    @RequestMapping("/novoVoto")
    public String novoVoto(Model model, HttpSession session) {
        List<Filme> filmesSelecionados = this.obtemOuCriaFilmesSelecionados(session);
        model.addAttribute("filmes", combinacoes.get(filmesSelecionados.size()).getAsList());
        return "voto/adicionar";
    }

    @RequestMapping("adicionarVoto")
    public String adicionaVoto(Filme filme, HttpSession session) {
        List<Filme> filmesSelecionados = this.obtemOuCriaFilmesSelecionados(session);
        filmesSelecionados.add(filme);

        if (filmesSelecionados.size() < this.combinacoes.size()) {
            return "redirect:novoVoto";
        }

        return "usuario/adicionar";
    }

    @RequestMapping("computarVotos")
    public String computarVotos(Usuario usuario, HttpSession session) {
        List<Filme> filmesSelecionados = this.obtemOuCriaFilmesSelecionados(session);

        for (Filme filme : filmesSelecionados) {
            this.votoDAO.adiciona(new Voto(usuario, filme));
        }

        session.setAttribute("usuario", usuario);

        return "redirect:rankVotos";
    }

    @RequestMapping("rankVotos")
    public String rankVotos(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        TreeMap<Filme, Integer> rankUsuario =
                this.computadorDeVotos.getRankMaisVotados(this.votoDAO.listaPorUsuario(usuario), this.filmeDAO.listaTodos());
        model.addAttribute("rankUsuario", rankUsuario.keySet());

        TreeMap<Filme, Integer> rankGeral =
                this.computadorDeVotos.getRankMaisVotados(this.votoDAO.listaTodos(), this.filmeDAO.listaTodos());
        model.addAttribute("rankGeral", rankGeral.keySet());

        session.invalidate();
        return "voto/rank";
    }

    private List<Filme> obtemOuCriaFilmesSelecionados(HttpSession session) {
        List<Filme> filmesSelecionados = null;
        if (session.getAttribute("filmesSelecionados") != null) {
            filmesSelecionados = (List) session.getAttribute("filmesSelecionados");
        } else {
            filmesSelecionados = new ArrayList<Filme>();
            session.setAttribute("filmesSelecionados", filmesSelecionados);
        }
        return filmesSelecionados;
    }

    private List<CombinacaoFilme> obtemCombinacoesPossiveis() {
        List<Filme> filmes = this.filmeDAO.listaTodos();
        List<CombinacaoFilme> combinacoes = this.combinadorDeFilmes.combinar(filmes, 2);
        return combinacoes;
    }
}
