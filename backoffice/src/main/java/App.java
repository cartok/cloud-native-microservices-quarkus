import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Optional;

import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Singleton;

@IfBuildProfile("dev")
@Singleton
public class App {
  @ConfigProperty(name = "quarkus.http.ssl.certificate.key-store-file")
  private Optional<String> keyStoreFile;

  @ConfigProperty(name = "quarkus.http.ssl.certificate.key-store-password")
  private Optional<String> keyStorePassword;

  @ConfigProperty(name = "quarkus.http.ssl.certificate.trust-store-file")
  private Optional<String> trustStoreFile;

  @ConfigProperty(name = "quarkus.http.ssl.certificate.trust-store-password")
  private Optional<String> trustStorePassword;

  void onStart(@Observes StartupEvent ev) {
    System.out.println("Application started!");
    System.out.println("JVM Keystore: " + System.getProperty("javax.net.ssl.keyStore"));
    System.out.println("JVM Keystore password: " + System.getProperty("javax.net.ssl.keyStorePassword"));
    System.out.println("Quarkus Keystore: " + keyStoreFile);
    System.out.println("Quarkus Keystore password: " + keyStorePassword);
    System.out.println("Quarkus Truststore: " + trustStoreFile);
    System.out.println("Quarkus Truststore password: " + trustStorePassword);
  }
}
