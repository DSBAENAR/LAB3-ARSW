package edu.eci.arsw.blueprints;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

@SpringBootApplication
public class AppCoreApplication {

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
