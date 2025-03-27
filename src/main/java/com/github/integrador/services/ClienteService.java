package com.github.integrador.services;

import com.github.integrador.dtos.ClienteGetDto;
import com.github.integrador.dtos.ClientePostDto;
import com.github.integrador.models.Cliente;
import com.github.integrador.repositories.ClienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepo clienteRepo;

    public List<ClienteGetDto> getAll (int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return clienteRepo.findAll(pageable)
            .stream()
            .map(Cliente::mapToDto)
            .collect(Collectors.toList());
    }

    /*Pageable pageable = PageRequest.of(page, count);
        return clienteRepo.findById(id, pageable)
            .orElseThrow()
            .stream()
            .map(Cliente::mapToDto)
            .collect(Collectors.toList());
     */

    public ClienteGetDto getOne (Integer id) {
        Optional<Cliente> clienteOptional = clienteRepo.findById(id);
        if (!clienteOptional.isPresent()){
            //
        }

        Cliente cliente = clienteOptional.orElseThrow();;
        return Cliente.mapToDto(cliente);
    }

    public ClienteGetDto post(ClientePostDto dto) {
        Cliente cliente = Cliente.mapToObj(dto);
        cliente = clienteRepo.save(cliente);
        return Cliente.mapToDto(cliente);
    }

    public ClienteGetDto patch(Integer id, ClientePostDto dto) {
        Cliente cliente = clienteRepo.findById(id).orElseThrow();
        cliente = Cliente.mapToObj(dto);
        cliente.setId(id);
        cliente = clienteRepo.save(cliente);
        return Cliente.mapToDto(cliente);
    }

    public void delete(Integer id) {
        clienteRepo.deleteById(id);
    }
}