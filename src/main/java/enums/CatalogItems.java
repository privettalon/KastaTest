package enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CatalogItems {

    Clothing("Одяг"),
    Footwear("Взуття"),
    DRESS("Плаття"),
    SUKNI("Сукні"),
    SHIRTS("Сорочки"),
    ForСhildren("Дітям"),
    HouseholdGoods("Побутова хімія і госптовари"),
    products("Продукти та алкоголь"),
    Beauty("Краса та здоров'я"),
    Accessories("Аксесуари"),
    homeItems("Товари для дому"),
    Sport("Спорт та відпочинок"),
    Electronics ("Електроніка та техніка");

    @Getter
    private String name;
}