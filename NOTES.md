# Notes

## Quarkus

### Thoughts about state of Hibernate + Panache (reactive) and Quarkus in general

When I wanted to try out the REST resources with the Hibernate + Panache extension, I did not expect that two years later, the extension would still be labeled "Experimental" and even the base extension for Hibernate + Panache would be "Preview". Also on the quarkus.io page, the extension filtering functions are quite unusable even today. I hope more companies will see the benefits and switch from Spring in the future. Because of this, as I would rather want to have some kind of a template ready if it comes to use at work, I decided to go without generation of reactive REST resources, at least for now.

- https://quarkus.io/extensions/io.quarkus/quarkus-hibernate-reactive-panache/
- https://quarkus.io/extensions/io.quarkus/quarkus-hibernate-reactive-rest-data-panache/

### Security JPA

- It did not understand version 2b of bcrypt, had to change version via string replacement:
    ```zsh
    mkpasswd -m bcrypt foo | sd '^\$2b' '$$2a'
    ```
- Auth header generation:
    ```zsh
    echo "Authorization: Basic $(printf 'bob:foo' | base64)"
    ```

### Self-signed SSL

#### Issues

1. Vermute Probleme mit Zertifikaten die CN nicht auf eine Addresse und auch kein SAN gesetzt haben.

- Curl failed
  - mit `--cacert`
  - mit `-k`?
- IDEA HTTP Client failed ebenfalls und man bekommt keinen Dialog um das Zertifikat zu erlauben.

2. Der IDEA HTTP Client Bug: Die Base URL wird reused, wodurch ich nicht abwechselnd http & https eines Endpoints testen kann.

3. Die `@TestSecurity` Annotation

- Wenn ich darüber die security disable werden `user` und `role` auch nicht validiert, es macht also keinen Sinn die zu setzen.

<pre>
Truststore
├─ Certificate Authority
│ ├─ Certificate A
│ ├─ Certificate B
│ └─ Certificate C
└─ Certificate X
</pre>

```zsh
# Create keystore incl. certificate
keytool -genkeypair \
  -keyalg Ed25519 \
  -storetype PKCS12 \
  -validity 3650 \
  -dname "CN=Ezreal, OU=Research, O=Piltover Weapons, L=Piltover, ST=Runeterra, C=PT" \
  -ext SAN=DNS:localhost,DNS:127.0.0.1 \
  -keypass changeit \
  -storepass changeit \
  -keystore keystore.p12

# Export certificate from keystore
keytool -exportcert \
  -keystore keystore.p12 \
  -storetype PKCS12 \
  -storepass changeit \
  -rfc \
  -file certificate.crt
```

## Java

### Generic Syntax & PECS = Producer Extends, Consumer Super

```
List<? extends Number> numbers = List.of(1, 2.0);
Number n = numbers.get(0); // okay
numbers.add(3); // ❌ nicht erlaubt
```

```
List<? super Integer> ints = new ArrayList<Number>();
ints.add(42); // okay
Number o = ints.get(0); // nur Object sicher
```

### Java Persistence API (JPA) and Java Database Connectivity (JDBC)

- JDBC (Spec -> Treiber + API): Direkte DB API als volle SQL Kontrolle: Connections, Statements, ResultSets
- JPA (Spec -> Impl. z.B. Hibernate): Klassenbasierter DB Zugriff durch Definition von Entities per Annotations -> JDBC Code-Generierung
