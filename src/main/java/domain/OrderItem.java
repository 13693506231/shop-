package domain;

public class OrderItem {
    private int count;
    private double subtotal;
    private int pid;
    private int oid;


    public OrderItem() {
    }

    public OrderItem(int count, double subtotal, int pid, int oid) {
        this.count = count;
        this.subtotal = subtotal;
        this.pid = pid;
        this.oid = oid;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getCount() {
        return count;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public int getPid() {
        return pid;
    }

    public int getOid() {
        return oid;
    }
}
