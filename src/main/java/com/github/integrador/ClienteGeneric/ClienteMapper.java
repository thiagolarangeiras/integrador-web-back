package com.github.integrador.ClienteGeneric;

import com.github.integrador.Cliente.Cliente;
import com.github.integrador.Cliente.ClienteGetDto;
import com.github.integrador.Cliente.ClientePostDto;
import com.github.integrador.infra.GenericMapper;
import org.springframework.stereotype.Service;

@Service
public class ClienteMapper implements GenericMapper<Cliente, ClienteGetDto, ClientePostDto> {
    public ClienteGetDto mapToDto(Cliente obj) {
        return new ClienteGetDto(
                obj.getId(),
                obj.getIdVendedor(),
                obj.getNomePessoa(),
                obj.getNomeEmpresa(),
                obj.getCpf(),
                obj.getCnpj(),
                obj.getEndereco(),
                obj.getTelefone(),
                obj.getEmail(),
                obj.getDescricao()
        );
    }

    public  Cliente mapToObj(ClientePostDto dto) {
        Cliente cliente = new Cliente();
        cliente.setIdVendedor(dto.idVendedor());
        cliente.setNomePessoa(dto.nomePessoa());
        cliente.setNomeEmpresa(dto.nomeEmpresa());
        cliente.setCpf(dto.cpf());
        cliente.setCnpj(dto.cnpj());
        cliente.setEndereco(dto.endereco());
        cliente.setTelefone(dto.telefone());
        cliente.setEmail(dto.email());
        cliente.setDescricao(dto.descricao());
        return cliente;
    }
}
