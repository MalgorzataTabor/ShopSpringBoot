package pl.tabor.Model;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Data
public class Basket {

    private final List<Product> products;
    private final Markup markup;

    @Autowired
    public Basket(List<Product> products, Markup markup) {
        this.products = products;
        this.markup = markup;
    }

    public BigDecimal getPriceSum() {


        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTaxSum() {

        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal::add).get().multiply(markup.getTax())
                .setScale(2, RoundingMode.HALF_UP);



    }

    /*public BigDecimal getDiscountSum() {
        return products.stream()
                .map(Product::getDiscountPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getDiscountAndTaxSum() {
        return products.stream()
                .map(Product::getDiscountTax)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }*/


}
