package config;

import org.openqa.selenium.WebDriver;

public class DriverFactory {
    // ThreadLocal to ensure each thread gets its own WebDriver instance
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    // Sets the WebDriver instance for the current thread
    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    // Quits and removes the WebDriver instance for the current thread
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}