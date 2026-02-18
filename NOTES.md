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

1. CURL failed wenn man beim Zertifikat CN nicht auf eine Addresse (localhost oder 127.0.0.1), oder stattdessen SAN nicht gesetzt hat.

2. Der IDEA HTTP Client:

- Bug: Failed den redirect, man bekommt nicht vorgeschlagen das Zertifikat zu erlauben, sieht nur: "javax.net.ssl.SSLHandshakeException: (certificate_unknown) PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target"
- Bug: Die Base URL wird reused, wodurch ich nicht abwechselnd http & https eines Endpoints testen kann. Update: Löste sich irgendwann, muss ein Cache-Problem gewesen sein.

3. Die `@TestSecurity` Annotation

- Wenn ich darüber die security disable werden `user` und `role` auch nicht validiert, es macht also keinen Sinn die zu setzen.

### Documentation

Goal: Mutual TLS scenario:

- Entity 1 = server
- Entity 2 = client A (bob)
- Entity 3 = client B (alice)

<pre>
Truststore
├─ Certificate Authority
│ ├─ Certificate A
│ ├─ Certificate B
│ └─ Certificate C
└─ Certificate X
</pre>

```zsh
# Create keystore incl. certificate for backoffice identity

keytool -genkeypair \
  -keyalg Ed25519 \
  -storetype PKCS12 \
  -validity 3650 \
  -dname "CN=Ezreal, OU=Research, O=Piltover Weapons, L=Piltover, ST=Runeterra, C=PT" \
  -ext SAN=DNS:localhost,DNS:127.0.0.1 \
  -keypass changeit \
  -storepass changeit \
  -keystore service.p12

# Export certificate from backoffice keystore

keytool -exportcert \
  -keystore service.p12 \
  -storetype PKCS12 \
  -storepass changeit \
  -rfc \
  -file service.crt

# Create two additional identities (bob and alice), extract their certificates to import them into a custom truststore, which will be configured in the backoffice quarkus config to be used in order to limit access to those two identities. Also export the private keys of the test identities to use them with the IDEA HTTP Client. The latter has to be done using openssl as keytool does not support exporting private keys of keystores and the IDEA HTTP Client can't use JVM p12 files.

for name in bob alice; do
  keytool -genkeypair \
    -alias $name \
    -keyalg Ed25519 \
    -storetype PKCS12 \
    -validity 3650 \
    -dname "CN=$name" \
    -keypass changeit \
    -storepass changeit \
    -keystore $name.p12
  
  keytool -exportcert \
    -alias $name \
    -keystore $name.p12 \
    -storetype PKCS12 \
    -storepass changeit \
    -rfc \
    -file $name.crt
    
  keytool -importcert \
    -alias $name \
    -file $name.crt \
    -storetype PKCS12 \
    -storepass changeit \
    -noprompt \
    -keystore service-truststore.p12
  
  openssl pkcs12 \
    -in $name.p12 \
    -out $name.key \
    -nocerts \
    -nodes \
    -passin pass:changeit
done
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
