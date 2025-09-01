## Escuela Colombiana de Ingeniería

## Arquitecturas de Software

# Componentes y conectores - Parte I.

El ejercicio se debe traer terminado para el siguiente laboratorio (Parte II).

#### Middleware- gestión de planos.


## Antes de hacer este ejercicio, realice [el ejercicio introductorio al manejo de Spring y la configuración basada en anotaciones](https://github.com/ARSW-ECI/Spring_LightweightCont_Annotation-DI_Example).

En este ejercicio se va a construír un modelo de clases para la capa lógica de una aplicación que permita gestionar planos arquitectónicos de una prestigiosa compañia de diseño. 

![](img/ClassDiagram1.png)

1. Configure la aplicación para que funcione bajo un esquema de inyección de dependencias, tal como se muestra en el diagrama anterior.


	Lo anterior requiere:

	* Agregar las dependencias de Spring. <br>
	Para este proyecto hice una actualización pasando las dependencias a SpringBoot
	```pom
	 <?xml version="1.0" encoding="UTF-8"?>
	<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	    <modelVersion>4.0.0</modelVersion>

    <groupId>edu.eci.pdsw.examples</groupId>
    <artifactId>blueprints-middleware</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>jar</packaging>

    <name>Blueprints_Middleware</name>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.3.3</version>
        <relativePath/>
    </parent>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>17</java.version>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.13.4</version>
            <scope>test</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

	</project>

 	```
	* Agregar la configuración de Spring.

	Y creé el método main dentro de la clase creada BlueprintApplication
	```java
	 package edu.eci.arsw.blueprints;
	
	import org.springframework.boot.SpringApplication;
	import org.springframework.boot.autoconfigure.SpringBootApplication;
	
	import edu.eci.arsw.blueprints.model.Blueprint;
	import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
	import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
	import edu.eci.arsw.blueprints.services.BlueprintsServices;
	
	@SpringBootApplication
	public class BlueprintApplication {

	public static void main(String[] args) {
		Blueprint bp = new Blueprint("dsbaenar","blueprint1");
		InMemoryBlueprintPersistence ibp = new InMemoryBlueprintPersistence();
		BlueprintsServices bps = new BlueprintsServices(ibp);
		bps.addNewBlueprint(bp);
		try {
			System.out.println(bps.getBlueprint("dsbaenar", "blueprint1"));
		} catch (BlueprintNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	}

 	```
	* Configurar la aplicación -mediante anotaciones- para que el esquema de persistencia sea inyectado al momento de ser creado el bean 'BlueprintServices'.

	```java
	 @Service
	public class BlueprintsServices {
   
    @Autowired
    BlueprintsPersistence bpp=null;
 	}
    
 	```


3. Complete los operaciones getBluePrint() y getBlueprintsByAuthor(). Implemente todo lo requerido de las capas inferiores (por ahora, el esquema de persistencia disponible 'InMemoryBlueprintPersistence') agregando las pruebas correspondientes en 'InMemoryPersistenceTest'.

   ```java
   public Blueprint getBlueprint(String author,String name) throws BlueprintNotFoundException{
        
        if(bpp!=null && author!=null && name!=null) { bpp.getBlueprint(author, name); }
        
        throw new UnsupportedOperationException("Not supported yet."); 
    }

   public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
        
        if(bpp!=null && author!=null) { bpp.getBlueprint(author,null); }
        
        throw new UnsupportedOperationException("Not supported yet."); 
    }
   ```

4. Haga un programa en el que cree (mediante Spring) una instancia de BlueprintServices, y rectifique la funcionalidad del mismo: registrar planos, consultar planos, registrar planos específicos, etc.

5. Se quiere que las operaciones de consulta de planos realicen un proceso de filtrado, antes de retornar los planos consultados. Dichos filtros lo que buscan es reducir el tamaño de los planos, removiendo datos redundantes o simplemente submuestrando, antes de retornarlos. Ajuste la aplicación (agregando las abstracciones e implementaciones que considere) para que a la clase BlueprintServices se le inyecte uno de dos posibles 'filtros' (o eventuales futuros filtros). No se contempla el uso de más de uno a la vez:
	* (A) Filtrado de redundancias: suprime del plano los puntos consecutivos que sean repetidos.
	* (B) Filtrado de submuestreo: suprime 1 de cada 2 puntos del plano, de manera intercalada.

6. Agrege las pruebas correspondientes a cada uno de estos filtros, y pruebe su funcionamiento en el programa de prueba, comprobando que sólo cambiando la posición de las anotaciones -sin cambiar nada más-, el programa retorne los planos filtrados de la manera (A) o de la manera (B). 
