package med.medapp.exception;

public class AppointmentAlreadyDoneException extends RuntimeException{
    public AppointmentAlreadyDoneException(String message) {
        super(message);
    }
}
