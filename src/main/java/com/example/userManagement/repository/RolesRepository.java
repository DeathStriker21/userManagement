package com.example.userManagement.repository;

import com.example.userManagement.entity.RolesEntity;
import com.example.userManagement.enumaration.RolesType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity,Long> {
    Optional<RolesEntity> findByName(RolesType roles);
}
