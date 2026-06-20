import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestLogin {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.edgedriver().setup();

        WebDriver driver = new EdgeDriver();

        driver.get("http://localhost:8080/PROYTJAVA/login.jsp");

        Thread.sleep(2000);

        driver.findElement(By.name("usuario")).sendKeys("root");
        driver.findElement(By.name("contrasena")).sendKeys("1234");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        Thread.sleep(3000);

        String url = driver.getCurrentUrl();

        System.out.println("URL: " + url);

        if (url.contains("index.jsp")) {
            System.out.println("Login exitoso ✅");
        } else {
            System.out.println("Login fallido ❌");
        }

        Thread.sleep(5000);

        driver.quit();
    }
}