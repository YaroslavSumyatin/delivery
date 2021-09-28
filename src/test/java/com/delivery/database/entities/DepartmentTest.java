package com.delivery.database.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DepartmentTest {

    Department dep;

    @Before
    public void initDepartment() {
        dep = new Department();
    }

    @Test
    public void testSetGetIndex() {
        String index = "00000";
        dep.setIndex(index);
        assertEquals(index, dep.getIndex());
    }

    @Test
    public void testSetGetNumber() {
        int num = 1;
        dep.setNumber(1);
        assertEquals(num, dep.getNumber());
    }

    @Test
    public void testSetGetAddress() {
        String address = "address";
        dep.setAddress(address);
        assertEquals(address, dep.getAddress());
    }

}
