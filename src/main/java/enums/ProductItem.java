package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ProductItem {

    DRESS_ISSA_PLUS("C&A біла кежуал сукня однотонна — виробництво Німеччина", "Сукня C&A", "md autlet"),
    WHITE_NENKA_DRESS("C&A футболка однотонна світло-рожева Кежуал виробництво — Німеччина","Футболка C&A","md autlet");

    @Getter
    private String name;
    @Getter
    private String cartName;
    @Getter
    private String sellersName;
}
