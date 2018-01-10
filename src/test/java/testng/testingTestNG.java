package testng;

import org.testng.annotations.Test;

public class testingTestNG {
	
	@Test(groups="Things")
	public void firstTest() {
		System.out.println("First test");
	}
	
	@Test(groups="Stuff")
	public void secondTest() {
		System.out.println("Second test");
	}
}
