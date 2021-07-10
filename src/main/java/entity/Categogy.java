package entity;

public class Categogy {
    private int idCate;
    private String name;

    public Categogy() {
    }

    public Categogy(int idCate, String name) {
        this.idCate = idCate;
        this.name = name;
    }

    public int getIdCate() {
        return idCate;
    }

    public void setIdCate(int idCate) {
        this.idCate = idCate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
