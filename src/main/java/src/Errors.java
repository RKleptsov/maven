package src;

public class Errors extends RuntimeException {
    public static int errorCount = 0;

    public Errors(String a) {
        super(a);
        errorCount++;
    }

    public static int getErrorCount() {
        return errorCount;
    }
}