# 💱 Conversor de Monedas

Este proyecto es una aplicación de consola desarrollada en Java que permite realizar conversiones de divisas en tiempo real. Consume los datos de la [ExchangeRate-API](https://www.exchangerate-api.com/) y está diseñado aplicando los principios de la Programación Orientada a Objetos (POO) y buenas prácticas de arquitectura de software.

## 📝 Descripción

El conversor ofrece una interfaz interactiva por consola donde el usuario puede elegir entre diversas opciones de cambio de moneda (USD, PEN, ARS, BRL, COP). El sistema se conecta a una API externa, extrae y filtra la tasa de cambio exacta mediante el parseo de estructuras JSON, y calcula el valor final, registrando cada operación exitosa en un archivo de texto local.

## ✨ Características Principales

* **Consumo de API REST:** Integración nativa con `java.net.http.HttpClient` para realizar peticiones HTTP (GET) y obtener tasas de cambio actualizadas.
* **Parseo JSON manual:** Uso de la librería `Gson` (`JsonParser`, `JsonObject`) para navegar eficientemente por el árbol JSON y filtrar únicamente los nodos necesarios (Currency Codes).
* **Programación Defensiva:** Validación estricta de entradas del usuario. El sistema captura `InputMismatchException` para evitar caídas por errores de tipeo y restringe conversiones con montos negativos o iguales a cero.
* **Registro de Historial (Logging):** Creación y escritura automática (modo *append*) en un archivo `historial.txt` utilizando `FileWriter`, marcando cada transacción con su fecha y hora exacta (`LocalDateTime`).

## 🏗️ Arquitectura y Estructura del Código

El proyecto prioriza la alta cohesión y la separación de responsabilidades (Separation of Concerns). Se divide en las siguientes clases clave:

1. `ConversorApp.java`: El punto de entrada puro de la aplicación. Su única responsabilidad es instanciar y arrancar el flujo principal.
2. `Conversor.java`: Orquesta la interacción con el usuario (interfaz de consola) y la lógica de validación de entradas.
3. `ApiService.java`: Encapsula toda la lógica de infraestructura de red. Se encarga exclusivamente de armar la URI, hacer la petición HTTP y parsear la respuesta JSON para extraer la tasa.
4. `Historial.java`: Aísla la lógica de persistencia de datos (I/O). Se encarga de formatear la fecha y escribir en el disco.

## 🚀 Tecnologías Utilizadas

* **Lenguaje:** Java (JDK 17 o superior)
* **Librerías externas:** [Gson](https://github.com/google/gson) (para manipulación de JSON, última versión actual)
* **API:** ExchangeRate-API

## ⚙️ Configuración y Ejecución

1. Clona este repositorio:
   ```bash
   git clone [https://github.com/d27pomaramirez-lab/challenge-conversor-de-monedas.git](https://github.com/d27pomaramirez-lab/challenge-conversor-de-monedas.git)
   o descargar el archivo zip y extraer en una carpeta.
2. Abre el proyecto en tu IDE favorito
3. Asegurate de tener la libreía Gson agregada a las dependencias de tu proyecto
4. Obtén tu propia API Key en ExchangeRate-API y colócala en la variable API_KEY dentro de la clase ApiService.java (por seguridad se adjunto como vacío)
5. Ejecuta la clase ConversorApp.java

## 📜 Historial de Conversiones
El programa genera automáticamente un archivo llamado historial.txt en la raíz del proyecto. Un ejemplo de su salida:
[02-03-2026 14:15:30] Conversión: 100.00 [USD] ===> 98500.00 [ARS]

[02-03-2026 14:16:12] Conversión: 50.00 [BRL] ===> 10.15 [USD]

### Tecnologías utilizadas
<img width="40" height="40" alt="intellij" src="https://github.com/user-attachments/assets/b0894a96-b4c2-46b9-96f9-407a3fd1caee" /> <img width="40" height="40" alt="java" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJEYp53vIyZaY3wclh9alfY8bK1UxUTFJ6XA&s" />



### Desarrollador

| [<img src="https://avatars.githubusercontent.com/u/224181779?s=400&u=b542509272eef999a81a70ad84b7084ea4ab8740&v=4" width=115><br><sub>Poma Ramirez Diego</sub>](https://github.com/d27pomaramirez-lab) 
