package birdcategories;

import java.io.Serializable;

public class BirdCategoriesDTO implements Serializable{
    private int categoriesID;
    private String categoriesName;
    
    public BirdCategoriesDTO(){
    }

    public BirdCategoriesDTO(int categoriesID, String categoriesName) {
        this.categoriesID = categoriesID;
        this.categoriesName = categoriesName;
    }

    public int getCategoriesID() {
        return categoriesID;
    }

    public void setCategoriesID(int categoriesID) {
        this.categoriesID = categoriesID;
    }

    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    @Override
    public String toString() {
        return "BirdCategoriesDTO{" + "categoriesID=" + categoriesID + ", categoriesName=" + categoriesName + '}';
    }
    
}