# Notes

## Lernplan

- Morgen: 4-6
- Montag: 7-8
- Dienstag: 9-11
- Mittwoch: 12+
- Donnerstag: Rest

## Quarkus

### Packages

- laut package info von "quarkus-rest-jackson" ist "rest-jackson" nicht kompatibel mit "resteasy" im tutorial nutzt er aber "resteasy"
  ich vermute man müsste stattdessen "resteasy-jackson" installieren
- A: ja
- viele resteasy packages haben im titel "Classic's" stehen, was ist classic und was sind die moderneren wege?
- A: quarkus-rest (reactive) = vert.x (non blocking async threading via netty -> nio)
- A: quarkus-resteasy (classic) = servlet basiert (blocking)
- worfür sind sind die "-client" packages?
- A: für quarkus basierte java/kotlin clients natürlich. für mich erstmal nicht wichtig vor allem nicht wenn js web frontend gebaut wird
- worfür sind sind die "-deployment" packages?
- A: interne module, transitiv, brauchen mich nicht interessieren!
- worfür ist "quarkus-resteasy-reactive-vertx"
- A: internes modul, transitiv, legacy naming (resteasy) wird aber von quarkus-rest verwendet.
- warum gibt es auch "org.jboss.resteasy" packages, ist das legacy?
- A: ja legacy und wenn dann intern verwendet, transitiv, spielt also keine rolle!
- info: quarkus rest bzw resteasy implementiert jax-rs, was teil der jakarta ee platfrom specs ist.
