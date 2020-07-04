package guru.springframework.msscbeerservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerDto {
    private UUID uuid;
    private Integer version;

    private Date createdDate;
    private Date lastModifiedDate;

    private String BeerName;

    private BeerStyle beerStyle;
    private Long upc;

    private BigDecimal price;
    private Integer quantityOnHand;
}
