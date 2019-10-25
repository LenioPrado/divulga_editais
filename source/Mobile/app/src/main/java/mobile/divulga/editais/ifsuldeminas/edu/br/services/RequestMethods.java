package mobile.divulga.editais.ifsuldeminas.edu.br.services;

public enum RequestMethods {
    DEPRECATED_GET_OR_POST (-1),
    GET (0),
    POST (1),
    PUT (2),
    DELETE (3),
    HEAD (4),
    OPTIONS (5),
    TRACE (6),
    PATCH (7);

    private final int value;

    RequestMethods(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
