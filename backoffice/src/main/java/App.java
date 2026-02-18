import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Optional;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Singleton;

@Singleton
public class App {
  @ConfigProperty(name = "quarkus.http.ssl.certificate.key-store-file")
  private Optional<String> keyStoreFile;

  void onStart(@Observes StartupEvent ev) {
    System.out.println("Application started!");
    System.out.println("JVM Keystore: " + System.getProperty("javax.net.ssl.keyStore"));
    System.out.println("JVM Keystore password: " + System.getProperty("javax.net.ssl.keyStorePassword"));
    System.out.println("Quarkus Keystore: " + keyStoreFile);
  }
}
