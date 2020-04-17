package domain;

import java.util.Date;
import java.util.List;

public class Orders {
    private String oid;
    private Date ordertime;
    private double total;
    private int state;
    private String address;
    private String name;
    private String telephone;
    private int uid;
    private List<OrderItem>   orderItem;



    public void setOrderItem(List<OrderItem>  orderItem) {
        this.orderItem = orderItem;
    }

    public List<OrderItem>  getOrderItem() {
        return orderItem;
    }

    public Orders(String oid, Date ordertime, double total, int state, String address, String name, String telephone, int uid) {
        this.oid = oid;
        this.ordertime = ordertime;
        this.total = total;
        this.state = state;
        this.address = address;
        this.name = name;
        this.telephone = telephone;
        this.uid = uid;
    }

    public Orders() {
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getOid() {
        return oid;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public double getTotal() {
        return total;
    }

    public int getState() {
        return state;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getTelephone() {
        return telephone;
    }

    public int getUid() {
        return uid;
    }
}