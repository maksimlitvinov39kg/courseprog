package com.example.courseprog.Repositories;

import com.example.courseprog.Tables.Dispersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispersionRepository extends JpaRepository<Dispersion,Long> {
Dispersion findByMccType(int mccType);
}
