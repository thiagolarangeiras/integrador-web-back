package com.github.integrador.Cliente;

import com.github.integrador.Fornecedor.FornecedorGetDto;
import com.github.integrador.Marca.MarcaGetDto;
import com.github.integrador.Produto.Produto;
import com.github.integrador.Produto.ProdutoGetDto;
import com.github.integrador.Vendedor.VendedorGetDto;
import com.github.integrador.enums.EstadosBr;
import com.github.integrador.enums.TipoCliente;
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
    private String nomeFantasia;
    private String descricao;
    private TipoCliente tipo;
    private String cpfCnpj;
    private String telefone;
    private String email;
    private String cep;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private EstadosBr estado;

    //@ManyToOne
    //@JoinColumn(name = "idVendedor", insertable=false, updatable=false)
    //@JsonIgnore
    //private Vendedor vendedor;

    //Mappers
    public static ClienteGetDto mapToDto(Cliente obj) {
        return new ClienteGetDto(
                obj.getId(),
                obj.getIdVendedor(),
                null,
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

    public static ClienteGetDto mapToDto(Cliente obj, VendedorGetDto dtoVendedor) {
        return new ClienteGetDto(
                obj.getId(),
                obj.getIdVendedor(),
                dtoVendedor,
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

    public static Cliente mapToObj(ClientePostDto dto) {
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