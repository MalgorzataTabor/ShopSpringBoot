package pl.tabor.Model;



import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private String productName;
    private BigDecimal price;


    public Product(String productName, BigDecimal price) {
        this.productName = productName;
        this.price = price;
    }

 /*public BigDecimal getTax() {
        return price.multiply(getMarkup().getTax())
                .divide(new BigDecimal(100))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getDiscountPrice() {
        return price.subtract(price.multiply(getMarkup().getDiscount()
        ))
                .divide(new BigDecimal(100))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getDiscountTax() {
        return getDiscountPrice().multiply(getMarkup().getTax())
                .divide(new BigDecimal(100))
                .setScale(2, RoundingMode.HALF_UP);
    }*/
}






