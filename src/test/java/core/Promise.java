package core;

import org.openqa.selenium.WebDriver;

public class Promise {

    private final WebDriver driver;

    Promise(WebDriver driver) {
        this.driver = driver;
    }

    public Promise Error() {
        return new Promise(driver);
    }

}
