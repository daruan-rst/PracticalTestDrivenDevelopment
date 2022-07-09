package com.virtualpairprogrammers.isbntools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockManagementTests {



    @Test
    public void testCanGetACorrectLocatorCode(){
        ExternalISBNDataService testService = new ExternalISBNDataService() {
            //test stub
            @Override
            public Book lookup(String isbn) {
                return new Book(isbn, "Of Mice and Men", "J. Steinbeck");
            }
        };

        String isbn = "0140177396";
        StockManager stockManager = new StockManager();
        stockManager.setService(testService);
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4", locatorCode);

    }
}
