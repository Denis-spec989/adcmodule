package com.denisspec.adcmodule.repository;

import com.denisspec.adcmodule.entities.PetrolStationEntity;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = {PetrolStationRepositoryTCIntegrationTest.Initializer.class})
public class PetrolStationRepositoryTCIntegrationTest {
    @Autowired
    PetrolStationRepository petrolStationRepository;
    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.1")
            .withDatabaseName("integration-tests-db")
            .withUsername("sa")
            .withPassword("sa");

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Test
    @Transactional
    public void testInsertAises_Then_FindAllValid(){
        insertAises();
        List<PetrolStationEntity> petrolStationEntityList = petrolStationRepository.findAll();
        assertEquals(2,petrolStationEntityList.size());
    }

    private void insertAises() {
        petrolStationRepository.save(new PetrolStationEntity("г.Майкоп, Хакурате, 654",44.62423,40.05552,"01023","RU","(8772) 53-46-05","Республика Адыгея"));
        petrolStationRepository.save(new PetrolStationEntity("г.Майкоп, Батарейная, 385 А",44.57913,40.1366,"01024","RU","(8772) 56-38-20","Республика Адыгея"));
       petrolStationRepository.flush();
    }
}
