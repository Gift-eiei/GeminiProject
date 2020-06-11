package com.example.Project.repository;

import com.example.Project.Model.SciencePlan.SciencePlan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SciencePlanRepository extends CrudRepository<SciencePlan, Integer> {

}
