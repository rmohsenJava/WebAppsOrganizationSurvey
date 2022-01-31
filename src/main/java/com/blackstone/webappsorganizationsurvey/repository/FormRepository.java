package com.blackstone.webappsorganizationsurvey.repository;

import com.blackstone.webappsorganizationsurvey.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FormRepository extends JpaRepository<Form, Integer> {
}
