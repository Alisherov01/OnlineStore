package com.example.OnlineStore.mappers;

import com.example.OnlineStore.dto.CategoriesDto;
import com.example.OnlineStore.entity.Categories;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface CategoriesMapper {
//    private final ModelMapper modelMapper;
//
//    public CategoriesMapper() {
//        this.modelMapper = new ModelMapper();
//    }
//
//    public CategoriesDto mapToDto(Categories categori) {
//        return modelMapper.map(categori, CategoriesDto.class);
//    }
//
//    public Categories mapToModel(CategoriesDto dto) {
//        return modelMapper.map(dto, Categories.class);
//    }
    Categories mapToDto(CategoriesDto dto);
}
