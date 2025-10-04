package mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class BancoServiceTest {
    @Test
    public void testConsultarSaldo() {
        // Criando mock do repositório
        ContaRepository repository = mock(ContaRepository.class);

        // Criando conta simulada
        String numeroConta = "1234";
        double saldo = 20D;
        Conta conta = new Conta(numeroConta, saldo);
        Conta mockConta = mock(Conta.class);
        // Definindo comportamento do mock
        when(repository.buscarConta(anyString())).thenReturn(conta);
        when(mockConta.getSaldo()).thenReturn(saldo);

        // Criando serviço com o mock
        BancoService service = new BancoService(repository);

        // Testando consulta de saldo
        double resultado = service.consultarSaldo(numeroConta);

        // Verificando se o saldo está correto
        Assertions.assertEquals(saldo, resultado);

        // Verificando se o método buscarConta foi chamado
        verify(repository).buscarConta(numeroConta);
    }


    @Test
    public void testDepositar() {
        // Criando mock do repositório
        ContaRepository repository = mock(ContaRepository.class);

        // Criando conta simulada
        String numeroConta = "1234";
        double saldo = 20D;
        Conta conta = new Conta(numeroConta, saldo);
        Conta mockConta = mock(Conta.class);
        Conta spy = spy(conta);
        // Definindo comportamento do mock
        when(repository.buscarConta(anyString())).thenReturn(spy);
        when(spy.getSaldo()).thenReturn(saldo);
        spy.setSaldo(40.0);
        doNothing().when(repository).salvar(spy);

        // Criando serviço com o mock
        BancoService service = new BancoService(repository);

        // Executando depósito
        service.depositar(numeroConta, saldo);

        // Verificando se o saldo foi atualizado
        Assertions.assertEquals(20D, spy.getSaldo());

        // Verificando se os métodos foram chamados corretamente
        verify(repository).salvar(spy);
    }
}
