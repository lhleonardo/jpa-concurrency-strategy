package com.example.demo.service;

import com.example.demo.models.Conta;
import com.example.demo.models.Saldo;
import com.example.demo.repository.SaldoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OperacoesSaldoService {

    private final SaldoRepository saldoRepository;
    private final ContaService contaService;

    @Transactional
    public void creditar(String numeroConta, BigDecimal valor) {
        Conta conta = this.contaService.buscar(numeroConta);
        Saldo saldo = this.saldoRepository.findByConta(conta).get();

        BigDecimal valorAtualizado = saldo.getValor().add(valor);

        saldo.setDataAtualizacao(LocalDateTime.now());
        saldo.setValor(valorAtualizado);

        this.saldoRepository.saveAndFlush(saldo);
    }

    @Transactional
    public void debitar(String numeroConta, BigDecimal valor) {
        Conta conta = this.contaService.buscar(numeroConta);
        Saldo saldo = this.saldoRepository.findByConta(conta).get();

        BigDecimal valorAtualizado = saldo.getValor().subtract(valor);

        saldo.setDataAtualizacao(LocalDateTime.now());
        saldo.setValor(valorAtualizado);

        this.saldoRepository.saveAndFlush(saldo);
    }

    public BigDecimal saldo(String conta) {
        Conta contaEncontrada = this.contaService.buscar(conta);
        return this.saldoRepository.findByContaSemLock(contaEncontrada).get().getValor();
    }
}
