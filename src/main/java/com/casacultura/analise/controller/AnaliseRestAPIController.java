
package com.casacultura.analise.controller;

import com.casacultura.analise.model.Analises;
import com.casacultura.analise.service.AnaliseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analise")
public class AnaliseRestAPIController {
    
     
    @Autowired
    AnaliseService analiseService;
    
    @PostMapping("/adicionar")
    public ResponseEntity<Analises> addFilme(@RequestBody Analises an) {
        var analiseCriado = analiseService.criarAnalise(an);
        return new ResponseEntity<>(analiseCriado, HttpStatus.CREATED);
    }

    @GetMapping("/pesquisar/{idFilme}")
    public ResponseEntity<List> pesquisar(@PathVariable Integer idFilme){
       List<Analises> lista = analiseService.listarTodasAnalisesPorIdFilme(idFilme);
       return new ResponseEntity<>(lista,HttpStatus.OK);
    }
    
    @DeleteMapping("/excluir/{idAnalise}")
    public ResponseEntity<?> delete(@PathVariable Integer idAnalise){
        analiseService.excluirAnalise(idAnalise);
        return new ResponseEntity<>(HttpStatus.OK);
    }    
    
    @DeleteMapping("/excluir-todos-filmes/{idFilme}")
    public ResponseEntity<?> deletePorFilme(@PathVariable Integer idFilme){
        analiseService.excluirTodasAnalisesPorFilme(idFilme);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
