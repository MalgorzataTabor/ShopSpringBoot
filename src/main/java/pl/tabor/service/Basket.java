package pl.tabor.service;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tabor.Model.Markup;
import pl.tabor.Model.Product;

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
                .divide(BigDecimal.valueOf(100))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getDiscount() {
        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal::add).get().multiply(markup.getDiscount()).divide(new BigDecimal(100))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getDiscountSum() {
        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal::add).get().subtract(getDiscount());
    }


    public BigDecimal getDiscountAndTaxSum() {
        return getDiscountSum().multiply(getMarkup().getTax())
                .divide(BigDecimal.valueOf(100))
                .setScale(2, RoundingMode.HALF_UP);
    }

}
