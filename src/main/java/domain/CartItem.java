package domain;
/**
 * Description :
 * @param：
 * @return String：
 * @Author: nkk
 * @Create Date: 2008-07-02
 */
public class CartItem {
    private  Goods goods;
    private  int goods_num;

    public CartItem(Goods goods,int goods_num) {
        this.goods = goods;
        this.goods_num = goods_num;
     }

    public CartItem() {
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public void setGoods_num(int goods_num) {
        this.goods_num = goods_num;
    }



    public Goods getGoods() {
        return goods;
    }

    public Double getItem_total() {
        return  goods.getShop_price() * goods_num;

    }

    public int getGoods_num() {
        return goods_num;
    }


}
