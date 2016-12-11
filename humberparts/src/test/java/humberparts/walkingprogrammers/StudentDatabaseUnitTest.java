package humberparts.walkingprogrammers;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by Ash on 2016-12-10.
 */

@RunWith(MockitoJUnitRunner.class)
public class StudentDatabaseUnitTest extends Mockito {
    @Mock
    Context mMockContext;

    @Mock
    Cursor cursor;

    @Test
    public void testInsertStudentData() {
        DatabaseActivity mStudentDatabase = mock(DatabaseActivity.class);
        boolean result = mStudentDatabase.insertData("5210", "2016-12-01", "20001");
        verify(mStudentDatabase).insertData("5210", "2016-12-01", "20001");
    }

    @Test
    public void testSearchStudentData() {
        DatabaseActivity mStudentDatabase = mock(DatabaseActivity.class);
        when(mStudentDatabase.search("500")).thenReturn(cursor);
        Assert.assertNotNull(mStudentDatabase.search("500"));
    }

    @Test
    public void testDeleteStudentData() {
        DatabaseActivity mStudentDatabase = mock(DatabaseActivity.class);
        when(mStudentDatabase.deleteData("500")).thenReturn(1);

        Integer deleteReturn = 1;
        Assert.assertEquals(deleteReturn, mStudentDatabase.deleteData("500"));
    }
}
