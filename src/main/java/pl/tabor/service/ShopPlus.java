
package pl.tabor.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.tabor.Model.Markup;


@Service
@Profile("plus")

public class ShopPlus implements ContentsOfBasket {

    private final MessageService messageService;
    private final Basket basket;
    private final Markup markup;
    private final ProductService productService;


    @Autowired
    public ShopPlus(MessageService messageService, Basket basket, Markup markup, ProductService productService) {
        this.messageService = messageService;
        this.basket = basket;
        this.markup = markup;
        this.productService = productService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void showBasket() {
        final String REGEX = "%-15s %10s %10s %10s %n";
        System.out.printf(REGEX,
                messageService.getTextProductName(),
                messageService.getTextPrice(),
                messageService.getTextTax(),
                messageService.getTextTogether());

        basket.getProducts().forEach(product ->
                System.out.printf(REGEX,
                        product.getProductName(),
                        product.getPrice(),
                        productService.getPriceWithTax(product),
                        product.getPrice().add(productService.getPriceWithTax(product)
                        )));

        System.out.printf(REGEX,
                messageService.getTextSum(),
                basket.getPriceSum(),
                basket.getTaxSum(),
                basket.getPriceSum().add(basket.getTaxSum()));


    }

}

