package moa.mymoney.hmapping;
// Generated 22 oct. 2012 15:42:07 by Hibernate Tools 3.2.1.GA



/**
 * Subcategory generated by hbm2java
 */
public class Subcategory  implements java.io.Serializable {


     private int id;
     private String name;
     private int category;

    public Subcategory() {
    }

    public Subcategory(int id, String name, int category) {
       this.id = id;
       this.name = name;
       this.category = category;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public int getCategory() {
        return this.category;
    }
    
    public void setCategory(int category) {
        this.category = category;
    }




}


