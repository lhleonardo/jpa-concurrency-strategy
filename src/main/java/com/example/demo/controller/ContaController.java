package com.example.demo.controller;

import com.example.demo.service.OperacoesSaldoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Log4j2
@RestController
@RequiredArgsConstructor
public class ContaController {

    private final OperacoesSaldoService operacoesSaldoService;
    @GetMapping("/hello")
    public void hello() {
        log.info("Olá");
    }

    @PostMapping("/creditar")
    public void creditar(@RequestParam String numeroConta,  @RequestParam BigDecimal valor) {
        this.operacoesSaldoService.creditar(numeroConta, valor);
    }

    @PostMapping("/debitar")
    public void debitar(@RequestParam String numeroConta,  @RequestParam BigDecimal valor) {
        this.operacoesSaldoService.debitar(numeroConta, valor);
    }

    @GetMapping("/saldo")
    public BigDecimal saldo(@RequestParam String numeroConta) {
        return this.operacoesSaldoService.saldo(numeroConta);
    }
}
