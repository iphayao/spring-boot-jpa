package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new Customer("John", "Doe"));
            repository.save(new Customer("Jan", "Doe"));

            // fetch all customers
            log.info("Customers found with findAll()");
            log.info("------------------------------");
            for(Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            repository.findById(1L).ifPresent(customer -> {
                log.info("Customer found with findById(1L)");
                log.info("--------------------------------");
                log.info(customer.toString());
                log.info("");
            });

            // fetch customers by last name
            log.info("Customer found with findByFirstName('John')");
            log.info("-----------------------------------------");
            repository.findByFirstName("John").forEach(doe -> {
                log.info(doe.toString());
            });
            log.info("");
        };
    }
}