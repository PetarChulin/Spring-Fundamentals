package com.example.exam.repositories;

import com.example.exam.model.entity.Condition;
import com.example.exam.model.entity.ConditionEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, Long> {

    Optional<Condition> findByName(ConditionEnum name);

}
