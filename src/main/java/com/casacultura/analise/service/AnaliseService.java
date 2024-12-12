
package com.casacultura.analise.service;

import com.casacultura.analise.model.Analises;
import com.casacultura.analise.repository.AnalisesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnaliseService {
    
    @Autowired
    AnalisesRepository analisesRepository;
    
    
    //CRUD
    public Analises criarAnalise(Analises an) {
        an.setId(null);
      analisesRepository.save(an);
        return an;
    }

    public Analises buscarAnalisePorId(Integer id) {
        return analisesRepository.findById(id).orElseThrow(); //caso não exista retorna sem erro!
    }

    public Analises atualizarAnalise(Integer id, Analises comEnviado) {
      Analises analiseEncontrado = buscarAnalisePorId(id); //valida se existe o id informado
      analiseEncontrado.setAnalise(analiseEncontrado.getAnalise());
        analisesRepository.save(analiseEncontrado);
        return analiseEncontrado;
    }
    
    public void excluirAnalise(Integer id) {
        Analises analiseEncontrado = buscarAnalisePorId(id); //valida se existe o id informado
        analisesRepository.deleteById(analiseEncontrado.getId());
    }

    public List<Analises> listarTodasAnalisesPorIdFilme(Integer id) {
        return analisesRepository.findByFilmeId(id); //retorna todos os comentarios de um id filme especifico
    }    
    
    public void excluirTodasAnalisesPorFilme(Integer id){
        for(Analises reg: listarTodasAnalisesPorIdFilme(id)){
           excluirAnalise(reg.getId());
        }
    }

  
}
