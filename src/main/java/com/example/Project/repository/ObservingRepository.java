package com.example.Project.repository;

import com.example.Project.Model.ObservingProgram.ObservingProgram;
import org.springframework.data.repository.CrudRepository;

public interface ObservingRepository extends CrudRepository<ObservingProgram, Integer> {
}
