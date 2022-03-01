# Java: Servidor CRUD Springboot Hibernate
# Trabajo en progreso.. creacion y manejo de dependencias

<p align="center">
<img src="https://www.sofka.com.co/wp-content/uploads/2021/02/sofkau-logo-horizontal.png">
</p>
<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white">
</p>
<p align="center">
  <img src="https://img.shields.io/github/v/release/JoseNSoler/PracticaMVC?style=flat-square"
</p>   

## Descripcion General

Servidor CRUD implementado en Java - Springboot - hibernate
- Para servidores Linux, el codigo se ejecuta desde el archivo `mvnw`
- Para servidores con distrubuciones Windows, el codigo se ejecuta desde el archivo `mvnw.cmd` desde una consola de comandos (cmd) o ventana PowerShell atravez del comando "./mvnw.cmd spring-boot:run"


Para el desarrollo (y posible recomendacion para implementacion rapida), se configuro un servidor MySql-Apache-PHPMyAdmin usando [USBWebServer](https://www.usbwebserver.net/downloads.html) :

`http://localhost/phpmyadmin/` : PHPMyAdmin

`http://localhost:3306/springboot` : MySql enlace y puertos *La base de datos por defecto se llama springboot*

`http://localhost:8080/{*operaciones*}` : servicio Java Springboot API CRUD, mas abajo se detallan los metodos permitidos

## Manejo de Errores

Si el usuario realiza una peticion no permitida, ya sea por un error en el tipo de datos enviados, o requiriendo informacion no existente en la DB, el servidor respondera con un JSON:

```JSON
{
    "message":  "__ERROR usuario con ID:20 no identificado en DB",
    "httpStatus":  "NOT_FOUND",
    "timeStamp":  "2022-02-21T04:27:37.423514Z"
}
```
Describiendo:
- El error en concreto que el usuario esta cometiendo
- HTTP status personalizado de acuerdo al tipo de problema
- Hora de respuesta del servidor

<hr>

## Operaciones permitidas
