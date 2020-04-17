package domain;

public class Goods {
    private int id ;
    private String pname ;
    private Double market_price ;
    private Double shop_price ;
    private String  pimage ;
    private String pdate ;
    private int is_hot ;
    private String  pdesc ;
    private int pflag ;
    private int cid ;

    public Goods(int id, String pname, Double market_price, Double shop_price, String pimage, String pdate, int is_hot, String pdesc, int pflag, int cid) {
        this.id = id;
        this.pname = pname;
        this.market_price = market_price;
        this.shop_price = shop_price;
        this.pimage = pimage;
        this.pdate = pdate;
        this.is_hot = is_hot;
        this.pdesc = pdesc;
        this.pflag = pflag;
        this.cid = cid;
    }

    public Goods() {
    }

    public int getId() {
        return id;
    }

    public String getPname() {
        return pname;
    }

    public Double getMarket_price() {
        return market_price;
    }

    public Double getShop_price() {
        return shop_price;
    }

    public String getPimage() {
        return pimage;
    }

    public String getPdate() {
        return pdate;
    }

    public int getIs_hot() {
        return is_hot;
    }

    public String getPdesc() {
        return pdesc;
    }

    public int getPflag() {
        return pflag;
    }

    public int getCid() {
        return cid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setMarket_price(Double market_price) {
        this.market_price = market_price;
    }

    public void setShop_price(Double shop_price) {
        this.shop_price = shop_price;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    public void setIs_hot(int is_hot) {
        this.is_hot = is_hot;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
    }

    public void setPflag(int pflag) {
        this.pflag = pflag;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

}
