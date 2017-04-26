package nameformat;

/**
 * Created by dery on 4/26/17.
 */
public class InvalidNameFormatException extends Exception{
    private ErrorCode errorCode;
    public InvalidNameFormatException(ErrorCode errorCode){
        this.errorCode = errorCode;
    }

    public String getMessage(){
        return getErrorCode().getMessage();
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
