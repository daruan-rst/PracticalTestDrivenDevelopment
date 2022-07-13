package com.virtualpairprogrammers.isbntools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StockManagementTests {

    ExternalISBNDataService testWebService;
    ExternalISBNDataService testDatabaseService;
    StockManager stockManager;

    @BeforeEach
    public void setUp(){
        testWebService = mock(ExternalISBNDataService.class);
        testDatabaseService = mock(ExternalISBNDataService.class);
        stockManager = new StockManager();
        stockManager.setWebService(testWebService);

    }

    @Test
    public void testCanGetACorrectLocatorCode(){

        String isbn = "0140177396";

        when(testWebService.lookup(anyString())).thenReturn(new Book(isbn, "Of Mice and Men", "J. Steinbeck"));

        when(testDatabaseService.lookup(anyString())).thenReturn(null);

        stockManager.setDatabaseService(testDatabaseService);
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4", locatorCode);

    }

    @Test
    public void databaseIsUsedIfDataIsPresent(){

        when(testDatabaseService.lookup("0140177396"))
                .thenReturn(new Book("0140177396","abc","abc"));

        String isbn = "0140177396";

        String locatorCode = stockManager.getLocatorCode(isbn);
        verify(testDatabaseService).lookup("0140177396");
        verify(testWebService, never()).lookup(anyString());
    }

    @Test
    public void webserviceIsUsedIfDataIsPresentInDatabase(){

        when(testWebService.lookup("0140177396"))
                .thenReturn(new Book("0140177396","abc","abc"));

        String isbn = "0140177396";

        String locatorCode = stockManager.getLocatorCode(isbn);
        verify(testWebService).lookup("0140177396");
        verify(testDatabaseService).lookup("0140177396");
    }

}
