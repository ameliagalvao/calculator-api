package org.example.calculatorapi.repository;

import org.example.calculatorapi.domain.Calculation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationRepository extends JpaRepository<Calculation,Long> {
}
