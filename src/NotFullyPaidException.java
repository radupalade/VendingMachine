public class NotFullyPaidException extends Exception {
    String message;
    long remaining;

    public NotFullyPaidException(String message, long remaining) {
        this.message = message;
        this.remaining = remaining;
    }


    public long getRemaining() {
        return remaining;
    }

    @Override
    public String getMessage() {  //@override
        return message + remaining;
    }


}
