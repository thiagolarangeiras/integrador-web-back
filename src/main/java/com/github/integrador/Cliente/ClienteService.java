package com.github.integrador.Cliente;

import com.github.integrador.Fornecedor.FornecedorGetDto;
import com.github.integrador.Marca.MarcaGetDto;
import com.github.integrador.Produto.Produto;
import com.github.integrador.Produto.ProdutoGetDto;
import com.github.integrador.Vendedor.VendedorGetDto;
import com.github.integrador.Vendedor.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired private ClienteRepo clienteRepo;
    @Autowired private VendedorService vendedorService;

    public List<ClienteGetDto> getAll (int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        List<ClienteGetDto> dtos = new ArrayList<>();
        for (Cliente cliente : clienteRepo.findAll(pageable)){
            VendedorGetDto dtoVendedor = vendedorService.getOne(cliente.getIdVendedor());
            dtos.add(Cliente.mapToDto(cliente, dtoVendedor));
        }
        return dtos;
    }

    public void addVendedorDto(Page<Cliente> clientes, List<ClienteGetDto> dtos){
        for (Cliente cliente : clientes){
            VendedorGetDto dtoVendedor = vendedorService.getOne(cliente.getIdVendedor());
            dtos.add(Cliente.mapToDto(cliente, dtoVendedor));
        }
    }

    public List<ClienteGetDto> getAllFilter (Integer page, Integer count, String nome) {
        Pageable pageable = PageRequest.of(page, count);
        List<ClienteGetDto> dtos = new ArrayList<>();

        Optional<Page<Cliente>> clienteOp = clienteRepo.findByNomePessoaContainingIgnoreCase(nome, pageable);
        if (clienteOp.isPresent())
            addVendedorDto(clienteOp.get(), dtos);

        clienteOp = clienteRepo.findByNomeEmpresaContainingIgnoreCase(nome, pageable);
        if (clienteOp.isPresent())
            addVendedorDto(clienteOp.get(), dtos);

        clienteOp = clienteRepo.findByNomeFantasiaContainingIgnoreCase(nome, pageable);
        if (clienteOp.isPresent())
            addVendedorDto(clienteOp.get(), dtos);

        clienteOp = clienteRepo.findByDescricaoContainingIgnoreCase(nome, pageable);
        if (clienteOp.isPresent())
            addVendedorDto(clienteOp.get(), dtos);

        return dtos;
    }

    public ClienteGetDto getOne (Integer id) {
        if(id == null) return null;
        Cliente cliente = clienteRepo.findById(id).get();
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