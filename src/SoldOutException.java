public class SoldOutException extends Exception {

    String message;

    public SoldOutException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
