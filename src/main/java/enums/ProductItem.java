package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ProductItem {

    DRESS_ISSA_PLUS("Garne сіра сукня karen однотонна — виробництво Україна", "Сукня KAREN Garne", "Garne"),
    WHITE_NENKA_DRESS("Garne комбінованя кежуал сукня ornella однотонна — виробництво Україна","Сукня ORNELLA Garne","Garne");


    @Getter
    private String name;
    @Getter
    private String cartName;
    @Getter
    private String sellersName;
}
