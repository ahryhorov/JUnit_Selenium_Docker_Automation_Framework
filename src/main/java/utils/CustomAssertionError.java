package utils;

public class CustomAssertionError extends AssertionError{
    public CustomAssertionError (Exception e) {
        super(e.getMessage() + "\n" + e.getCause());
        e.printStackTrace();
    }
}
