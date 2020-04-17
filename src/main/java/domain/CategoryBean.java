package domain;

public class CategoryBean {
    int id;
    String cname;

    public CategoryBean() {
    }

    public CategoryBean(int id, String cname) {
        this.id = id;
        this.cname = cname;
    }

    public int getId() {
        return id;
    }

    public String getCname() {
        return cname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
