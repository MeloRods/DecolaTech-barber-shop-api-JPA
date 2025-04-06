package com.example.barber_shop_api.repository;

import com.example.barber_shop_api.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    @Query("SELECT a FROM Agendamento a WHERE YEAR(a.dataHora) = :year AND MONTH(a.dataHora) = :month")
    List<Agendamento> findByYearAndMonth(@Param("year") int year, @Param("month") int month);
}