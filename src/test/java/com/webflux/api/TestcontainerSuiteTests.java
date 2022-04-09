package com.webflux.api;


import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Tc-Compose: TestcontainerSuite")
@SelectPackages({
     "com.webflux.api.modules.project.core.exceptions",
     "com.webflux.api.core.exceptions",
     "com.webflux.api.modules.project.resource",
     "com.webflux.api.modules.task",
})
@IncludeTags({"testcontainer"})
public class TestcontainerSuiteTests {
}