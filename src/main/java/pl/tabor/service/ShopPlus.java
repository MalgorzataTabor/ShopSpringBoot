
package pl.tabor.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.tabor.Model.Basket;
import pl.tabor.Model.Markup;

import java.math.BigDecimal;


@Service
@Profile("plus")
public class ShopPlus implements ContentsOfBasket {
    @Value("${VAT}")
    private BigDecimal VAT;
    private final MessageService messageService;
    private final Basket basket;

    public ShopPlus(MessageService messageService, Basket basket) {
        this.messageService = messageService;
        this.basket = basket;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void showBasket() {
        final String REGEX = "%-20s %7s %n";
        System.out.printf(REGEX,
                messageService.getTextProductName(),
                messageService.getTextPrice(),
                messageService.getTextTax(),
                messageService.getTextTogether());

        basket.getProducts().forEach(product ->
                System.out.printf(REGEX,
                        product.getProductName(),
                        product.getPrice(),
                        product.getTax(),
                        product.getPrice().add(product.getTax())));

        System.out.printf(REGEX,
                messageService.getTextSum(),
                basket.getPriceSum(),
                basket.getTaxSum(),
                basket.getPriceSum().add(basket.getTaxSum()));


    }

}

