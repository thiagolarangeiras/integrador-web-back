package com.github.integrador.Cliente;

import com.github.integrador.infra.GenericModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente extends GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idVendedor;

    private String nomePessoa;
    private String nomeEmpresa;
    private String cpf;
    private String cnpj;
    private String endereco;
    private String telefone;
    private String email;
    private String descricao;

    //@ManyToOne
    //@JoinColumn(name = "idVendedor", insertable=false, updatable=false)
    //@JsonIgnore
    //private Vendedor vendedor;

    //Mappers
    public static ClienteGetDto mapToDto(Cliente obj) {
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

    public static Cliente mapToObj(ClientePostDto dto) {
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
