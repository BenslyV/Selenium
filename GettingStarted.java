package BensQA.Test;

import java.io.IOException;

import org.testng.annotations.Test;

import BensQA.Base;

public class GettingStarted extends Base {
	@Test
	void test() {
		System.out.println("hello framwork");
	}

	@Test
	void basePageNav() throws IOException {
		initDriver();
		driver.get("https://www.google.co.in/");
		//driver.get(url());---- need to check why this is not working
	}
}
