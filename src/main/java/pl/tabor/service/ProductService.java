package pl.tabor.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tabor.Model.Basket;
import pl.tabor.Model.Markup;
import pl.tabor.Model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


@Service
public class ProductService {

    private List<Product> products;
    private Markup markup;
    private Basket basket;

    public ProductService(List<Product> products, Markup markup, Basket basket) {
        this.products = products;
        this.markup = markup;
        this.basket = basket;
    }

    @Autowired


    public BigDecimal getTax() {
        return products.stream().map(Product::getPrice).iterator().next()
                .multiply(markup.getTax())
                .divide(new BigDecimal(100))
                .setScale(2, RoundingMode.HALF_UP);


    }


    public BigDecimal getDiscount() {
        return markup.getDiscount();
    }


    public BigDecimal getDiscountPrice() {
        BigDecimal price = products.stream().map(Product::getPrice).iterator().next();
        BigDecimal discount = products.stream().map(Product::getPrice).iterator().next().multiply(markup.getDiscount())
                .divide(new BigDecimal(100))
                .setScale(2, RoundingMode.HALF_UP);

        return price.subtract(discount);

    }


    public BigDecimal getDiscountTax() {
        return getDiscountPrice().multiply(markup.getTax())
                .divide(new BigDecimal(100))
                .setScale(2, RoundingMode.HALF_UP);

    }
}

