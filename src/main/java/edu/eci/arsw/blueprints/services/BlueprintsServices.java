/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.LinkedHashMap;
import java.util.Map;
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
     * 
     */
    public void addNewBlueprint(Blueprint bp) {
        try{
            if (bpp != null) {
                bpp.saveBlueprint(bp);
                }
        } catch (BlueprintPersistenceException ex) {
            ex.printStackTrace();    
        }
    }
    
    public Set<Blueprint> getAllBlueprints(){
        return null;
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
        
        throw new UnsupportedOperationException("The Blueprint made by " + author + " does not exist."); 
    }
    
}
