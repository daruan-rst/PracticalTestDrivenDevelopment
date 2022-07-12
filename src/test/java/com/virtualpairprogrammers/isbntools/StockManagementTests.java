package com.virtualpairprogrammers.isbntools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class StockManagementTests {



    @Test
    public void testCanGetACorrectLocatorCode(){
        ExternalISBNDataService testWebService = new ExternalISBNDataService() {
            //test stub
            @Override
            public Book lookup(String isbn) {
                return new Book(isbn, "Of Mice and Men", "J. Steinbeck");
            }
        };


        ExternalISBNDataService testDatabaseService = new ExternalISBNDataService() {
            //test stub
            @Override
            public Book lookup(String isbn) {

                return null;
            }
        };

        String isbn = "0140177396";
        StockManager stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(testDatabaseService);
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4", locatorCode);

    }

    @Test
    public void databaseIsUsedIfDataIsPresent(){
        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

        when(databaseService.lookup("0140177396"))
                .thenReturn(new Book("0140177396","abc","abc"));

        String isbn = "0140177396";
        StockManager stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);

        String locatorCode = stockManager.getLocatorCode(isbn);
        verify(databaseService).lookup("0140177396");
        verify(webService, never()).lookup(anyString());
    }

    @Test
    public void webserviceIsUsedIfDataIsPresentInDatabase(){
        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

        when(webService.lookup("0140177396"))
                .thenReturn(new Book("0140177396","abc","abc"));

        String isbn = "0140177396";
        StockManager stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);

        String locatorCode = stockManager.getLocatorCode(isbn);
        verify(webService).lookup("0140177396");
        verify(databaseService).lookup("0140177396");
    }

}
