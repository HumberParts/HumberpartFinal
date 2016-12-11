package humberparts.walkingprogrammers;

import android.content.Context;
import android.database.Cursor;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

/**
 * Created by Ash on 2016-12-10.
 */

@RunWith(MockitoJUnitRunner.class)
public class InventoryDatabaseUnitTest extends Mockito {

    @Mock
    Context mMockContext;

    @Mock
    Cursor cursor;

    @Test
    public void testInsertInventory() {
        InentoryDatabase mMockInventoryDb = mock(InentoryDatabase.class);
        boolean result = mMockInventoryDb.insertData("mock_testpart", "10");
        verify(mMockInventoryDb).insertData("mock_testpart", "10");
    }

    @Test
    public void testSearchInventory() {
        InentoryDatabase mMockInventoryDb = mock(InentoryDatabase.class);
        when(mMockInventoryDb.search("1")).thenReturn(cursor);

        Assert.assertNotNull(mMockInventoryDb.search("1"));
    }

    @Test
    public void testDeleteInventory() {
        InentoryDatabase mMockInventoryDb = mock(InentoryDatabase.class);
        when(mMockInventoryDb.deleteData("1")).thenReturn(1);

        Integer deleteEqual = 1;
        Assert.assertEquals(deleteEqual, mMockInventoryDb.deleteData("1"));
    }
}
