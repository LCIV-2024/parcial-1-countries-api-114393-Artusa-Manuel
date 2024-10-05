package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.clients.CountriesByLanguage;
import ar.edu.utn.frc.tup.lciii.clients.CountriesByRegion;
import ar.edu.utn.frc.tup.lciii.clients.CountryDTO;
import ar.edu.utn.frc.tup.lciii.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CountryControllerTest {

    @SpyBean
    CountryController countryController;

    @MockBean
    CountryService countryService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getCountiresByName() {
        // Dado
        String countryName = "ARGENTINA";
        List<CountryDTO> mockCountries = new ArrayList<>();
        mockCountries.add(new CountryDTO("ARG", "ARGENTINA"));
        when(countryService.getCountriesByName(countryName)).thenReturn(mockCountries);

        // Cuando
        ResponseEntity<List<CountryDTO>> response = countryController.getCountiresByName(countryName);

        // Entonces
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("ARG", response.getBody().get(0).getCode());
        assertEquals("ARGENTINA", response.getBody().get(0).getName());


        verify(countryService).getCountriesByName(countryName);
    }

    @Test
    void getCountriesByRegion() {
        // Dado
        String region = "América";
        List<CountriesByRegion> mockCountries = new ArrayList<>();
        mockCountries.add(new CountriesByRegion("ARG", "ARGENTINA","America"));
        when(countryService.getCountriesByRegion(region)).thenReturn(mockCountries);

        // Cuando
        ResponseEntity<List<CountriesByRegion>> response = countryController.getCountriesByRegion(region);

        // Entonces
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("ARG", response.getBody().get(0).getCode());
        assertEquals("ARGENTINA", response.getBody().get(0).getName());


        verify(countryService).getCountriesByRegion(region);
    }

    @Test
    void getCountriesByLanguage() {

        String language = "Español";
        List<CountriesByLanguage> mockCountries = new ArrayList<>();
        Map<String,String> mockLanguages = new HashMap<>();
        mockLanguages.put("language","Español");
//        mockCountries.add(new CountriesByLanguage("ARG", "ARGENTINA",mockLanguages.get("language")));
        when(countryService.getCountriesByLanguage(language)).thenReturn(mockCountries);

        // Cuando
        ResponseEntity<List<CountriesByLanguage>> response = countryController.getCountriesByLanguage(language);

        // Entonces
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("ARG", response.getBody().get(0).getCode());
        assertEquals("ARGENTINA", response.getBody().get(0).getName());


        verify(countryService).getCountriesByLanguage(language);
    }

    @Test
    void getMostBorders() {
        // Dado
        CountryDTO countryWithMostBorders = new CountryDTO("CHN","China");
        when(countryService.getCountryWithMostBorders()).thenReturn(countryWithMostBorders);

         //Cuando
        ResponseEntity<CountryDTO> response = countryController.getMostBorders();

        // Entonces
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        //assertEquals(countryWithMostBorders, response.getBody());


        verify(countryService).getCountryWithMostBorders();
    }

    @Test
    void postCountries() {
        // Dado
        int amount = 10;
        List<CountryDTO> mockCountries = new ArrayList<>();
        mockCountries.add(new CountryDTO("ARG", "ARGENTINA"));
        when(countryService.saveRandomCountries(amount)).thenReturn(mockCountries);

        // Cuando
        ResponseEntity<List<CountryDTO>> response = countryController.postCountries(amount);

        // Entonces
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("ARG", response.getBody().get(0).getCode());
        assertEquals("ARGENTINA", response.getBody().get(0).getName());


        verify(countryService).saveRandomCountries(amount);
    }
}
