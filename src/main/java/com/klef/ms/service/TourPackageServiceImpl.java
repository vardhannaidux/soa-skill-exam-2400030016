package com.klef.ms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.ms.dto.TourPackageRequest;
import com.klef.ms.dto.TourPackageResponse;
import com.klef.ms.entity.TourPackage;
import com.klef.ms.exception.ResourceNotFoundException;
import com.klef.ms.repository.TourPackageRepository;

@Service
public class TourPackageServiceImpl implements TourPackageService {

    @Autowired
    private TourPackageRepository repository;

    // 1. Insert a new Tour Package
    @Override
    public TourPackageResponse saveTourPackage(TourPackageRequest request) {

        TourPackage tourPackage = TourPackage.builder()
                .name(request.getName())
                .type(request.getType())
                .status(request.getStatus())
                .location(request.getLocation())
                .build();

        TourPackage savedPackage = repository.save(tourPackage);

        return mapToResponse(savedPackage);
    }

    // 2. Display all Tour Packages
    @Override
    public List<TourPackageResponse> getAllTourPackages() {

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // 3. Display Tour Package by ID
    @Override
    public TourPackageResponse getTourPackageById(Long id) {

        TourPackage tourPackage = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Tour Package is Not Found with id: " + id));

        return mapToResponse(tourPackage);
    }

    // 4. Update an existing Tour Package
    @Override
    public TourPackageResponse updateTourPackage(
            Long id, TourPackageRequest request) {

        TourPackage tourPackage = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Tour Package not found with id: " + id));

        tourPackage.setName(request.getName());
        tourPackage.setType(request.getType());
        tourPackage.setStatus(request.getStatus());
        tourPackage.setLocation(request.getLocation());

        TourPackage updatedPackage = repository.save(tourPackage);

        return mapToResponse(updatedPackage);
    }

    // 5. Delete a Tour Package
    @Override
    public void deleteTourPackage(Long id) {

        TourPackage tourPackage = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Tour Package not Found with the id: " + id));

        repository.delete(tourPackage);
    }

    // 6. Find Tour Packages by Name
    @Override
    public List<TourPackageResponse> getTourPackagesByName(String name) {

        return repository.findByName(name)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // 7. Find Tour Packages by Type
    @Override
    public List<TourPackageResponse> getTourPackagesByType(String type) {

        return repository.findByType(type)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Convert Entity into Response DTO
    private TourPackageResponse mapToResponse(TourPackage tourPackage) {

        return TourPackageResponse.builder()
                .tourPackageId(tourPackage.getToutPackageId())
                .name(tourPackage.getName())
                .type(tourPackage.getType())
                .status(tourPackage.getStatus())
                .location(tourPackage.getLocation())
                .createdAt(tourPackage.getCreatedAt())
                .updatedAt(tourPackage.getUpdatedAt())
                .build();
    }
}