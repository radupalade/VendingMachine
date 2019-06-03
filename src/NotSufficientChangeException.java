public class NotSufficientChangeException extends Exception {
    String message;

    public NotSufficientChangeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() { // @Override
        return message;
    }
}
