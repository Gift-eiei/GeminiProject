package com.example.Project.repository;

import com.example.Project.Model.VirtualTelescope.VtModel;
import org.springframework.data.repository.CrudRepository;

public interface VirtualTelescopeRepository extends CrudRepository<VtModel, Integer> {
}
