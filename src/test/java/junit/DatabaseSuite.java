package junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	//Define test modules for Database suite here
	UsersTest.class,
	OrderModule.class
})
public class DatabaseSuite {
	//This class is just for defining the suite.
}
