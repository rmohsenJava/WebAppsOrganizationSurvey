package com.blackstone.webappsorganizationsurvey.util;

import com.blackstone.webappsorganizationsurvey.entity.Form;
import com.blackstone.webappsorganizationsurvey.entity.enums.FormStatus;
import com.blackstone.webappsorganizationsurvey.exception.FormAlreadyCanceledException;
import com.blackstone.webappsorganizationsurvey.exception.FormAlreadyCompletedException;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class FormActionValidator {

    public static void checkActionValidity(Form form, String uuid) throws FormAlreadyCompletedException,
            FormAlreadyCanceledException {

        if (FormStatus.COMPLETED.name().equals(form.getFormStatus().name())) {

            log.info("Form with uuid {} already completed !", form.getUuid());

            throw new FormAlreadyCompletedException("Form with uuid [" + uuid + "] already completed !");
        }

        if (FormStatus.CANCELED.name().equals(form.getFormStatus().name())) {

            log.info("Form with uuid {} already canceled !", form.getUuid());

            throw new FormAlreadyCanceledException("Form with uuid [" + uuid + "] already canceled !");
        }
    }

}
