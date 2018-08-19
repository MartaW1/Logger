import model.Event;
import model.Request;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import repository.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Logger {


    private Request getSettingsForTest(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your testname: ");
        String testname = scanner.nextLine();
        System.out.println("Enter your account: ");
        String account = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        System.out.println("Enter your locationId: ");
        String locationId = scanner.nextLine();
        System.out.println("Enter your deviceId: ");
        String deviceId = scanner.nextLine();

        Request request = new Request(testname, account, password, locationId, deviceId);
        return request;
    }

    public void startLogging(){
        Request request = getSettingsForTest();

        System.setProperty("webdriver.chrome.driver", "/home/marcia/installs/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://account.smartthings.com/login/samsungaccount");
        driver.findElement(By.id("username")).sendKeys(request.getAccount());
        driver.findElement(By.id("password")).sendKeys(request.getPassword());
        driver.findElement(By.id("login-user-btn")).click();
        sleep();

        String shardUrl = driver.getCurrentUrl();
        String deviceEventUrl = shardUrl + "/device/" + request.getDeviceId() + "/events";
        driver.get(deviceEventUrl);
        sleep();
        driver.findElement(By.id("allLink")).click();
        sleep();

        List<WebElement> eventRows = driver.findElements(By.xpath("//*[@id=\"events-table\"]/tbody/tr[*]"));

        for(int row = 1; row<eventRows.size()+1; row++){
            WebElement eventRow = eventRows.get(row-1);
            Event event = new Event();
            String dirtyDate = eventRow.findElement(By.xpath(getEventCellXpath(row,1))).getText();
            event.setDate(toDate(dirtyDate));
            event.setSource(eventRow.findElement(By.xpath(getEventCellXpath(row,2))).getText());
            event.setType(eventRow.findElement(By.xpath(getEventCellXpath(row,3))).getText());
            event.setName(eventRow.findElement(By.xpath(getEventCellXpath(row,4))).getText());
            event.setValue(eventRow.findElement(By.xpath(getEventCellXpath(row,5))).getText());
            event.setUser(eventRow.findElement(By.xpath(getEventCellXpath(row,6))).getText());
            event.setDisplayedText(eventRow.findElement(By.xpath(getEventCellXpath(row,7))).getText());
            event.setDeviceId(request.getDeviceId());



            String id = event.getDate().toString()+event.getDeviceId();
            event.setId(id);
            if (!Repository.existsById(id)){
                Repository.save(event);
            } else {
                break;
            }
        }
    }

    private LocalDateTime toDate(String dirtyDate){
        String trimmedDate = dirtyDate.substring(0, 26).trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm:ss.SSS a");
        return LocalDateTime.parse(trimmedDate, formatter);

    }

    private String getEventCellXpath(int row, int cell){
        return "//*[@id=\"events-table\"]/tbody/tr["+row+"]/td["+cell+"]";
    }

    private void sleep(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e){
        }
    }




}
