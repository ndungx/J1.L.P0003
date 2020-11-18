
/*
 * @author NDungx
 */
public class Category {

    String idCategory;
    String nameCategory;

    public Category() {
        idCategory = "";
        nameCategory = "";
    }

    public Category(String idCategory, String nameCategory) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    @Override
    public String toString() {
        return idCategory.stripLeading() + ", " + nameCategory.stripLeading();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Category) {
            return ((Category) obj).idCategory.equals(idCategory);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return Integer.valueOf(this.idCategory);
    }
}