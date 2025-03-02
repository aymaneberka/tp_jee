package ma.formation.servlet.dao;

public class DaoException extends RuntimeException {
    private int code;
    public DaoException(String message, int code) {
        super(message);
        this.code = code;
    }
}

