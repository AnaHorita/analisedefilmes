
package com.casacultura.analise.controller;

import com.casacultura.analise.model.Filme;
import com.casacultura.analise.service.FilmeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filme")
public class FilmeRestAPIController {
    
    @Autowired
    FilmeService filmeService;   
    
    @PostMapping("/adicionar")
    public ResponseEntity<Filme> addFilme(@RequestBody Filme fim) {
        var filmeCriado = filmeService.criar(fim);
        return new ResponseEntity<>(filmeCriado, HttpStatus.CREATED);
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List> listar(){
        List<Filme> listagem = filmeService.listarTodos();
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }
    
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        filmeService.excluir(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<Filme> pesquisar(@PathVariable Integer id){
        Filme filmeEncontrado = filmeService.buscarPorId(id);
        return new ResponseEntity<>(filmeEncontrado,HttpStatus.OK);
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Filme> editFilme(@PathVariable Integer id,@RequestBody Filme fim) {
        var filmeEditado = filmeService.atualizar(id, fim);
        return new ResponseEntity<>(filmeEditado,HttpStatus.OK);
    }
     
}
