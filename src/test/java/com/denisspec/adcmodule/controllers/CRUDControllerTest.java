package com.denisspec.adcmodule.controllers;

import com.denisspec.adcmodule.entities.PetrolStationEntity;
import com.denisspec.adcmodule.service.PetrolStationService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CRUDControllerTest {
    @Mock
    PetrolStationService petrolStationService;
    @InjectMocks
    CRUDController crudController;
    @Before
    public  void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testGetDataFromServiceInController(){
        PetrolStationEntity petrolStationEntity1 = new PetrolStationEntity("г.Майкоп, Хакурате, 654",44.62423,40.05552,"01023","RU","(8772) 53-46-05","Республика Адыгея");
        PetrolStationEntity petrolStationEntity2 = new PetrolStationEntity("г.Майкоп, Батарейная, 385 А",44.57913,40.1366,"01024","RU","(8772) 56-38-20","Республика Адыгея");
        List<PetrolStationEntity> petrolStationEntityList = new ArrayList<>();
        petrolStationEntityList.add(petrolStationEntity1);
        petrolStationEntityList.add(petrolStationEntity2);
        when(petrolStationService.getAllPetrolStations()).thenReturn(petrolStationEntityList);
        assertThatCode(()->this.crudController.getAllPetrolStations()).doesNotThrowAnyException();
        try {
            assertEquals(new ResponseEntity<>(petrolStationEntityList, HttpStatus.OK),
                    this.crudController.getAllPetrolStations());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
