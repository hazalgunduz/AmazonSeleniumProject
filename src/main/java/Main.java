public class Main {


    //Sets driver for the tests
    static {
        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
    }


    public static void main(String[] args) throws Exception {

        TestClass runObj = new TestClass();
        runObj.setUp();
        runObj.testAmazonLogIn();
        runObj.itemSearch();
        runObj.tearDown();
    }
}







