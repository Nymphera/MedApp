package med.medapp.api;

import med.medapp.api.model.Errors;
import med.medapp.exception.AppointmentAlreadyDoneException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;


@RestControllerAdvice
public class MedAppErrorHandler {
    public static final Logger LOGGER = LoggerFactory.getLogger(MedAppErrorHandler.class);

    @ExceptionHandler(IllegalStateException.class)
    public Errors handleIllegalState (IllegalStateException ex) {
        LOGGER.error("Error", ex);
        return Errors.builder().errors(Collections.singletonList(ex.getMessage())).build();
    }

    @ResponseStatus (HttpStatus.CONFLICT)
    @ExceptionHandler(AppointmentAlreadyDoneException.class)
    public Errors handleAppointmentAlreadyDone (AppointmentAlreadyDoneException ex) {
        LOGGER.error("Error", ex);
        return Errors.builder().errors(Collections.singletonList(ex.getMessage())).build();
    }

}
