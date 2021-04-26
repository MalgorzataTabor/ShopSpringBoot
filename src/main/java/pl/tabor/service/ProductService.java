package pl.tabor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tabor.Model.Markup;
import pl.tabor.Model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ProductService {

    private List<Product> products;
    private Markup markup;

    @Autowired
    public ProductService(List<Product> products, Markup markup) {
        this.products = products;
        this.markup = markup;
    }


    public BigDecimal getTax() {
     return products.stream().map(Product::getPrice).flatMap()
             .findFirst().get().multiply(markup.getTax())
               .divide(new BigDecimal(100))
               .setScale(2, RoundingMode.HALF_UP);

    }

    /*public BigDecimal getDiscountPrice() {
        return product.getPrice().subtract(product.getPrice().multiply(markup.getDiscount()
        ))
                .divide(new BigDecimal(100))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getDiscountTax() {
        return getDiscountPrice().multiply(markup.getTax())
                .divide(new BigDecimal(100))
                .setScale(2, RoundingMode.HALF_UP);

    }*/
}
