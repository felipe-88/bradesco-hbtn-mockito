package mockito;

public class Calculadora {

    private ServicoMatematico servicoMatematico;

    public int somar(int a, int b) {
        return servicoMatematico.somar(a, b);
    }
}
