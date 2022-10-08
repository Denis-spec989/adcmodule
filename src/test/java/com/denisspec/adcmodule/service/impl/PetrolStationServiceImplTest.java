package com.denisspec.adcmodule.service.impl;

import com.denisspec.adcmodule.entities.PetrolStationEntity;
import com.denisspec.adcmodule.models.CSV;
import com.denisspec.adcmodule.models.MultipartModel;
import com.denisspec.adcmodule.models.PetrolStationDto;
import com.denisspec.adcmodule.models.XML;
import com.denisspec.adcmodule.repository.PetrolStationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PetrolStationServiceImplTest {
    @Mock
    private CSVConverter csvConverter;
    @Mock
    private XmlConverter xmlConverter;
    @Mock
    PetrolStationRepository petrolStationRepository;
    @InjectMocks
    private PetrolStationServiceImpl petrolStationService;
    @Before
    public  void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testLoadingCsv(){
        String bytes = "asfarfeafr123421";
        MultipartModel multipartModel = new MultipartModel(new MockMultipartFile("xlsasd.csv",bytes.getBytes()));
        CSV csv = new CSV(multipartModel.getMultipartFile());
        PetrolStationDto petrolStationdto1 = new PetrolStationDto("г.Майкоп, Хакурате, 654",44.62423,
                40.05552,"01023","RU","(8772) 53-46-05","Республика Адыгея");
        PetrolStationDto petrolStationdto2 = new PetrolStationDto("г.Майкоп, Батарейная, 385 А",44.57913,
                40.1366,"01024","RU","(8772) 56-38-20","Республика Адыгея");
        List<PetrolStationDto> petrolStationEntityList = new ArrayList<>();
        petrolStationEntityList.add(petrolStationdto1);
        petrolStationEntityList.add(petrolStationdto2);
        try {
            when(csvConverter.convert(eq(csv))).thenReturn(petrolStationEntityList);
            when(petrolStationRepository.findByNameAndRegion(any(String.class),any(String.class))).thenReturn(null);
            assertThatCode(()->this.petrolStationService.load(multipartModel)).doesNotThrowAnyException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testLoadingXml(){
        String bytes = "asfarfeafr123421";
        MultipartModel multipartModel = new MultipartModel(new MockMultipartFile("xlsasd.csv",bytes.getBytes()));
        XML xml = new XML(multipartModel.getMultipartFile());
        PetrolStationDto petrolStationdto1 = new PetrolStationDto("г.Майкоп, Хакурате, 654",44.62423,40.05552,"01023","RU","(8772) 53-46-05","Республика Адыгея");
        PetrolStationDto petrolStationdto2 = new PetrolStationDto("г.Майкоп, Батарейная, 385 А",44.57913,40.1366,"01024","RU","(8772) 56-38-20","Республика Адыгея");
        List<PetrolStationDto> petrolStationEntityList = new ArrayList<>();
        petrolStationEntityList.add(petrolStationdto1);
        petrolStationEntityList.add(petrolStationdto2);
        try {
            when(xmlConverter.convert(eq(xml))).thenReturn(petrolStationEntityList);
            when(petrolStationRepository.findByNameAndRegion(any(String.class),any(String.class))).thenReturn(null);
            assertThatCode(()->this.petrolStationService.load(multipartModel)).doesNotThrowAnyException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Test
    public  void testGetAllPetrolStations(){
        assertThatCode(()->this.petrolStationService.getAllPetrolStations()).doesNotThrowAnyException();
        verify(petrolStationRepository,times(1)).findAll();
    }



}
