# SistemaFichajes


# Desarrollo
Cuando se inicia la aplicación, se ejecuta automáticamente docker compose up y la app se conecta a los servicios definidos en Docker. Docker debe estar disponible en el sistema actual.

Durante el desarrollo, se recomienda usar el perfil local.
En IntelliJ puedes añadir -Dspring.profiles.active=local en las opciones de la máquina virtual (VM options) dentro de la configuración de ejecución ("Run Configuration") tras habilitar esta opción en "Modify options".

Crea tu propio archivo application-local.yml para sobrescribir configuraciones específicas del entorno de desarrollo.

Lombok debe estar soportado por tu IDE.
En IntelliJ, instala el plugin de Lombok y activa el procesamiento de anotaciones (annotation processing)

Frontend (Angular)
Además de la aplicación Spring Boot, también se debe iniciar el servidor de desarrollo del frontend.
Para esto, se requiere Node.js versión 22.

Instala Angular CLI y las dependencias necesarias (esto solo una vez):
```
npm install -g @angular/cli
npm install
```

Luego, puedes iniciar el servidor de desarrollo con:

```
ng serve
```

La aplicación ahora estará disponible en http://localhost:4200

Add code using Angular schematics with `ng generate ...`.
Frontend unit tests can be executed with `ng test`.
Generate a messages.json for translation with `ng extract-i18n --format=json`.

## Construcción del proyecto (Build)
Puedes compilar la aplicación con el siguiente comando:

```
mvnw clean package
```

Para ejecutarla en producción:

```
java -Dspring.profiles.active=production -jar ./target/sistemaFichajes-0.0.1-SNAPSHOT.jar
```

Si lo necesitas, también puedes crear una imagen Docker usando el plugin de Spring Boot.
Agrega la variable de entorno SPRING_PROFILES_ACTIVE=production al ejecutar el contenedor.

```
mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=coredev/sistema-fichajes
```

## Lecturas adicionales:

* [Maven docs](https://maven.apache.org/guides/index.html)  
* [Spring Boot reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)  
* [Spring Data JPA reference](https://docs.spring.io/spring-data/jpa/reference/jpa.html)
* [Learn Angular](https://angular.dev/tutorials/learn-angular)  
* [Angular CLI](https://angular.dev/tools/cli)
* [Tailwind CSS](https://tailwindcss.com/)  
