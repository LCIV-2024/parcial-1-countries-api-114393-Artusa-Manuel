package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.clients.CountryDTO;
import ar.edu.utn.frc.tup.lciii.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CountryControllerTest {

    @SpyBean
    CountryController countryController;

    @MockBean
    CountryService countryService;

    @Test
    void getCountiresByName() {
        List<CountryDTO> countryDTOList = new List<CountryDTO>({
                new CountryDTO("Arg","ARGENTINA"),
                new CountryDTO("BRA","BRASIL");
        })

    }

    @Test
    void getCountriesByRegion() {
    }

    @Test
    void getCountriesByLanguage() {
    }

    @Test
    void getMostBorders() {
    }

    @Test
    void postCountries() {
    }
}