package com.noisyninja.androidlistpoc;

import com.noisyninja.androidlistpoc.modules.DatabaseModuleTest;
import com.noisyninja.androidlistpoc.modules.NetworkModuleTest;
import com.noisyninja.androidlistpoc.modules.UtilModuleTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by sudiptadutta on 18/05/18.
 */


@RunWith(Suite.class)
@Suite.SuiteClasses({
        DatabaseModuleTest.class,
        UtilModuleTest.class,
        NetworkModuleTest.class
})
public class UITestSuite {
}
