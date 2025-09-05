
package edu.eci.arsw.blueprints.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Blueprint {

    private String author=null;
    
    private List<Point> points=null;
    
    private String name=null;
            
    public Blueprint(String author,String name,Point[] pnts){
        this.author=author;
        this.name=name;
        points=Arrays.asList(pnts);
    }
         
    public Blueprint(String author, String name){
        this.name=name;
        this.author=author;
        points=new ArrayList<>();
    }

    public Blueprint() {
    }    
    
    /** 
     * @return String
     */
    public String getName() {
        return name;
    }

    /** 
     * @return String
     */
    public String getAuthor() {
        return author;
    }
    
    /** 
     * @return List<Point>
     */
    public List<Point> getPoints() {
        return points;
    }
    
    /** 
     * @param p
     */
    public void addPoint(Point p){
        this.points.add(p);
    }

    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Blueprint{" + "author=" + author + ", name=" + name + '}';
    }

    /** 
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    /** 
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Blueprint other = (Blueprint) obj;
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (Objects.equals(points, other.points)) {
            return true;
        }
        
        for (int i=0;i<this.points.size();i++){
            if (this.points.get(i)!=other.points.get(i)){
                return false;
            }
        }
        
        return true;
    }
    
    
    
}
