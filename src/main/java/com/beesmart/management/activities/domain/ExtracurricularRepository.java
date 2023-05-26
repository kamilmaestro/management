package com.beesmart.management.activities.domain;

import com.beesmart.management.activities.dto.TypeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

interface ExtracurricularRepository extends JpaRepository<ExtracurricularActivityConfiguration, UUID> {

  @Query("SELECT a FROM ExtracurricularActivityConfiguration a " +
      "WHERE type = :type AND ( LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%')) OR ( a.price >= :priceFrom AND a.price <= :priceTo ) )")
  List<ExtracurricularActivityConfiguration> search(@Param("name") String name,
                                                    @Param("priceFrom") BigDecimal priceFrom,
                                                    @Param("priceTo") BigDecimal priceTo,
                                                    @Param("type") ExtracurricularActivityConfiguration.ActivityType type);

  @Query("SELECT a FROM ExtracurricularActivityConfiguration a " +
      "WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%')) OR ( a.price >= :priceFrom AND a.price <= :priceTo )")
  List<ExtracurricularActivityConfiguration> search(@Param("name") String name,
                                                    @Param("priceFrom") BigDecimal priceFrom,
                                                    @Param("priceTo") BigDecimal priceTo);

  @Query("SELECT a FROM ExtracurricularActivityConfiguration a WHERE a.teacherId = :teacherId")
  List<ExtracurricularActivityConfiguration> getTeacherActivities(@Param("teacherId") UUID teacherId);

  @Query("SELECT a FROM ExtracurricularActivityConfiguration a WHERE a.teacherId = :teacherId")
  List<ExtracurricularActivityConfiguration> getStudentActivities(@Param("teacherId") UUID teacherId);

}
