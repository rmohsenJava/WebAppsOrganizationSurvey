package com.blackstone.webappsorganizationsurvey.repository;

import com.blackstone.webappsorganizationsurvey.entity.FormFile;
import com.blackstone.webappsorganizationsurvey.entity.enums.FileType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FormFileRepository extends JpaRepository<FormFile, Integer> {

    List<FormFile> findAllByForm_IdAndFileType(Long formId, FileType fileType);

    Long countAllByForm_IdAndFileType(Long formId, FileType fileType);
}
