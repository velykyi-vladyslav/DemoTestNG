package velykyi.vladyslav.DemoTestNJ.exception;

public class EmailNotAllowedException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Email is not allowed";

    public EmailNotAllowedException() {
        super(DEFAULT_MESSAGE);
    }

    public EmailNotAllowedException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.PROCESSING_ERROR_TYPE;
    }
}
