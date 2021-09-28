package com.delivery.database.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WaybillTest {

    private Waybill waybill;

    @Before
    public void initWaybill() {
        waybill = new Waybill();
    }

    @Test
    public void testConstWaitingForPayment() {
        assertEquals("waiting_for_payment", Waybill.STATE_WAITING_FOR_PAYMENT);
    }

    @Test
    public void testConstPaid() {
        assertEquals("paid", Waybill.STATE_PAID);
    }

    @Test
    public void testSetGetState() {
        waybill.setState(Waybill.STATE_PAID);
        assertEquals(Waybill.STATE_PAID, waybill.getState());
    }

    @Test
    public void testSetGetCost() {
        float cost = 123.5F;
        waybill.setCost(cost);
        assertEquals(cost, waybill.getCost(), 0.0);
    }

}
