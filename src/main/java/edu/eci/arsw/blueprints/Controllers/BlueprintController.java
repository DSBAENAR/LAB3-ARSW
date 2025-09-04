package edu.eci.arsw.blueprints.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/blueprints")
public class BlueprintController {

    private final BlueprintsServices bps;

    public BlueprintController(BlueprintsServices bps) {
        this.bps = bps;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllBlueprints() {
        try{
            return ResponseEntity.ok(bps.getAllBlueprints());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/blueprint/author/{author}")
    public ResponseEntity<?> getBlueprintByAuthor(@PathVariable String author){
        try {
            return ResponseEntity.ok(bps.getBlueprintsByAuthor(author));
        } catch (BlueprintNotFoundException e) {
            return ResponseEntity.status(400).body(e.getMessage());
            
        }
    }

    @GetMapping("/blueprint/name/{name}")
    public ResponseEntity<?> getBlueprintByName(@PathVariable String name){
        try {
            return ResponseEntity.ok(bps.getBlueprint(null,name));
        } catch (BlueprintNotFoundException e) {
            return ResponseEntity.status(400).body(e.getMessage());
            
        }
    }


    @PostMapping("/blueprint")
    public ResponseEntity<?> addBlueprint(@RequestBody Blueprint bp){
        try {
            bps.addNewBlueprint(bp);
            return ResponseEntity.status(201).body("The Blueprint made by " + bp.getAuthor() + " with name " + bp.getName() + " has been created.");
        } catch (BlueprintPersistenceException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


    
}
