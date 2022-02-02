package com.blackstone.webappsorganizationsurvey.repository;

import com.blackstone.webappsorganizationsurvey.entity.Form;
import com.blackstone.webappsorganizationsurvey.entity.enums.FormStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FormRepository extends JpaRepository<Form, Long> {

    Optional<Form> findByUuid(String uuid);

    Page<Form> findAllByFormStatus(Pageable pageable, FormStatus formStatus);
}
