package com.trilogyed.finaidservice.repository;

import com.trilogyed.finaidservice.model.FinancialAid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FinAidRepository extends JpaRepository<FinancialAid, String> {

    Optional<FinancialAid> findFinancialAidByStudentId(String studentId);
}
