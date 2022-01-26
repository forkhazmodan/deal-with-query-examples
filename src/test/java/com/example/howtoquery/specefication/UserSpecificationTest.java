package com.example.howtoquery.specefication;

import com.example.howtoquery.model.User;
import com.example.howtoquery.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MySQLContainer;

import static org.junit.jupiter.api.Assertions.*;

@DisabledIfEnvironmentVariable(named = "RUN_INTEGRATION_TEST", matches = "false")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserSpecificationTest {

    static final MySQLContainer DATABASE = new MySQLContainer("mysql:8.0.27")
            .withDatabaseName("integration-tests-db")
            .withUsername("root")
            .withPassword("root");

    @Autowired
    private UserRepository userRepository;


//    @Test
//    void ageLessThan() {
//    }

    @Test
    void nameLike() {

        var user1 = new User();
        user1.setFirstName("TestUser FirstName");
        user1.setLastName("TestUser LastName");

        userRepository.save(user1);

        Specification<User> specification = Specification.where(UserSpecification.nameLike("Test"));
        var result = userRepository.findAll(specification, PageRequest.of(0, 10));

        assertTrue(true);
    }

    static {
        DATABASE.start();
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext context) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + DATABASE.getJdbcUrl(),
                    "spring.datasource.username=" + DATABASE.getUsername(),
                    "spring.datasource.password=" + DATABASE.getPassword()
            ).applyTo(context.getEnvironment());
        }
    }
}