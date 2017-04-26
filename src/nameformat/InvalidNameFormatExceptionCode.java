package nameformat;

/**
 * Created by dery on 4/26/17.
 */
public enum InvalidNameFormatExceptionCode implements ErrorCode {

    S_NOT_STRING("Name must be alphabeth"),
    S_ZERO_LENGTH("Name must be filled"),
    S_MAXIMUM("Name length hit the maximum allowed");

    private final String message;

    private InvalidNameFormatExceptionCode(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
