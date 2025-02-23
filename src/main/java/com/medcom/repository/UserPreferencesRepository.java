package com.medcom.repository;

import com.medcom.entity.UserPreferences;
import com.medcom.entity.UserPreferencesId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreferencesRepository extends JpaRepository<UserPreferences, UserPreferencesId> {
}