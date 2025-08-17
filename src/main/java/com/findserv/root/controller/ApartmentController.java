package com.findserv.root.controller;

import com.findserv.root.DTO.ApartmentDto;
import com.findserv.root.service.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apartments")
@RequiredArgsConstructor
public class ApartmentController {

    private final ApartmentService apartmentService;

    @GetMapping
    public ResponseEntity<List<ApartmentDto>> getAll() {
        return ResponseEntity.ok(apartmentService.getAllApartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApartmentDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(apartmentService.getApartment(id));
    }

    @GetMapping("/community/{communityId}")
    public ResponseEntity<List<ApartmentDto>> getByCommunity(@PathVariable Long communityId) {
        return ResponseEntity.ok(apartmentService.getApartmentsByCommunity(communityId));
    }

    @PostMapping
    public ResponseEntity<ApartmentDto> create(@RequestBody ApartmentDto dto) {
        return ResponseEntity.ok(apartmentService.createApartment(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApartmentDto> update(@PathVariable Long id, @RequestBody ApartmentDto dto) {
        return ResponseEntity.ok(apartmentService.updateApartment(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        apartmentService.deleteApartment(id);
        return ResponseEntity.noContent().build();
    }

    // Batch insert for apartments with auto community creation
    @PostMapping("/batch/create-with-community")
    public ResponseEntity<List<ApartmentDto>> createBatchWithCommunity(@RequestBody List<ApartmentDto> dtos) {
        return ResponseEntity.ok(apartmentService.createApartmentsBatchWithCommunityCreation(dtos));
    }

    // Batch insert for a single existing community
    @PostMapping("/batch/{communityId}")
    public ResponseEntity<List<ApartmentDto>> createBatchSingleCommunity(
            @PathVariable Long communityId,
            @RequestBody List<ApartmentDto> dtos) {
        return ResponseEntity.ok(apartmentService.createApartmentsBatch(communityId, dtos));
    }
}
