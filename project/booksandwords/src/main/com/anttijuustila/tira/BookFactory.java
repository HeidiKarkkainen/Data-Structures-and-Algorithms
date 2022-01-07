package com.anttijuustila.tira;

/**
 * @author Antti Juustila
 * @version 1.0
 */
public final class BookFactory {
    private BookFactory() {
    }

    /**
     * @return Your implementation of the Book interface.
     */
    public static Book createBook() {
        return new BookImplementation();
    }
}
