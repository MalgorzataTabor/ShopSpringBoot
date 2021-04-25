package pl.tabor.Model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Data
public class Basket {


    private final List<Product> products;

    public Basket(List<Product> products) {
        this.products = products;
    }

    public BigDecimal getPriceSum(){

        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTaxSum(){
        return products.stream()
                .map(Product::getTax)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getDiscountSum(){
        return products.stream()
                .map(Product::getDiscountPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getDiscountAndTaxSum(){
        return products.stream()
                .map(Product::getDiscountTax)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
