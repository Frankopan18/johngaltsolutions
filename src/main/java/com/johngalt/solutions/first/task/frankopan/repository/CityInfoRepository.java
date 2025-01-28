package com.johngalt.solutions.first.task.frankopan.repository;

import com.johngalt.solutions.first.task.frankopan.entity.CityInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityInfoRepository extends JpaRepository<CityInfo, Long> {
}