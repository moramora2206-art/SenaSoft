import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestProductos {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = null;

        try {
            WebDriverManager.edgedriver().setup();

            driver = new EdgeDriver();
            driver.manage().window().maximize();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            System.out.println("🔗 Navegando a ProductoServlet?accion=nuevo...");
            driver.get("http://localhost:8080/PROYTJAVA/ProductoServlet?accion=nuevo");
            
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("form")));
            Thread.sleep(2000);

            System.out.println("📝 Registrando producto de prueba...");

            WebElement selectElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.name("idProveedor"))
            );
            Select selectProveedor = new Select(selectElement);
            List<WebElement> opciones = selectProveedor.getOptions();
            
            System.out.println("📦 Opciones en el select: " + opciones.size());
            
            if (opciones.size() > 1) {
                selectProveedor.selectByIndex(1); 
                System.out.println("✅ Proveedor seleccionado");
            } else {
                System.out.println("⚠️ No hay proveedores en la BD o no se cargaron");
                System.out.println("💡 Inserta un proveedor de prueba en la base de datos");
                return;  
            }

            driver.findElement(By.name("nombre")).sendKeys("Producto Selenium");

            driver.findElement(By.name("codigoSku")).sendKeys("1001");

            driver.findElement(By.name("precio")).sendKeys("29.99");

            driver.findElement(By.name("stock")).sendKeys("50");

            ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                    "document.getElementsByName('fechaVencimiento')[0].value='2025-10-31';"
                );

            driver.findElement(By.name("descripcion")).sendKeys("Producto generado por test automatizado");

            System.out.println("✅ Campos completados");

            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")))
                .click();

            Thread.sleep(4000);

            System.out.println("📊 URL: " + driver.getCurrentUrl());
            String source = driver.getPageSource();

            if (source.contains("HTTP Status 404")) {
                System.out.println("❌ ERROR 404 - Revisa web.xml o @WebServlet");
            } else if (source.contains("HTTP Status 500")) {
                System.out.println("❌ ERROR 500 - Revisa logs de Tomcat");
            } else if (driver.getCurrentUrl().contains("ProductoServlet") || source.contains("registrado")) {
                System.out.println("✅ Producto registrado exitosamente");
            } else {
                System.out.println("⚠️ Verificar resultado manualmente");
            }

        } catch (NoSuchElementException e) {
            System.out.println("❌ Elemento no encontrado");
   
            if (driver != null) {
                System.out.println("🔍 Inputs en la página:");
                driver.findElements(By.tagName("input")).forEach(el -> 
                    System.out.println("  • name='" + el.getAttribute("name") + "' type='" + el.getAttribute("type") + "'")
                );
            }
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("❌ ERROR: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (driver != null) {
                Thread.sleep(2000);
                driver.quit();
            }
            System.out.println("🏁 Test finalizado");
        }
    }
}