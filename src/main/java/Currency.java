public class Currency {
    private String codeName;

    private double price;

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public Currency(String codename,double price) {
        this.codeName = codename;
        this.price = price;
    }
}
