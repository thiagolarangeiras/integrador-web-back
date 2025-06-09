package com.github.integrador.DadosEmpresa;

import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DadosEmpresaService {
    @Autowired private DadosEmpresaRepo repo;

    public DadosEmpresaGetDto get() {
        DadosEmpresa obj = repo.findById(1).orElse(null);
        return DadosEmpresa.mapToDto(obj);
    }

    public DadosEmpresaGetDto post(DadosEmpresaPostDto dto) {
        DadosEmpresa obj = obj = DadosEmpresa.mapToObj(dto);
        obj.setId(1);
        try {
            obj = repo.save(obj);
        } catch (Exception e){
            obj.setId(null);
            obj = repo.save(obj);
        }

        return DadosEmpresa.mapToDto(obj);
    }

    public DadosEmpresaGetDto patch(DadosEmpresaPostDto dto) {
        return post(dto);
    }
}
