package com.example.demo.repository;

import com.example.demo.models.Conta;
import com.example.demo.models.Saldo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface SaldoRepository extends JpaRepository<Saldo, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Saldo> findByConta(Conta conta);

    @Query("SELECT s FROM Saldo s WHERE s.conta = :conta")
    Optional<Saldo> findByContaSemLock(Conta conta);
}
