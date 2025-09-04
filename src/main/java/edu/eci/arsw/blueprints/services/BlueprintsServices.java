/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class BlueprintsServices {
   
    @Autowired
    BlueprintsPersistence bpp=null;
    
    public BlueprintsServices(BlueprintsPersistence bpp){
        this.bpp=bpp;
    }
    /**
     * This method adds a new blueprint to the system
     * @param bp
     * @throws BlueprintNotFoundException 
     * 
     */
    public Blueprint addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        bpp.saveBlueprint(bp); 
        return bp;
    }

    /**
     * 
     * @return all the blueprints in the system
     * @throws BlueprintPersistenceException if there are no blueprints
     */
    public Set<Blueprint> getAllBlueprints() throws BlueprintPersistenceException {
        if (bpp != null) {
            return bpp.getAllBlueprints();
        }
        throw new BlueprintPersistenceException("There are no Blueprints.");
    }
    
    /**
     * 
     * @param author blueprint's author
     * @param name blueprint's name
     * @return the blueprint of the given name created by the given author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String name) throws BlueprintNotFoundException{
        
        if(author!=null && name!=null) { return bpp.getBlueprint(author, name); }
        
        throw new BlueprintNotFoundException("The Blueprint made by " + author + " with name " + name + " does not exist.");
    }

    
    /**
     * 
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
        
        if(author!=null) { bpp.getBlueprint(author,null); }
        
        throw new BlueprintNotFoundException("The Blueprint made by " + author + " does not exist."); 
    }

    /**
     * 
     * @param name blueprint's name
     * @return all the blueprints with the given name
     * @throws BlueprintNotFoundException if the given name doesn't exist
     */
    public Set<Blueprint> getBlueprintsByName(String name) throws BlueprintNotFoundException{
        
        if(name!=null) { bpp.getBlueprint(null,name);}
        
        throw new BlueprintNotFoundException("The Blueprint " + name + " does not exist."); 
    }

}
