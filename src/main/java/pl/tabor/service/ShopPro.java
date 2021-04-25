package pl.tabor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
/*
import pl.tabor.Model.Markup;
import pl.tabor.Model.Product;

import java.math.BigDecimal;
import java.util.List;

@Service
@Profile("pro")
public class ShopPro extends ShopPlus {

    @Autowired
    public ShopPro(List<Product> products, MessageService messageService, Markup markup) {
        super(products, messageService, markup);
    }

    public BigDecimal getTotalPriceWithRabat() {
        BigDecimal grossTotalPrice = getGrossTotalPrice();
        BigDecimal discount = getMarkup().getDiscount().divide(new BigDecimal(100));
        BigDecimal totalPriceWithRabat = getGrossTotalPrice().subtract(grossTotalPrice.multiply(discount));
        return totalPriceWithRabat;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void printGrossNetAndRabatTotalPrice() {
        System.out.println("Łączna cena za zakupy 'ProShop' : " + getBasicBasketPrice().doubleValue() + " zł(Netto), "
                + getGrossTotalPrice().doubleValue() + " zł(Brutto), " + getTotalPriceWithRabat().doubleValue() + " zł(Po Rabacie: " + getMarkup().getVAT() + "%)");
    }

}
*/
