package testng;

import org.testng.annotations.Test;

public class TestingTestNG2 {
	
	
	@Test(groups="Things")
	public void TestCases(){
		System.out.println("What are you still doing here?");
	}
	
	@Test(groups="Stuff")
	public void TestCases2(){
		System.out.println("The show is over...");
	}
}
