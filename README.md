# Juvica Backend 🚀

¡Bienvenido al backend de Juvica! 🎉 Aquí es donde se maneja toda la lógica y las funcionalidades principales de nuestra aplicación. Este backend está desarrollado con **Spring Boot** y expone varias APIs REST para interactuar con los datos de las **categorías** y los **trabajos**. 🙌

## Tecnologías Usadas 🔧

- **Spring Boot**: Para la creación de la API REST.
- **Java**: El lenguaje de programación principal.
- **Spring Data JPA**: Para la persistencia de los datos.
- **H2 Database** (por defecto para desarrollo): Se puede cambiar por cualquier base de datos relacional.

## API Endpoints 📡

Aquí te dejo un resumen de los endpoints más importantes de la API:

### Trabajos (Jobs)

- **GET /api/trabajos**: Obtiene todos los trabajos ordenados desde el más reciente.
- **GET /api/trabajos/{id}**: Obtiene los detalles de un trabajo por su ID.
- **PUT /api/trabajos/{id}**: Edita un trabajo existente (nombre, comentario largo, etc.).
- **POST /api/trabajos/{id}/media**: Añade archivos multimedia (imágenes, videos) a un trabajo. ¡Perfecto para agregar fotos o videos a tus proyectos! 📸🎥
- **DELETE /api/trabajos/{id}**: Elimina un trabajo según su ID.

### Categorías (Categories)

- **POST /api/categorias**: Crea una nueva categoría.
- **GET /api/categorias**: Obtiene todas las categorías disponibles.
- **GET /api/categorias/{id}**: Obtiene una categoría específica junto con sus trabajos.
- **POST /api/categorias/{id}/trabajos**: Añade un trabajo a una categoría.
- **DELETE /api/categorias/{id}**: Elimina una categoría.
- **POST /api/categorias/{id}/image**: Sube una imagen de la categoría.
- **PUT /api/categorias/{id}/remove-image**: Elimina la imagen de una categoría.

## Cómo Empezar 🚀

## Configuración del Entorno 🚀

### 1. Asegúrate de tener Java 17 y Maven instalados en tu máquina

Para poder ejecutar la aplicación, necesitas tener **Java 17** y **Maven** instalados. Si no los tienes, puedes descargarlos e instalarlos desde los siguientes enlaces:

- [Java 17](https://adoptopenjdk.net/)
- [Maven](https://maven.apache.org/download.cgi)

Para comenzar, sigue estos pasos:

### 1. Clona este repositorio

```bash
git clone https://github.com/jlemonn1/juvicaBack
cd juvica-backend
```

### 2. Ejecuta la aplicación

Una vez que hayas clonado el repositorio y configurado tu entorno, puedes iniciar la aplicación con el siguiente comando:

```bash
mvn spring-boot:run
```
Esto iniciará el servidor en [http://localhost:8080](http://localhost:8080).

¡Listo para usar! 🎉 Ahora puedes interactuar con la API usando herramientas como Postman o tu frontend.

## 🛠️ Configuración

La configuración de la base de datos y otras propiedades del backend se puede modificar en el archivo `application.properties`.

## 🔐 Seguridad

La API está configurada para permitir el acceso desde cualquier origen (`@CrossOrigin(origins = "*")`), pero puedes personalizar esto según tus necesidades de seguridad.

## 📂 Estructura del Proyecto

```bash
/juvica-backend
  ├── src/
      ├── main/
          ├── java/
              ├── com/
                  ├── example/
                      ├── juvica/
                          ├── controller/  <-- Aquí van los controladores
                          ├── dto/         <-- Aquí van los DTOs
                          ├── entity/       <-- Aquí van las entidades
                          ├── service/      <-- Aquí van los servicios
  ├── pom.xml  <-- Archivo de dependencias de Maven
  ├── application.properties  <-- Configuración de la aplicación

```

## ✨ Características

- Los trabajos pueden tener imágenes y videos, que se pueden subir dinámicamente con los endpoints correspondientes. 🌟
- Cada categoría puede tener su propia imagen, ¡y puedes actualizarla fácilmente! 📸
- Todo está bien organizado usando DTOs para el intercambio de datos entre el backend y el frontend.
