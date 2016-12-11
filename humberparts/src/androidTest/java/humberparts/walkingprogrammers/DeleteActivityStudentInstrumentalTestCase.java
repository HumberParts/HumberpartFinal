package humberparts.walkingprogrammers;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Ash on 2016-12-10.
 */

@RunWith(AndroidJUnit4.class)
public class DeleteActivityStudentInstrumentalTestCase {

    @Rule
    public ActivityTestRule deleteStudentActivity = new ActivityTestRule(DeleteActivity.class);

    @Test
    public void deleteStudentId() {
        onView(withId(R.id.ed_del_student_num)).perform(typeText("500"), closeSoftKeyboard());
        onView(withId(R.id.buttondelete1)).perform(click());
    }
}
