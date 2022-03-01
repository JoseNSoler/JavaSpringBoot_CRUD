# Java: Servidor CRUD Springboot Hibernate

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

## Estrcutura y modelado

Para los metodos Role y Project, estan implementados CRUD por defecto, si se cambia la informacion de alguna tabla, este cambio se reflejara en las demas tablas asociadas a la misma

La estructura JSON asociada los objetos son
*Employee = Empleado*
```JSON
{   
    "employeeId": "WORK123456",
    "firstName": "Alvarado",
    "lastName": "Hernadez",
    "role" : {
	    "name":"ADMIN"
    },
    "projects": [
        {
	    "name":"final cambiado"
    }, {
	    "name":"Projecto abecedario"
    } 
    ]
}
```

*Role = Rol usuario*
```JSON
{
	"name":"ADMIN"
}
```

*Project = Project*
```JSON
{
	"name":"Proyecto Empresarial 1"
}
```

## Operaciones permitidas
El servidor manejara tres controladores distintos, accedidos por las direcciones:
`http://localhost:8080/employee`
`http://localhost:8080/role`
`http://localhost:8080/projec`

### GET
`http://localhost:8080/employee` :
Responde en formato JSON con todos los usuarios presentes en la DB.

`http://localhost:8080/role/{id}` *ID refiere a la identificacion unica del usuario*:
Responde en formato JSON con el usuario con el ID requerido

`http://localhost:8080/project/{id}`:
Responde en formato JSON con los usuarios que tengan el atributo y valor solicitados

<hr>

### POST
`http://localhost:8080/employee` :
*BODY enviado en formato JSON*
```JSON
{   
    "employeeId": "WORK123456",
    "firstName": "Alvarado",
    "lastName": "Hernadez",
    "role" : {
	    "name":"ADMIN"
    },
    "projects": [
        {
	    "name":"Proyecto Empresarial 1"
    }, {
	    "name":"Implementacion final"
    } 
    ]
}

```
Creacion de  projecto  en base a body peticion en formato JSON, con la informacion del usuario deseado a crear en DB. El servidor respondera en el mismo formato JSON con la informacion del usuairo creado. __Id autogenerado al crear los usuarios__
*En caso de error en algun parametro (tipo de dato, nombre invalido atributo) el servidor devolvera un error y no creara el proyecto*

<hr>

### PUT
`http://localhost:8080/project/{id}` *ID refiere a la identificacion unica del proyecto*:
*BODY enviado en formato JSON*

Modificacion de  proyecto por medio de su ID, y requiriendo un Body del usuari    en formato JSON con la nueva informacion.
*En caso de enviar un ID no encontrado en la DB, enviara un JSON error respuesta.
En caso de error en algun parametro (tipo de dato, nombre invalido atributo) el servidor devolvera un error y no creara el proyecto*

<hr>

### DELETE
`http://localhost:8080/project/{id}` *ID refiere a la identificacion unica del rpojecto*:
Peticion delete para eliminar el projecto con ID requerido de la base de datos
*En caso de enviar un ID no encontrado en la DB, enviara un JSON error respuesta.*
<hr>