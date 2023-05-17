package com.beesmart.management.activities.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface ActivityTermRepository extends JpaRepository<ActivityTerm, UUID> {

}
