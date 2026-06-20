from selenium import webdriver
from selenium.webdriver.common.by import By
import time

driver = webdriver.Edge()

driver.get("http://localhost/tu_proyecto")

time.sleep(2)

driver.find_element(By.NAME, "usuario").send_keys("admin")
driver.find_element(By.NAME, "password").send_keys("123")
driver.find_element(By.ID, "login").click()

time.sleep(3)

# Captura para el trabajo
driver.save_screenshot("login.png")

driver.quit()