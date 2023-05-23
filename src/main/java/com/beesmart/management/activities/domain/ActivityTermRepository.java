package com.beesmart.management.activities.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

interface ActivityTermRepository extends JpaRepository<ActivityTerm, UUID> {

  List<ActivityTerm> findByStudentId(UUID studentId);

  List<ActivityTerm> findByActivityId(UUID activityId);

}
