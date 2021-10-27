# Proyecto Videojuegos Thymeleaf
Proyecto web CRUD de Videojuegos realizado con Springboot, Java 11, MySQL y Thymeleaf. El mismo se desarrolló para la cátedra Taller de Programación Avanzada, UTN-FRM-
# A tener en cuenta
Para ejecutar el proyecto se deben agregar las configuraciones correctas de la base de datos en el archivo src/main/resources/application.properties Además tener en cuenta que existe un archivo con un dump de la base de datos en caso de que se quieran importar algunos datos.
Luego de instalar todas las dependencias con Maven se debe iniciar el proyecto desde la clsae src/main/java/com/tpa/videogames/VideogamesApplication.java y acceder a http://localhost:9000/ desde el navegador web.
El proyecto está realizado en base a la Playlist https://www.youtube.com/playlist?list=PLRFOqDrY-6nu_k7XrUgIgd5SA3Vg7TiWe del canal ColmenaTec. Se le realizaron algunas mejoras que permitieron ampliar el CRUD a categorias y estidos. Además se utilizaron paths relativos para el almacenaje de imágenes estáticas.
