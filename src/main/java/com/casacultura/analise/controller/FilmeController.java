
package com.casacultura.analise.controller;

import com.casacultura.analise.model.Analises;
import com.casacultura.analise.model.Filme;
import com.casacultura.analise.service.AnaliseService;
import com.casacultura.analise.service.FilmeService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


  
    
@Controller
public class FilmeController {
    
    
  @Autowired 
    FilmeService filmeService;
    
    @Autowired 
    AnaliseService analiseService; 
    
  

    @GetMapping("/inicio")//eh definido a URL que sera chamada no navegador
    public String inicio() {
        return "index";//sera apontado o arquivo HTML que sera chamado (template)
    }

    @GetMapping("/inserir")//responsavel por chamar o formulário
    public String cadastro(Model model) {//vou chamar os atributos que estão dentro do model
        model.addAttribute("filme", new Filme());
        return "cadastro";//sera apontado o arquivo HTML que sera chamado (template)
    }
    
      @GetMapping("/alterar")
    public String alterarFilme(Model model, @RequestParam String id){
        Integer idFilme = Integer.parseInt(id);
        Filme filmeEncontrado = filmeService.buscarPorId(idFilme);
        model.addAttribute("filme", filmeEncontrado);
        return "cadastro";
    }

    @PostMapping("/gravar")//responsável por receber o formulário
    public String processarFromulario(Model model,@ModelAttribute Filme filme ) {
        
          if (filme.getId()!=null) {
            filmeService.atualizar(filme.getId(), filme);
        } else {
            filmeService.criar(filme);
        }
        return "redirect:/listar"; 
     
    }
    
      @GetMapping("/excluir")
    public String excluirFilme(Model model, @RequestParam String id){
        Integer idFilme = Integer.parseInt(id);
        analiseService.excluirTodasAnalisesPorFilme(idFilme);
        filmeService.excluir(idFilme);
        return "redirect:/listar";
    }  
    
    

    @GetMapping("/listar") //eh definido a URL que sera chamada no navegador
    public String listagem(Model model) {
        model.addAttribute("lista",  filmeService.listarTodos());
        return "listagem";//sera apontado o arquivo HTML que sera chamado (template)
    }
    
    @GetMapping("/excluirAnalise")
    public String exibirAnalise(@RequestParam String id, Model model){
        Integer idAnalise = Integer.parseInt(id);
        
        Analises objAnalise = analiseService.buscarAnalisePorId(idAnalise);
        
        analiseService.excluirAnalise(idAnalise);
        
       Integer idFilme = objAnalise.getFilme().getId();
        return "redirect:/exibir?id=" + idFilme;
    }  
    
        @GetMapping("/exibir")
    public String exibirDados(@RequestParam String id, Model model){
        Integer idFilme = Integer.parseInt(id);
        
        Filme filmeEncontrado = new Filme();
        filmeEncontrado = filmeService.buscarPorId(idFilme);
        
        List<Analises> analisesEncontrado = new ArrayList<>();
        analisesEncontrado = analiseService.listarTodasAnalisesPorIdFilme(idFilme);
        
        model.addAttribute("filme", filmeEncontrado);
        model.addAttribute("analise", new Analises());
        model.addAttribute("analises", analisesEncontrado);
        return "exibir";
    }
    
     @PostMapping("/gravar-analise") 
    public String processarAnalise(@ModelAttribute Filme filme,@ModelAttribute Analises analise,Model model){
        analise.setFilme(filme);
        analiseService.criarAnalise(analise);
        return "redirect:/listar";
    } 
}
    




