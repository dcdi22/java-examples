package com.trilogyed.tuitionservice.repository;

import com.trilogyed.tuitionservice.model.BaseTuition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TuitionRepository extends JpaRepository<BaseTuition, String> {

    BaseTuition getBaseTuitionByMajor(String major);
}
