package com.example.demo.service;

import com.example.demo.models.Conta;
import com.example.demo.models.Saldo;
import com.example.demo.repository.ContaRepository;
import com.example.demo.repository.SaldoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaldoService {

    private final SaldoRepository saldoRepository;

    public Saldo buscar(Conta conta) {
        return this.saldoRepository.findByConta(conta).get();
    }
}
