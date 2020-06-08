---


---

<h1 id="java-sockets">Java Sockets</h1>
<p>Este repositorio es un conjunto de actividades de laboratorio y practicas para Aplicaciones para Comunicaciones de Red con la profesora Sandra Ivette Bautista Rosales, en el grupo 3CV7.<br>
El proyecto fue realizado por Enya Quetzalli Gómez Rodríguez (Eduardo Gómez Rodríguez).</p>
<h2 id="descripción">Descripción</h2>
<p>La actividad L1, pide realizar 2 tareas, las cuales realicé utilizando sockets de java con el protocolo TCP en Kotlin.</p>
<ul>
<li>Mandar un mensaje de texto de un servidor a un cliente y el código fuente se encuentra <a href="https://github.com/equetzal/javaSockets/tree/master/mensaje%20de%20texto">aquí</a></li>
<li>Enviar un archivo cualquiera de un cliente a un servidor, el codigo fuente se encuentra <a href="https://github.com/equetzal/javaSockets/tree/master/envio%20de%20archivos">aquí</a></li>
</ul>
<p>La actividad L2, pide realizar 1 tarea1, la cual realicé utilizando sockets de java con el protocolo TCP en Kotlin.</p>
<ul>
<li>Enviar un objeto serializado del cliente al servidor, y deserializarlo en el servidor para motrar su contenido. El codigo fuente se encuentra <a href="https://github.com/equetzal/javaSockets/tree/master/envio%20de%20objetos">aquí</a></li>
</ul>
<h2 id="tecnologías-utilizadas">Tecnologías Utilizadas</h2>
<dl>
<dt>Servidor</dt>
<dd>Utilicé Kotlin como el lenguaje de programación para la Java Virtual Machine. El proyecto está en el IDE IntelliJ IDEA CE.<br>
El servidor se corrió en Windows 10, por lo que la ruta de escritura de los archivos recibidos es para Windows y no para Linux.</dd>
<dt>Cliente</dt>
<dd>Utilicé Kotlin como el lenguaje de programación para la Java Virtual Machine, con él cree una aplicación móvil para Android, utilizando el IDE Android Studio, donde el cliente se encarga de ejecutarse en un proceso asíncrono que envía los archivos seleccionados desde el menú contextual de Android o el pasado en su respectivo parámetro motivo por el cuál el cliente tiene mayor trabajo más allá del socket.<br>
Además, la aplicación está orientada para Android 9.0 ya que la gestión de archivos es variante con respecto a cada versión por lo que sólo era optimo hacer que su funcionamiento no fuera compatible con múltiples versiones del sistema operativo.</dd>
</dl>
<h2 id="pruebas">Pruebas</h2>
<p>Para las pruebas utilicé:</p>
<dl>
<dt>L1 Parte 1</dt>
<dd>Envié desde el servidor “Saludos Quetzallianos”</dd>
<dt>L2 Parte 2</dt>
<dd>Envié 4 archivos<br>
– Una una foto de mi perrito Tux<br>
– Una foto de Kaskade &lt;3<br>
– Un PDF de una ley de puebla<br>
– Un audio m4a de un Mix de Kaskade en 2017<br>
Todos los archivos fueron incluidos en la carpeta de cada parte.</dd>
<dt>L2</dt>
<dd>Utilicé un archivo .json para mandar mi GustoQuetzalliano.</dd>
</dl>

