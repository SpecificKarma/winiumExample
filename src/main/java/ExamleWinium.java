
import org.openqa.selenium.By;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.assertEquals;

public class ExamleWinium {

    private WiniumDriver driver;
    WiniumDriverService service;
    DesktopOptions options = new DesktopOptions();

    //For tracking buttons use VisualUIAVerifyNative from Win10 SDK

    @BeforeTest
    void preparation() throws Exception {
        File winiumExe = new File("C:\\selenium-java-3.8.1\\winium\\Winium.Desktop.Driver.exe");
        service = new WiniumDriverService.Builder().usingDriverExecutable(winiumExe)
                .usingPort(9999)
                .withVerbose(true)
                .withSilent(false)
                .buildDesktopService();
        service.start();
    }

    @Test(priority = 1)
    void verify_Create_New_Document() {

//        1) Open Notepad app
        options.setApplicationPath("C:\\Windows\\SysWOW64\\notepad.exe");
        driver = new WiniumDriver(service, options);
//        2) Click "File"
        driver.findElement(By.name("File")).click();
//        3) Select "New"
        driver.findElement(By.name("New")).click();
//        4) Check the window title
        try {
            assertEquals("Untitled - Notepad", driver.findElement(By.className("Notepad")).getAttribute("Name"));
//        5) Check the text field
            assertEquals("", driver.findElement(By.className("Edit")).getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    void afterTest(){
        driver.quit();
    }
}
