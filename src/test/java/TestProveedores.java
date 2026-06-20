import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.JavascriptExecutor; 
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestProveedores {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = null;

        try {
            WebDriverManager.edgedriver().setup();

            driver = new EdgeDriver();
            driver.manage().window().maximize();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            JavascriptExecutor js = (JavascriptExecutor) driver;

            System.out.println("🔗 Navegando a ProveedorServlet?accion=nuevo...");
            driver.get("http://localhost:8080/PROYTJAVA/ProveedorServlet?accion=nuevo");
            
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("form")));
            Thread.sleep(2000);

            System.out.println("📝 Registrando proveedor de prueba...");
 
            driver.findElement(By.name("nombreProveedor")).sendKeys("Proveedor Test Selenium");

            driver.findElement(By.name("nit")).sendKeys("900123456");

            driver.findElement(By.name("nombreContacto")).sendKeys("Juan Pérez");

            driver.findElement(By.name("nCelular")).sendKeys("3001234567");

            driver.findElement(By.name("email")).sendKeys("juan.perez@proveedortest.com");

            System.out.println("✅ Campos completados correctamente");

            WebElement btnSubmit = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']"))
            );

            js.executeScript("arguments[0].click();", btnSubmit);

            Thread.sleep(4000);

            System.out.println("📊 URL actual: " + driver.getCurrentUrl());
            String source = driver.getPageSource();

            if (source.contains("HTTP Status 404")) {
                System.out.println("❌ ERROR 404 - Servlet no encontrado");
            } 
            else if (source.contains("HTTP Status 500")) {
                System.out.println("❌ ERROR 500 - Error en servidor");
            } 
            else if (driver.getCurrentUrl().contains("ProveedorServlet") || 
                     source.toLowerCase().contains("registrado") ||
                     source.toLowerCase().contains("exitoso")) {
                System.out.println("✅ Proveedor registrado exitosamente");
            } 
            else {
                System.out.println("⚠️ Resultado no claro - Revisar página manualmente");
            }

        } catch (NoSuchElementException e) {
            System.out.println("❌ ERROR: Campo no encontrado en el formulario");
            if (driver != null) {
                System.out.println("🔍 Inputs encontrados:");
                driver.findElements(By.tagName("input")).forEach(el -> 
                    System.out.println("  • name='" + el.getAttribute("name") + 
                                       "' value='" + el.getAttribute("value") + "'")
                );
            }
            e.printStackTrace();
            
        } catch (Exception e) {
            System.out.println("❌ ERROR GENERAL: " + e.getMessage());
            e.printStackTrace();
            
        } finally {
            if (driver != null) {
                Thread.sleep(2000);
                driver.quit();
            }
            System.out.println("🏁 Test de proveedores finalizado");
        }
    }
}