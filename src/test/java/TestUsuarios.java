import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestUsuarios {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = null;

        try {
            WebDriverManager.edgedriver().setup();

            driver = new EdgeDriver();
            driver.manage().window().maximize();

            // 1. Navegar al formulario
            System.out.println("🔗 Navegando a registrar.jsp...");
            driver.get("http://localhost:8080/PROYTJAVA/web/usuario/registrar.jsp");
            Thread.sleep(3000);

            System.out.println("📝 Registrando usuario completo...");

            // 🔹 Usuario
            driver.findElement(By.name("usuario")).sendKeys("usuarioTest");

            // 🔹 Password
            driver.findElement(By.name("password")).sendKeys("123456");

            // 🔹 Nombre
            driver.findElement(By.name("nombre")).sendKeys("Juan");

            // 🔹 Apellido
            driver.findElement(By.name("apellido")).sendKeys("Pérez");

            // 🔹 Email
            driver.findElement(By.name("email")).sendKeys("juan@test.com");

            // 🔹 Celular
            driver.findElement(By.name("ncelular")).sendKeys("3001234567");

            // 🔹 Rol (SELECT)
            Select selectRol = new Select(driver.findElement(By.name("rol")));
            selectRol.selectByVisibleText("admin");

            System.out.println("✅ Todos los campos llenados");

            // 🚀 Enviar formulario
            driver.findElement(By.cssSelector("button[type='submit']")).click();

            Thread.sleep(4000);

            System.out.println("📊 URL: " + driver.getCurrentUrl());

            String source = driver.getPageSource();

            // 🔍 Validaciones
            if (driver.getCurrentUrl().contains("UsuarioServlet") && !source.contains("404")) {
                System.out.println("✅ Servlet ejecutado correctamente");
            }
            else if (source.contains("HTTP Status 404")) {
                System.out.println("❌ ERROR 404 - Servlet no encontrado");
            }
            else if (source.contains("HTTP Status 500")) {
                System.out.println("❌ ERROR 500 - Error en servidor");
            }
            else {
                System.out.println("⚠️ Resultado no claro");
            }

        } catch (NoSuchElementException e) {
            System.out.println("❌ ERROR: No se encontró un campo");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("❌ ERROR GENERAL");
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