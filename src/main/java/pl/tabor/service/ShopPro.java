package pl.tabor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import pl.tabor.Model.Basket;
import pl.tabor.Model.Markup;
import pl.tabor.Model.Product;

import java.math.BigDecimal;
import java.util.List;

@Service
@Profile("pro")
public class ShopPro implements ContentsOfBasket {


    private final MessageService messageService;
    private final Basket basket;
    private final Markup markup;
    private final ProductService productService;


    public ShopPro(MessageService messageService, Basket basket, Markup markup, ProductService productService) {
        this.messageService = messageService;
        this.basket = basket;
        this.markup = markup;
        this.productService = productService;
    }

    @Autowired


    @EventListener(ApplicationReadyEvent.class)
    public void showBasket() {
        final String REGEX = "%-15s %10s %10s %10s %10s %12s %10s %10s %n";
        System.out.printf(REGEX,
                messageService.getTextProductName(),
                messageService.getTextPrice(),
                messageService.getTextTax(),
                messageService.getTextTogether(),
                messageService.getTextDiscount(),
                messageService.getTextPrice(),
                messageService.getTextTax(),
                messageService.getTextTogether());

        basket.getProducts().forEach(product ->
                System.out.printf(REGEX,
                        product.getProductName(),
                        product.getPrice(),
                        productService.getTax(),
                        product.getPrice().add(productService.getTax()),
                        productService.getDiscount() + "%",
                        productService.getDiscountPrice(),
                        productService.getDiscountTax(),
                        productService.getDiscountPrice().add(productService.getDiscountTax())));

        System.out.printf(REGEX,
                messageService.getTextSum(),
                basket.getPriceSum(),
                basket.getTaxSum(),
                basket.getPriceSum().add(basket.getTaxSum()),
                "-",
                basket.getDiscountSum(),
                basket.getDiscountAndTaxSum(),
                basket.getDiscountSum().add(basket.getDiscountAndTaxSum()));


    }


    /*public BigDecimal getTotalPriceWithRabat() {
        BigDecimal grossTotalPrice = getGrossTotalPrice();
        BigDecimal discount = getMarkup().getDiscount().divide(new BigDecimal(100));
        BigDecimal totalPriceWithRabat = getGrossTotalPrice().subtract(grossTotalPrice.multiply(discount));
        return totalPriceWithRabat;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void printGrossNetAndRabatTotalPrice() {
        System.out.println("Łączna cena za zakupy 'ProShop' : " + getBasicBasketPrice().doubleValue() + " zł(Netto), "
                + getGrossTotalPrice().doubleValue() + " zł(Brutto), " + getTotalPriceWithRabat().doubleValue() + " zł(Po Rabacie: " + getMarkup().getVAT() + "%)");
    }*/

}

