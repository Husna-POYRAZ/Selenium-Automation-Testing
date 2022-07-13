import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class searchTest {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(chromeOptions);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Case 1 : Open tatilBudur website
        driver.manage().window().maximize();
        driver.get("https://www.tatilbudur.com/");
        Thread.sleep(1000);

        // Case 2: The word "Antalya" is entered in the search bar in the HOTEL tab.
        driver.findElement(By.id("key")).click();
        driver.findElement(By.id("key")).sendKeys("Antalya");
        Thread.sleep(1000);

        // Case 3: Start date selected August 1, 2022.
        driver.findElement(By.id("hotelSearchCheckIn")).click();
        driver.findElement(By.xpath("//th[contains(text(), 'Temmuz 2022')]")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Ağu')]")).click();
        driver.findElement(By.xpath("//tbody/tr[2]/td[1]")).click();

        // Case 4: End date selected August 8, 2022.
        driver.findElement(By.xpath("//tbody/tr[3]/td[1]")).click();
        Thread.sleep(500);

        // Case 5: 2 adults are selected in the person area.
        driver.findElement(By.xpath("//*[@id=\"searchFormHotel\"]/div/div/div[3]/div/div[2]/div/div[1]")).click();
        driver.findElements(By.xpath("//*[@id=\"closable-wrapper\"]/span")).get(0).click();
        Thread.sleep(500);

        // Case 6: Press the "Search" button
        driver.findElement(By.xpath("//button[@id='searchFormHotelSubmitBtn']")).click();
        /*
        WebElement searchButton = driver.findElement(By.xpath("//button[@id='searchFormHotelSubmitBtn']"));
        searchButton.click();
         */
        Thread.sleep(2000);



        // Case 7: The hotel that comes to the top in the results is selected and the detail page is passed.
        // Open new window by clicking the button
        Thread.sleep(3000);
        js.executeScript("window.scrollTo(0,185)");
        Thread.sleep(500);
        driver.findElement(By.linkText("Oteli İncele")).click();
        Thread.sleep(1000);

        // Case 8: On the detail page, the top room is selected and the payment page is entered.
        // Get handles of the windows
        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));

        js.executeScript("window.scrollTo(0,700)");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@id='roomSelectBtn1']")).click();
        Thread.sleep(3000);

        // Case 9: "Do You Want Transportation?" The I Want option is selected in the field.
        js.executeScript("window.scrollTo(0,900)");
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"personInfo\"]/section[4]/div[1]/div[2]/label[2]")).click();
        Thread.sleep(500);

        // Case 10: "Sabiha Gökçen" is written for the departure airport in the departure section.
        driver.findElement(By.xpath("//*[@id=\"txtFromLocationReservation\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"txtFromLocationReservation\"]")).sendKeys("Sabiha Gökçen");
        Thread.sleep(500);

        // Case 11: In the return section, Sabiha Gökçen is written to the destination airport.
        driver.findElement(By.xpath("//*[@id=\"txtToLocationReservation\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"txtToLocationReservation\"]")).sendKeys("Sabiha Gökçen");
        Thread.sleep(500);

        // Case 12: Press the "Search" button.
        driver.findElement(By.xpath("//*[@id=\"personInfo\"]/section[4]/div[3]/div/button")).click();
        Thread.sleep(3000);

        // Case 13: Check if your planes are listed
        WebElement assertElement = driver.findElement(By.xpath("//*[@id=\"personInfo\"]/section[4]/div[4]/div[1]/div[1]/div[3]/ul/li"));
        String assertText = assertElement.getText();
        System.out.println(assertText);

        Thread.sleep(2000);
        driver.quit();
    }
}
/*
        WebElement assertElement = driver.findElement(By.id("hotelSearchCheckIn"));
        String assertText = assertElement.getText();
        System.out.println(assertText);
        Assert.assertEquals(assertText, "Bu benim text assertim");

        Assert.assertTrue(assertElement.isDisplayed());
*/