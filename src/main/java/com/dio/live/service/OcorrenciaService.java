package com.dio.live.service;

import com.dio.live.model.Ocorrencia;
import com.dio.live.repository.OcorrenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OcorrenciaService {
    @Autowired
    OcorrenciaRepository ocorrenciaRepository;


    public Ocorrencia create(Ocorrencia ocorrencia){
        return ocorrenciaRepository.save(ocorrencia);
    }

    public List<Ocorrencia> listAll(){
        return ocorrenciaRepository.findAll();
    }

    public Ocorrencia findByID(Long id) throws NotFoundException {
       return verifiIfExists(id);// caso nao enconmtre o elemento lanca a excecao e retorna 4040 not found
    }

    public Ocorrencia update(Ocorrencia ocorrencia) throws NotFoundException {
        verifiIfExists(ocorrencia.getId());
        return ocorrenciaRepository.save(ocorrencia);
    }

    public void delete(Long id) throws NotFoundException {
        verifiIfExists(id); // caso nao encontre o id retorna 404 pra requisição
        ocorrenciaRepository.deleteById(id);
    }




    /*
    * Recebe um Long id e verifica se ele existe no repositorio
    * caso seja encontrado retorna um objeto do tipo Ocorrencia que esta salvo caso contrario retorna um exceção not Foud e passa 404 pra request
    * */
    public Ocorrencia verifiIfExists(Long id) throws NotFoundException {
        return ocorrenciaRepository
                .findById(id)
                .orElseThrow(()-> new NotFoundException(id));
    }


}
