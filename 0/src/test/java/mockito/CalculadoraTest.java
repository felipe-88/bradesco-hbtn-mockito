package mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class CalculadoraTest {

    @InjectMocks
    private Calculadora calculadora;
    @Mock
    private ServicoMatematico servicoMatematico;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarCinco() {
        int expected = 5;
        Mockito.when(servicoMatematico.somar(Mockito.anyInt(), Mockito.anyInt())).thenReturn(expected);

        int resultado = calculadora.somar(2, 3);

        Assertions.assertEquals(expected, resultado);
    }
}
