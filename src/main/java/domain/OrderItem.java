package domain;

public class OrderItem {
    private int count;
    private double subtotal;
    private int pid;
    private String oid;
    private Goods goods;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public OrderItem() {
    }

    public OrderItem(int count, double subtotal, int pid, String oid) {
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

    public void setOid(String oid) {
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

    public String getOid() {
        return oid;
    }
}
