package com.klef.ms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.ms.entity.TourPackage;

@Repository
public interface TourPackageRepository extends JpaRepository<TourPackage, Long>
{
    List<TourPackage> findByName(String name);

    List<TourPackage> findByType(String type);
}