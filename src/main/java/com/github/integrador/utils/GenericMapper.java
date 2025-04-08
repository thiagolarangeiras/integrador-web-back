package com.github.integrador.utils;

import org.springframework.stereotype.Service;

@Service
public interface GenericMapper<TModel extends GenericModel, TGetDto extends Record, TPostDto extends Record> {
    TGetDto mapToDto(TModel obj);
    TModel mapToObj(TPostDto dto);
}
