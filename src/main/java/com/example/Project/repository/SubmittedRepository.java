package com.example.Project.repository;

import com.example.Project.Model.SciencePlan.SubmittedSciencePlan;
import org.springframework.data.repository.CrudRepository;

public interface SubmittedRepository  extends CrudRepository<SubmittedSciencePlan, Integer> {
}
