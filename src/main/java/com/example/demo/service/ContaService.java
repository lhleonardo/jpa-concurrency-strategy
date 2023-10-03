package com.example.demo.service;

import com.example.demo.models.Conta;
import com.example.demo.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepository contaRepository;

    public Conta buscar(String numero) {
        String numeroContaFormatado = StringUtils.leftPad("0000" + numero, 5);
        return this.contaRepository.findByNumero(numeroContaFormatado).get();
    }
}
