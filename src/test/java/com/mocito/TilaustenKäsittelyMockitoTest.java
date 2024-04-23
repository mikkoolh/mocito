package com.mocito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class TilaustenKäsittelyMockitoTest {
    @Mock
    IHinnoittelija hinnoittelijaMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testaaKäsittelijäAlle100() {
        // Arrange
        float alkusaldo = 100.0f;
        float ovh = 30.0f;
        float ale = 20.0f;
        float loppusaldo = alkusaldo - (ovh * (1 - ale / 100));
        Asiakas asiakas = new Asiakas(alkusaldo);
        Tuote tuote = new Tuote("TDD in Action", ovh);
        
        // Record
        when(hinnoittelijaMock.getAlennusProsentti(asiakas, tuote))
                .thenReturn(ale, ale);

        // Act
        TilaustenKäsittely käsittelijä = new TilaustenKäsittely();
        käsittelijä.setHinnoittelija(hinnoittelijaMock);
        käsittelijä.käsittele(new Tilaus(asiakas, tuote));
        
        // Assert
        assertEquals(loppusaldo, asiakas.getSaldo(), 0.001);
        verify(hinnoittelijaMock, times(2)).getAlennusProsentti(asiakas, tuote);
    }

    @Test
    public void testaaKäsittelijäYli100() {
        // Arrange
        float alkusaldo = 150.0f;
        float ovh = 140.0f;
        float ale = 20.0f;
        float loppusaldo = alkusaldo - (ovh * (1 - (ale+5) / 100));
        Asiakas asiakas = new Asiakas(alkusaldo);

        Tuote tuote = new Tuote("TDD in Action", ovh);

        // Record
        when(hinnoittelijaMock.getAlennusProsentti(asiakas, tuote))
                .thenReturn(ale, (ale+5));
        
        // Act
        TilaustenKäsittely käsittelijä = new TilaustenKäsittely();
        käsittelijä.setHinnoittelija(hinnoittelijaMock);
        käsittelijä.käsittele(new Tilaus(asiakas, tuote));
        
        // Assert
        assertEquals(loppusaldo, asiakas.getSaldo(), 0.001);
        verify(hinnoittelijaMock, times(2)).getAlennusProsentti(asiakas, tuote);
    }
}


 