package com.findserv.root.repos;


import com.findserv.root.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    List<Apartment> findByCommunityId(Long communityId);
    List<Apartment> findByAvailableTrue();
}

