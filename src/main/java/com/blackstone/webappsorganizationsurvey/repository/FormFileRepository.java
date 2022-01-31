package com.blackstone.webappsorganizationsurvey.repository;

import com.blackstone.webappsorganizationsurvey.entity.FormFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FormFileRepository extends JpaRepository<FormFile, Integer> {
}
