-- No need to generate schema and tables cause Hibernate will do (at least in Dev Service mode).
-- Example insertion:
insert into smartbar.ArticleEntity_SEQ (name, price, description, picture)
values ("Fish and Chips",
        1337,
        "The best fish and chips on the island can be eaten at Breege Harbor.",
        "https://picsum.photos/200")
