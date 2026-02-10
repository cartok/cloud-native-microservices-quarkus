package de.cartok.quarkus.tutorial.backoffice.table;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import de.cartok.quarkus.tutorial.backoffice.api.model.ApiTable;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface TableMapper {
  @Mapping(target = "id", ignore = true)
  void mapToEntity(ApiTable apiObject, @MappingTarget TableEntity entityObject);

  ApiTable mapToDto(TableEntity entityObject);
}
