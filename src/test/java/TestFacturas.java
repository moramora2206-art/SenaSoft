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

public class TestFacturas {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = null;

        try {
            WebDriverManager.edgedriver().setup();

            driver = new EdgeDriver();
            driver.manage().window().maximize();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            System.out.println("🔗 Navegando a FacturaServlet?accion=nuevo...");
            driver.get("http://localhost:8080/PROYTJAVA/FacturaServlet?accion=nuevo");
            
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("form")));
            Thread.sleep(2000);

            System.out.println("📝 Creando factura de prueba...");

            driver.findElement(By.name("idUsuario")).sendKeys("1");

            driver.findElement(By.name("idCliente")).sendKeys("2");

            WebElement selectPago = driver.findElement(By.name("formaPago"));
            selectPago.findElement(By.xpath("//option[.='EFECTIVO']")).click();
  
            driver.findElement(By.name("descuento")).sendKeys("0");

            driver.findElement(By.name("observaciones")).sendKeys("Factura generada por Selenium");

            System.out.println("✅ Campos principales completados");


            System.out.println("🔍 Buscando producto ID=2...");
            driver.findElement(By.id("idProducto")).sendKeys("2");

            WebElement btnBuscar = driver.findElement(By.xpath("//button[text()='Buscar']"));
            js.executeScript("arguments[0].click();", btnBuscar);

            WebElement campoNombre = driver.findElement(By.id("nombre"));

            wait.until(ExpectedConditions.attributeToBeNotEmpty(campoNombre, "value"));
            Thread.sleep(1500);

            String nombreProd = driver.findElement(By.id("nombre")).getAttribute("value");
            if (nombreProd.isEmpty() || nombreProd.equals("Nombre")) {
                System.out.println("❌ Producto no encontrado, usando datos manuales");
                driver.findElement(By.id("nombre")).sendKeys("Producto Manual");
                driver.findElement(By.id("precio")).sendKeys("10.00");
            } else {
                System.out.println("✅ Producto encontrado: " + nombreProd);
            }

            driver.findElement(By.id("cantidad")).sendKeys("2");
            WebElement btnAgregar = driver.findElement(By.xpath("//button[text()='➕ Agregar']"));
            js.executeScript("arguments[0].click();", btnAgregar);
            
            Thread.sleep(1000);

            WebElement tabla = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cuerpoTabla")));
            if (tabla.findElements(By.tagName("tr")).size() > 0) {
                System.out.println("✅ Producto agregado a la tabla");
            } else {
                System.out.println("❌ Producto NO apareció en la tabla");
            }

            System.out.println("🔍 Agregando segundo producto ID=2...");
            driver.findElement(By.id("idProducto")).clear();
            driver.findElement(By.id("idProducto")).sendKeys("2");
            js.executeScript("arguments[0].click();", btnBuscar);
            Thread.sleep(1500);
            
            driver.findElement(By.id("cantidad")).clear();
            driver.findElement(By.id("cantidad")).sendKeys("1");
            js.executeScript("arguments[0].click();", btnAgregar);
            Thread.sleep(1000);
            
            int filas = tabla.findElements(By.tagName("tr")).size();
            System.out.println("📊 Productos en tabla: " + filas);

            String totalVista = driver.findElement(By.id("totalVista")).getText();
            System.out.println("💰 Total calculado: $" + totalVista);

            // ========================================

            System.out.println("💾 Guardando factura...");
            WebElement btnSubmit = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']"))
            );
            btnSubmit.click();

            Thread.sleep(5000);

            System.out.println("📊 URL final: " + driver.getCurrentUrl());
            String source = driver.getPageSource();

            if (source.contains("HTTP Status 404")) {
                System.out.println("❌ ERROR 404 - FacturaServlet no encontrado");
            } else if (source.contains("HTTP Status 500")) {
                System.out.println("❌ ERROR 500 - Revisa logs de Tomcat");
                System.out.println("💡 Posibles causas: IDs inválidos, productos sin stock, error en DAO");
            } else if (driver.getCurrentUrl().contains("FacturaServlet") || 
                       source.toLowerCase().contains("exitoso") ||
                       source.toLowerCase().contains("registrada")) {
                System.out.println("✅ Factura creada exitosamente");
            } else {
                System.out.println("⚠️ Resultado no claro - Revisar página manualmente");
            }

        } catch (NoSuchElementException e) {
            System.out.println("❌ ERROR: Campo no encontrado");
            if (driver != null) {
                System.out.println("🔍 Inputs disponibles:");
                driver.findElements(By.tagName("input")).forEach(el -> 
                    System.out.println("  • name='" + el.getAttribute("name") + "' id='" + el.getAttribute("id") + "'")
                );
            }
            e.printStackTrace();
            
        } catch (Exception e) {
            System.out.println("❌ ERROR GENERAL: " + e.getMessage());
            e.printStackTrace();
            
        } finally {
            if (driver != null) {
                Thread.sleep(3000);
                driver.quit();
            }
            System.out.println("🏁 Test de facturas finalizado");
        }
    }
}