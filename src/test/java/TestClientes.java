import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.NoSuchElementException;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestClientes {
    
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = null;
        
        try {
            WebDriverManager.edgedriver().setup();

            driver = new EdgeDriver();
            driver.manage().window().maximize();

            // 1. Navegar al formulario
            System.out.println("🔗 Navegando a registrar.jsp...");
            driver.get("http://localhost:8080/PROYTJAVA/web/cliente/registrar.jsp");
            Thread.sleep(3000);

            // 2. PRUEBA: Registrar nuevo cliente
            System.out.println("📝 Prueba: Registrar cliente...");
            
            try {
                System.out.println("   → Buscando campo 'nombre'...");
                driver.findElement(By.name("nombre")).sendKeys("Juan Pérez");
                System.out.println("   ✅ Campo 'nombre' encontrado y llenado");
            } catch (NoSuchElementException e) {
                System.out.println("   ❌ ERROR: No se encontró el campo 'nombre'");
                return;
            }

            try {
                System.out.println("   → Buscando campo 'apellido'...");
                driver.findElement(By.name("apellido")).sendKeys("Gonzales");
                System.out.println("   ✅ Campo 'apellido' encontrado y llenado");
            } catch (NoSuchElementException e) {
                System.out.println("   ❌ ERROR: No se encontró el campo 'apellido'");
                return;
            }
            
            try {
                driver.findElement(By.name("email")).sendKeys("juan.perez@test.com");
                System.out.println("   ✅ Campo 'email' llenado");
            } catch (NoSuchElementException e) {
                System.out.println("   ❌ ERROR: No se encontró el campo 'email'");
                return;
            }
            
            try {
                driver.findElement(By.name("cedula")).sendKeys("123456789");
                System.out.println("   ✅ Campo 'cedula' llenado");
            } catch (NoSuchElementException e) {
                System.out.println("   ❌ ERROR: No se encontró el campo 'cedula'");
                return;
            }
            
            try {
                driver.findElement(By.name("nCelular")).sendKeys("3001234567");
                System.out.println("   ✅ Campo 'nCelular' llenado");
            } catch (NoSuchElementException e) {
                System.out.println("   ❌ ERROR: No se encontró el campo 'nCelular'");
                return;
            }

            try {
                driver.findElement(By.name("direccion")).sendKeys("Calle 123 #45-67");
                System.out.println("   ✅ Campo 'direccion' llenado");
            } catch (NoSuchElementException e) {
                System.out.println("   ❌ ERROR: No se encontró el campo 'direccion'");
                return;
            }
            
            // 3. Enviar formulario
            System.out.println("🚀 Enviando formulario...");
            try {
                System.out.println("   → Buscando botón submit...");
                boolean clicked = false;
                
                try {
                    driver.findElement(By.cssSelector("button[type='submit']")).click();
                    clicked = true;
                    System.out.println("   ✅ Click con button[type='submit']");
                } catch (NoSuchElementException e1) {
                    try {
                        driver.findElement(By.cssSelector("input[type='submit']")).click();
                        clicked = true;
                        System.out.println("   ✅ Click con input[type='submit']");
                    } catch (NoSuchElementException e2) {
                        try {
                            driver.findElement(By.tagName("button")).click();
                            clicked = true;
                            System.out.println("   ✅ Click con tagName button");
                        } catch (NoSuchElementException e3) {
                            System.out.println("   ❌ ERROR: No se encontró botón para enviar");
                            return;
                        }
                    }
                }
                
                if (clicked) {
                    Thread.sleep(4000);
                    System.out.println("📊 URL después del submit: " + driver.getCurrentUrl());
                    System.out.println("📄 Título de página: " + driver.getTitle());
                    
                    String source = driver.getPageSource();
                    
                    // ✅ Validación para ruta /ClienteServlet (sin /web/)
                    if (driver.getCurrentUrl().contains("/ClienteServlet") && !source.contains("404")) {
                        System.out.println("✅ ¡Servlet respondido correctamente!");
                    }
                    else if (source.contains("HTTP Status 404") || source.contains("No encontrado")) {
                        System.out.println("❌ ERROR 404: Servlet no encontrado en /ClienteServlet");
                        System.out.println("💡 Verifica:");
                        System.out.println("   • @WebServlet(\"/ClienteServlet\") en tu servlet");
                        System.out.println("   • action=\"${pageContext.request.contextPath}/ClienteServlet\" en el JSP");
                    }
                    else if (source.contains("HTTP Status 500") || source.contains("excepción")) {
                        System.out.println("❌ ERROR 500: El servlet falló. Revisa la consola de Tomcat.");
                    }
                    else if (source.contains("éxito") || source.contains("registrado")) {
                        System.out.println("✅ ¡Registro exitoso detectado!");
                    }
                    else {
                        System.out.println("⚠️ Resultado indeterminado - revisa la salida");
                    }
                }
                
            } catch (Exception e) {
                System.out.println("❌ Excepción al hacer click: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.out.println("❌ Excepción general en el test:");
            e.printStackTrace();
        } finally {
            if (driver != null) {
                Thread.sleep(2000);
                driver.quit();
            }
            System.out.println("🏁 Pruebas de clientes finalizadas");
        }
    }
}