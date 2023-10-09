package com.example.cardiacrecord;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class test_Test {

    @Test
    public void testAddRecord(){
        Records recordList= new Records();
        ModelClass modelClass= new ModelClass("1-1-2000","10:20","89","67","67","dummy");
        recordList.addRecord(modelClass);
        assertEquals(1, recordList.mcl.size());

        ModelClass modelClass1=new ModelClass("1-1-2000","10:20","89","67","67","dummy");
        recordList.addRecord(modelClass1);
        assertEquals(2,recordList.mcl.size());
        assertTrue(recordList.mcl.contains(modelClass));
        assertTrue(recordList.mcl.contains(modelClass1));

    }

    @Test
    public void addRecordExTest(){
        Records recordList= new Records();
        ModelClass modelClass= new ModelClass("1-1-2000","10:20","89","67","67","dummy");
        recordList.addRecord(modelClass);
        assertThrows(IllegalArgumentException.class, () -> recordList.addRecord(modelClass));
    }
    @Test
    public void testDeleteRecord() {
        //int position=0;
        Records recordList = new Records();
        ModelClass record1 = new ModelClass("1-1-2000", "10:20", "89", "67", "67", "dummy");
        recordList.addRecord(record1);
        assertEquals(1, recordList.mcl.size());

        Records recordList1 = new Records();
        ModelClass record2 = new ModelClass("1-1-2000", "10:20", "89", "67", "67", "dummy");
        recordList.addRecord(record2);
        assertEquals(2, recordList.mcl.size());


        Records recordList2 = new Records();
        ModelClass record3 = new ModelClass("1-1-2000", "10:20", "89", "67", "67", "dummy");
        recordList.addRecord(record3);
        assertEquals(3, recordList.mcl.size());

        assertTrue(recordList.mcl.contains(record1));
        assertTrue(recordList.mcl.contains(record2));

        recordList.deleteRecord(0);
        assertEquals(2, recordList.mcl.size());
        assertFalse(recordList.mcl.contains(record1));

        recordList.deleteRecord(0);
        assertEquals(1, recordList.mcl.size());
        assertFalse(recordList.mcl.contains(record2));

        assertThrows(IllegalArgumentException.class, () -> recordList.deleteRecord(1));
    }
    @Test
    public void testCount(){
        Records recordList=new Records();
        ModelClass modelClass1=new ModelClass("1-1-2000","10:20","89","67","67","dummy");
        recordList.addRecord(modelClass1);
        assertEquals(1,recordList.count());

    }
}