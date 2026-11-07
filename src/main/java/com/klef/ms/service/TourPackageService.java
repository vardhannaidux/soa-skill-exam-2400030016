package com.klef.ms.service;

import java.util.List;

import com.klef.ms.dto.TourPackageRequest;
import com.klef.ms.dto.TourPackageResponse;

public interface TourPackageService
{
    TourPackageResponse saveTourPackage(TourPackageRequest request);

    List<TourPackageResponse> getAllTourPackages();

    TourPackageResponse getTourPackageById(Long id);

    TourPackageResponse updateTourPackage(Long id, TourPackageRequest request);

    void deleteTourPackage(Long id);

    List<TourPackageResponse> getTourPackagesByName(String name);

    List<TourPackageResponse> getTourPackagesByType(String type);
}