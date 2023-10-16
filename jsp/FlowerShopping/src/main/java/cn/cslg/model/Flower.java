package cn.cslg.model;

public class Flower {
    private Integer fid;//鲜花编号
    private String name;//名称
    private double price;//价格
    private String info;//花语
    private String pic;//图片
    private Integer stock;//库存

    public Flower() {
    }

    public Flower(Integer fid, String name, double price, String info, String pic, Integer stock) {
        this.fid = fid;
        this.name = name;
        this.price = price;
        this.info = info;
        this.pic = pic;
        this.stock = stock;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "fid=" + fid +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", info='" + info + '\'' +
                ", pic='" + pic + '\'' +
                ", stock=" + stock +
                '}';
    }
}
