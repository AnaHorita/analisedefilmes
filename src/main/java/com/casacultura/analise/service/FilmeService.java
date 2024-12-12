
package com.casacultura.analise.service;

import com.casacultura.analise.model.Filme;
import com.casacultura.analise.repository.FilmeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmeService {
    
    @Autowired
    FilmeRepository filmeRepository;
    
    //CRUD
    public Filme criar(Filme fim) {
        fim.setId(null);
        filmeRepository.save(fim);
        return fim;
    }
    
    public List<Filme> listarTodos(){
        return filmeRepository.findAll();
    }
    
    public Filme buscarPorId(Integer id) {
        return filmeRepository.findById(id).orElseThrow(); //caso não exista retorna sem erro!
    }
    
    public void excluir(Integer id) {
        Filme filmeEncontrado = buscarPorId(id); //valida se existe o id informado
        filmeRepository.deleteById(filmeEncontrado.getId());
    }
    
    public Filme atualizar(Integer id, Filme filmeEnviado) {
        Filme filmeEncontrado = buscarPorId(id); //valida se existe o id informado
        filmeEncontrado.setTitulo(filmeEnviado.getTitulo());
        filmeEncontrado.setSinopse(filmeEnviado.getSinopse());
        filmeEncontrado.setGenero(filmeEnviado.getGenero());
        filmeEncontrado.setAno(filmeEnviado.getAno());
        filmeEncontrado.setAvaliado(filmeEnviado.isAvaliado());
        filmeRepository.save(filmeEncontrado);
        return filmeEncontrado;
    }
    
} 
  