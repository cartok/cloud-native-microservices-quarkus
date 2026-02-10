package de.cartok.quarkus.tutorial.backoffice.article;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import de.cartok.quarkus.tutorial.backoffice.api.model.ApiArticle;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface ArticleMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "category", ignore = true)
  @Mapping(target = "pictureBase64", source = "picture")
  void mapToEntity(ApiArticle apiObject, @MappingTarget ArticleEntity entityObject);

  @Mapping(target = "picture", source = "pictureBase64")
  ApiArticle mapToDto(ArticleEntity entityObject);
}
