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
                null,
                obj.getNomePessoa(),
                obj.getNomePessoa(),
                obj.getNomeEmpresa(),
                obj.getNomeFantasia(),
                obj.getDescricao(),
                obj.getTipo(),
                obj.getCpfCnpj(),
                obj.getTelefone(),
                obj.getEmail(),
                obj.getCep(),
                obj.getRua(),
                obj.getNumero(),
                obj.getComplemento(),
                obj.getBairro(),
                obj.getCidade(),
                obj.getEstado()
        );
    }

    public  Cliente mapToObj(ClientePostDto dto) {
        Cliente cliente = new Cliente();
        cliente.setIdVendedor(dto.idVendedor());
        cliente.setNomePessoa(dto.nomePessoa());
        cliente.setNomeEmpresa(dto.nomeEmpresa());
        cliente.setNomeFantasia(dto.nomeFantasia());
        cliente.setDescricao(dto.descricao());
        cliente.setTipo(dto.tipo());
        cliente.setCpfCnpj(dto.cpfCnpj());
        cliente.setTelefone(dto.telefone());
        cliente.setEmail(dto.email());
        cliente.setCep(dto.cep());
        cliente.setRua(dto.rua());
        cliente.setNumero(dto.numero());
        cliente.setComplemento(dto.complemento());
        cliente.setBairro(dto.bairro());
        cliente.setCidade(dto.cidade());
        cliente.setEstado(dto.estado());
        return cliente;
    }
}
