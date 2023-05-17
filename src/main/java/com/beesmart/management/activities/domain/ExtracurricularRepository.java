package com.beesmart.management.activities.domain;

import com.beesmart.management.activities.dto.TypeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

interface ExtracurricularRepository extends JpaRepository<ExtracurricularActivityConfiguration, UUID> {

  @Query("SELECT a FROM ExtracurricularActivityConfiguration")
  List<ExtracurricularActivityConfiguration> search(String name, BigDecimal priceFrom, BigDecimal priceTo, TypeDto type);

}
