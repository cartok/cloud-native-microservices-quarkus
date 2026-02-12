package de.cartok.quarkus.tutorial.backoffice.article;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArticleRepository implements PanacheRepository<ArticleEntity> {
}
