# LiterAlura
LiterAlura es una aplicación que te permite buscar libros, ver información detallada sobre ellos y gestionar autores. Utiliza una API externa para obtener datos sobre los libros y los presenta de manera organizada para que los usuarios puedan explorarlos fácilmente.

Requisitos
Para ejecutar esta aplicación, necesitarás tener instalado:

    Java Development Kit (JDK) 8 o superior
    Maven para la gestión de dependencias (opcional si se usan otras herramientas de construcción)

Instalación

    Clona o descarga el repositorio en tu máquina local.
    Abre el proyecto en tu IDE preferido.
    Asegúrate de tener las dependencias necesarias instaladas (puedes usar Maven para gestionarlas).
    Y asegúrate de modificar las variables de entorno o en su defecto, modificar lo siguiente:

    spring.datasource.url=jdbc:postgresql://${direccionDB}/${DB nombre}
    spring.datasource.username="user de la DB"
    spring.datasource.password=${password de la DB}

    Uso

Para utilizar la aplicación, sigue estos pasos:

    Ejecuta la clase LiterAluraApplication desde tu IDE o mediante la línea de comandos.
    Selecciona una opción del menú principal:
        Buscar libro por título: Busca un libro específico por su título.
        Listar libros registrados: Muestra todos los libros que han sido registrados en la base de datos.
        Listar autores registrados: Muestra todos los autores que han sido registrados en la base de datos.
        Listar autores vivos en un determinado año: Busca autores que estuvieran vivos en un año específico.
        Listar libros por idioma: Muestra todos los libros disponibles en un idioma específico.
        Salir: Cierra la aplicación.
    Sigue las instrucciones en pantalla para interactuar con la aplicación.

Contribuir

Si deseas contribuir a este proyecto, ¡eres bienvenido! Siéntete libre de enviar pull requests con mejoras o correcciones.
Licencia

Este proyecto está bajo la licencia MIT.
