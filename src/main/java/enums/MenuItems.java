package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MenuItems {
    PROFILE("Профіль"),
    CART("Кошик"),
    FAVOURITES("Обране"),
    KASTAPOST("KastaPost"),
    HELP("Допомога");

    @Getter
    private String name;
}
