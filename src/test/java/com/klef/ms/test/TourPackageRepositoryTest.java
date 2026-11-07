
package com.klef.ms.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.klef.ms.entity.TourPackage;
import com.klef.ms.repository.TourPackageRepository;

@DataJpaTest
@AutoConfigureTestDatabase(
    replace = AutoConfigureTestDatabase.Replace.NONE
)
public class TourPackageRepositoryTest {

    @Autowired
    private TourPackageRepository repository;

    // Helper method to create test data
    private TourPackage createPackage(
            String name, String type, String status, String location) {

        return TourPackage.builder()
                .name(name)
                .type(type)
                .status(status)
                .location(location)
                .build();
    }

    // TEST 1: Find All Records
    @Test
    public void testFindAll() {

        repository.save(createPackage(
                "Goa Trip", "Adventure", "ACTIVE", "Goa"));

        repository.save(createPackage(
                "Manali Trip", "Family", "ACTIVE", "Manali"));

        List<TourPackage> packages = repository.findAll();

        assertNotNull(packages);
        assertTrue(packages.size() >= 2);

        System.out.println("TEST 1 PASSED: Find All Records");
        System.out.println("Total Packages: " + packages.size());
    }

    // TEST 2: Find By ID
    @Test
    public void testFindById() {

        TourPackage savedPackage = repository.save(
                createPackage(
                        "Kerala Trip", "Family", "ACTIVE", "Kerala"));

        Long id = savedPackage.getToutPackageId();

        Optional<TourPackage> result = repository.findById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getToutPackageId());

        Optional<TourPackage> invalidResult =
                repository.findById(-1L);

        assertFalse(invalidResult.isPresent());

        System.out.println("TEST 2 PASSED: Find By ID");
        System.out.println("Package ID: " + id);
        System.out.println("Invalid ID correctly returned empty");
    }

    // TEST 3: Find By Derived Query
    @Test
    public void testFindByName() {

        repository.save(createPackage(
                "Goa Trip", "Adventure", "ACTIVE", "Goa"));

        List<TourPackage> result =
                repository.findByName("Goa Trip");

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("Goa Trip", result.get(0).getName());

        System.out.println("TEST 3 PASSED: Find By Name");
        System.out.println("Found Package: " + result.get(0).getName());
    }

    // TEST 4: Count Records
    @Test
    public void testCount() {

        repository.save(createPackage(
                "Ooty Trip", "Family", "ACTIVE", "Ooty"));

        repository.save(createPackage(
                "Ladakh Trip", "Adventure", "ACTIVE", "Ladakh"));

        long count = repository.count();

        assertEquals(2L, count);

        System.out.println("TEST 4 PASSED: Count Records");
        System.out.println("Total Records: " + count);
    }

    // TEST 5: Entity Details / Property-Based Result
    @Test
    public void testEntityDetails() {

        repository.save(createPackage(
                "Hyderabad Trip", "City", "ACTIVE", "Hyderabad"));

        List<TourPackage> result =
                repository.findByType("City");

        assertFalse(result.isEmpty());

        TourPackage tourPackage = result.get(0);

        assertNotNull(tourPackage.getToutPackageId());
        assertNotNull(tourPackage.getName());
        assertNotNull(tourPackage.getType());
        assertNotNull(tourPackage.getStatus());
        assertNotNull(tourPackage.getLocation());
        assertNotNull(tourPackage.getCreatedAt());
        assertNotNull(tourPackage.getUpdatedAt());

        assertEquals("City", tourPackage.getType());

        System.out.println("TEST 5 PASSED: Entity Details");
        System.out.println("Package Name: " + tourPackage.getName());
        System.out.println("Package Type: " + tourPackage.getType());
        System.out.println("Package Status: " + tourPackage.getStatus());
        System.out.println("Package Location: " + tourPackage.getLocation());
    }
}
