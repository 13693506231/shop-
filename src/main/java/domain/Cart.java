package domain;

import constants.Common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Description : 购物车总
 * @param：
 * @return String：
 * @Author: nkk
 * @Create Date: 2008-07-02
 */
public class Cart {
    private List<CartItem> items;
    private  Double item_total;

    public Cart(List<CartItem> items, Double item_total) {
        this.items = items;
        this.item_total = item_total;
    }

    public Cart() {
    }

    public void setItem(List<CartItem> items) {
        this.items = items;
    }


    public List<CartItem> getItem() {
        return items;
    }

    public Double getItem_total() {
        Double total = 0.00;
        for (CartItem item : items) {
            total += item.getItem_total();
        }
        return total;
    }
    public void addItem(CartItem cartitem){
        if(items == null){
            List<CartItem> items = new ArrayList<>();
            items.add(cartitem);
            setItem(items);
        }else{
            int ishas = 0;
            for (CartItem item : items) {
                int id = item.getGoods().getId();
                int itemid = cartitem.getGoods().getId();
                 if(id == itemid){
                    ishas = 1;
                    item.setGoods_num(item.getGoods_num() + cartitem.getGoods_num());
                 }
            }
            if(ishas == 0){
                items.add(cartitem);
            }
        }
    }
    public void delItem(String pid){
        int intpid = Integer.parseInt(pid);
        Iterator<CartItem> iterator = items.iterator();
        while (iterator.hasNext()){
            CartItem item = iterator.next();
            int id = item.getGoods().getId();
            if(id == intpid){
                iterator.remove();
            }
        }
    }
}
