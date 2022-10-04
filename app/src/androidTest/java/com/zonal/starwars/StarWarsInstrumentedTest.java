package com.zonal.starwars;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class StarWarsInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.zonal.starwars", appContext.getPackageName());
    }

    //TODO:
    //  Test the App gets the Planet list correctly from the api
    //  Test the list selection grabs the right Planet and starts the correct activity
    //  Test the menu items work
    //  Test the refresh layout works
    //  Test the refresh Planet details works
    //  Test the back arrow works (Planet Activity -> StarWarActivity)
}
