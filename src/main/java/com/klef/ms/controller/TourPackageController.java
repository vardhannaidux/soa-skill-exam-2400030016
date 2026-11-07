package com.klef.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.klef.ms.dto.TourPackageRequest;
import com.klef.ms.dto.TourPackageResponse;
import com.klef.ms.service.TourPackageService;

import jakarta.validation.Valid;
@RestController
@Validated
@RequestMapping("/tour")

public class TourPackageController
{
   @Autowired
   private TourPackageService service;
   
   @GetMapping("/")
   public String home() {
	   return "Tour Package Service Project";
   }
   
   @PostMapping("/add")
   public ResponseEntity<TourPackageResponse> saveTourPackage(
           @Valid @RequestBody TourPackageRequest request)
{
	   return new ResponseEntity<>(service.saveTourPackage(request), HttpStatus.CREATED);

   }
   
   @GetMapping("/displayall")
   public ResponseEntity<List<TourPackageResponse>> getAllTourPackages(){
	   return ResponseEntity.ok(service.getAllTourPackages());
   }
   
   @GetMapping("/display/{id}")
   public ResponseEntity<TourPackageResponse> getTourPackageById(@PathVariable Long id)
   {
	   return ResponseEntity.ok(service.getTourPackageById(id));
   }
   @PutMapping("/update/{id}")
   public ResponseEntity<TourPackageResponse> updateTourPackage(
           @PathVariable Long id,
           @Valid @RequestBody TourPackageRequest request)
{
	   return ResponseEntity.ok(service.updateTourPackage(id, request));
   }
   
   @DeleteMapping("/delete/{id}")
   public ResponseEntity<String> getTourPackageByName(@PathVariable Long id){
	   service.deleteTourPackage(id);
	   return ResponseEntity.ok("tour Package deleted Successfully");
   }
   
   @GetMapping("/name/{name}")
   public ResponseEntity<List<TourPackageResponse>> getTourPackageByName(@PathVariable String name){
	   return ResponseEntity.ok(service.getTourPackagesByName(name));
   }
   
   @GetMapping("/type/{type}")
   public ResponseEntity<List<TourPackageResponse>> getTourPackagesByType(
           @PathVariable String type)
   {
       return ResponseEntity.ok(service.getTourPackagesByType(type));
   }

}