package puho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Application {

    public Application() { //NOSONAR
    }


    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
