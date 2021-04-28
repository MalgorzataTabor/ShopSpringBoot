package pl.tabor.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tabor.Model.Markup;
import pl.tabor.Model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static java.math.RoundingMode.HALF_UP;


@Service
public class ProductService {

    private final List<Product> products;
    private final Markup markup;
    private final Basket basket;


    @Autowired
    public ProductService(List<Product> products, Markup markup, Basket basket) {
        this.products = products;
        this.markup = markup;
        this.basket = basket;
    }

    public BigDecimal getPriceWithTax(Product product) {
        return product.getPrice().
                multiply(markup.getTax())
                .divide(BigDecimal.valueOf(100))
                .setScale(2, RoundingMode.HALF_UP);
    }


    public BigDecimal getDiscount() {
        return markup.getDiscount();
    }


    public BigDecimal getPriceWithDiscount(Product product) {
        return product.getPrice()
                .subtract(product.getPrice()
                        .multiply(markup.getDiscount())
                        .divide(BigDecimal.valueOf(100)))
                .setScale(2, HALF_UP);
    }

    public BigDecimal getPriceWithDiscountAndTax(Product product) {
        return getPriceWithDiscount(product).multiply(markup.getTax())
                .divide(BigDecimal.valueOf(100))
                .setScale(2, HALF_UP);

    }
}

