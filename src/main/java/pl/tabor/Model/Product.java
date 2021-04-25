package pl.tabor.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.math.RoundingMode;


@Data
@AllArgsConstructor

public class Product {
    private final String productName;
    private final BigDecimal price;
    private  Markup markup;


    public Product(String productName, BigDecimal price) {
        this.productName = productName;
        this.price = price;
         markup= markup;
    }

    public BigDecimal getTax() {
        return price.multiply(getMarkup().getVAT())
                .divide(new BigDecimal(100))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getDiscountPrice() {
        return price.subtract(price.multiply(getMarkup().getDiscount()))
                .divide(new BigDecimal(100))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getDiscountTax() {
        return getDiscountPrice().multiply(getMarkup().getVAT())
                .divide(new BigDecimal(100))
                .setScale(2, RoundingMode.HALF_UP);
    }





}
