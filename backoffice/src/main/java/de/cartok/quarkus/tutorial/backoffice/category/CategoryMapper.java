package de.cartok.quarkus.tutorial.backoffice.category;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import de.cartok.quarkus.tutorial.backoffice.api.model.ApiCategory;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface CategoryMapper {
  @Mapping(target = "id", ignore = true)
  void mapToEntity(ApiCategory apiObject, @MappingTarget Category entityObject);

  ApiCategory mapToDto(Category entityObject);
}
