# Notes

## State of Quarkus

When I wanted to try out the REST resources with the Hibernate + Panache extension, I did not expect that two years later, the extension would still be labeled "Experimental" and even the base extension for Hibernate + Panache would be "Preview". Also on the quarkus.io page, the extension filtering functions are quite unusable even today. I hope more companies will see the benefits and switch from Spring in the future. Because of this, as I would rather want to have some kind of a template ready if it comes to use at work, I decided to go without generation of reactive REST resources, at least for now.

- https://quarkus.io/extensions/io.quarkus/quarkus-hibernate-reactive-panache/
- https://quarkus.io/extensions/io.quarkus/quarkus-hibernate-reactive-rest-data-panache/

## Java

### Generic Syntax & PECS = Producer Extends, Consumer Super

List<? extends Number> numbers = List.of(1, 2.0);
Number n = numbers.get(0); // okay
numbers.add(3); // ❌ nicht erlaubt

List<? super Integer> ints = new ArrayList<Number>();
ints.add(42); // okay
Number o = ints.get(0); // nur Object sicher

### Java Persistence API (JPA) and Java Database Connectivity (JDBC)

- JDBC (Spec -> Treiber + API): Direkte DB API als volle SQL Kontrolle: Connections, Statements, ResultSets
- JPA (Spec -> Impl. z.B. Hibernate): Klassenbasierter DB Zugriff durch Definition von Entities per Annotations -> JDBC Code-Generierung
