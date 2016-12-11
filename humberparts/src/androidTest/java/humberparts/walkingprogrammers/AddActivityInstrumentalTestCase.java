package humberparts.walkingprogrammers;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by Ash on 2016-12-10.
 */

@RunWith(AndroidJUnit4.class)
public class AddActivityInstrumentalTestCase {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(AddActivity.class);

    @Test
    public void testAddClick() {
        onView(withId(R.id.edittextstudentnumber)).perform(typeText("Test_Student"), closeSoftKeyboard());
        onView(withId(R.id.Part)).perform(typeText("Test_1"), closeSoftKeyboard());
        onView(withId(R.id.Part2)).perform(typeText("Test_2"), closeSoftKeyboard());
        onView(withId(R.id.Part3)).perform(typeText("Test_3"), closeSoftKeyboard());
        onView(withId(R.id.Part4)).perform(typeText("Test_4"), closeSoftKeyboard());
        onView(withId(R.id.Part5)).perform(typeText("Test_5"), closeSoftKeyboard());
        onView(withId(R.id.buttonadd1)).perform(click());
    }
}
