package com.example.LegacyBabies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository <Items, Integer> {
    List<Items> findByCategory(String category);
}
