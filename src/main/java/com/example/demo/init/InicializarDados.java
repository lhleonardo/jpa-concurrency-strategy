package com.example.demo.init;

import com.example.demo.models.Conta;
import com.example.demo.models.Saldo;
import com.example.demo.repository.ContaRepository;
import com.example.demo.repository.SaldoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

//@Component
@RequiredArgsConstructor
public class InicializarDados implements CommandLineRunner {

    private final ContaRepository contaRepository;
    private final SaldoRepository saldoRepository;

    @Override
    public void run(String... args) throws Exception {
        this.saldoRepository.deleteAll();
        this.contaRepository.deleteAll();

        List<Conta> contas = IntStream
                .rangeClosed(1, 100)
                .mapToObj(numeroConta -> {
                    String numeroContaFormatado = StringUtils.leftPad("0000" + numeroConta, 5);

                    Conta conta = new Conta();
                    conta.setNumero(numeroContaFormatado);

                    return conta;
                })
                .toList();

        List<Saldo> saldos = contas.stream()
                .map(conta -> {
                    Saldo saldo = new Saldo();
                    saldo.setValor(new BigDecimal("1000.00"));
                    saldo.setConta(conta);

                    return saldo;
                }).toList();

        this.contaRepository.saveAll(contas);
        this.saldoRepository.saveAll(saldos);
    }
}
