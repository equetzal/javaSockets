# Java Sockets
Esta es una practica para Aplicaciones para Comunicaciones de Red con la profesora Sandra Ivette Bautista Rosales, en el grupo 3CV7.
El proyecto fue realizado por Enya Quetzalli Gómez Rodríguez (Eduardo Gómez Rodríguez).

## Descripción
El proyecto, pide realizar 2 tareas. 
- Mandar un mensaje de texto de un servidor a un cliente
- Enviar un archivo de un cliente a un servidor

Ambos casos utilizando Sockets en java con el protocolo TCP.

## Tecnologías Utilizadas
Servidor
: Utilicé Kotlin como el lenguaje de programación para la Java Virtual Machine, utilicé la clase "L1_Server" que se corre desde la función main en consola. El proyecto está en el IDE IntelliJ IDEA CE. 
El servidor se corrió en Windows 10, por lo que la ruta de escritura de los archivos recibidos es para Windows y no para Linux.

Cliente
: Utilicé Kotlin como el lenguaje de programación para la Java Virtual Machine, con él cree una aplicación móvil para Android, utilizando el IDE Android Studio, donde la clase "cliente" es la encarga de ejecutarse en un proceso asíncrono que envía los archivos seleccionados desde el menú contextual de Android. Motivo por el cuál el cliente tiene mayor trabajo más allá del socket. 
Además, la aplicación está orientada para Android 9.0 ya que la gestión de archivos es variante con respecto a cada versión por lo que sólo era optimo hacer que su funcionamiento no fuera compatible con múltiples versiones del sistema operativo.
