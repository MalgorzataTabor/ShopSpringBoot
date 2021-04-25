package pl.tabor.Model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
@Data
/*@ConfigurationProperties(
        prefix = "markup",
        ignoreUnknownFields = true

)*/
public class Markup {

    private BigDecimal VAT;
    private BigDecimal discount;


}
