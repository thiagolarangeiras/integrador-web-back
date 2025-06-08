package com.github.integrador.DadosEmpresa;

import com.github.integrador.enums.EstadosBr;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dados-empresa")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosEmpresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String cnpj;
    private String email;
    private String telefone;

    private String cep;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private EstadosBr estado;

    //Mappers
    public static DadosEmpresaGetDto mapToDto(DadosEmpresa obj) {
        return new DadosEmpresaGetDto(
                obj.getId(),
                obj.getNome(),
                obj.getCnpj(),
                obj.getEmail(),
                obj.getTelefone(),
                obj.getCep(),
                obj.getRua(),
                obj.getNumero(),
                obj.getComplemento(),
                obj.getBairro(),
                obj.getCidade(),
                obj.getEstado()
        );
    }

    public static DadosEmpresa mapToObj(DadosEmpresaPostDto dto) {
        DadosEmpresa obj = new DadosEmpresa();
        obj.setNome(dto.getNome());
        obj.setCnpj(dto.getCnpj());
        obj.setEmail(dto.getEmail());
        obj.setTelefone(dto.getTelefone());
        obj.setCep(dto.getCep());
        obj.setRua(dto.getRua());
        obj.setNumero(dto.getNumero());
        obj.setComplemento(dto.getComplemento());
        obj.setBairro(dto.getBairro());
        obj.setCidade(dto.getCidade());
        obj.setEstado(dto.getEstado());
        return obj;
    }
}