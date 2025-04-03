package com.github.integrador.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GenericService <
        TModel extends GenericModel,
        TGetDto extends Record,
        TPostDto extends Record,
        TRepo extends JpaRepository<TModel, Integer>,
        TMapper extends GenericMapper<TModel, TGetDto, TPostDto>
> {
    @Autowired
    private TRepo repo;

    @Autowired
    private TMapper mapper;

    public List<TGetDto> getAll (int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return repo.findAll(pageable)
                .stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    public TGetDto getOne (Integer id) {
        Optional<TModel> optional = repo.findById(id);
        TModel obj = optional.orElseThrow();;
        return mapper.mapToDto(obj);
    }

    public TGetDto post(TPostDto dto) {
        TModel obj = mapper.mapToObj(dto);
        obj = repo.save(obj);
        return mapper.mapToDto(obj);
    }

    public TGetDto patch(Integer id, TPostDto dto) {
        TModel obj = repo.findById(id).orElseThrow();
        obj = mapper.mapToObj(dto);
        obj.setId(id);
        obj = repo.save(obj);
        return mapper.mapToDto(obj);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
