package com.delivery.database.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ApplicationTest {

    private Application app;

    @Before
    public void initApplication(){
        app = new Application();
    }

    @Test
    public void testConstDocuments(){
        assertEquals("documents", Application.BAGGAGE_DOCUMENTS);
    }

    @Test
    public void testConstPackage(){
        assertEquals("package", Application.BAGGAGE_PACKAGE);
    }

    @Test
    public void testConstFragile(){
        assertEquals("fragile", Application.BAGGAGE_FRAGILE);
    }

    @Test
    public void testConstClothes(){
        assertEquals("clothes", Application.BAGGAGE_CLOTHES);
    }

    @Test
    public void testConstInProcessing(){
        assertEquals("in_processing", Application.STATE_IN_PROCESSING);
    }

    @Test
    public void testConstSent(){
        assertEquals("sent", Application.STATE_SENT);
    }

    @Test
    public void testConstWaitingForPayment(){
        assertEquals("waiting_for_payment", Application.STATE_WAITING_FOR_PAYMENT);
    }

    @Test
    public void testSetGetState(){
        app.setState(Application.STATE_SENT);
        assertEquals(Application.STATE_SENT, app.getState());
    }

    @Test
    public void testSetGetBaggageType(){
        app.setBaggageType(Application.BAGGAGE_CLOTHES);
        assertEquals(Application.BAGGAGE_CLOTHES, app.getBaggageType());
    }

}
