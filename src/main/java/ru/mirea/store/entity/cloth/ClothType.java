package ru.mirea.store.entity.cloth;

public enum ClothType {
    OUTERWEAR("верхняя одежда"),
    UNDERWEAR("нижнее белье"),
    T_SHIRT("футболки"),
    DRESS("платья и сарафаны"),
    SUIT("костюмы"),
    SPORT("спортивная одежда"),
    SHIRT("рубашки и блузки"),
    JEANS("джинсы"),
    SHORTS("шорты"),
    SKIRT("юбки"),
    TROUSERS("брюки"),
    HAT("головной убор"),
    OTHER("другое");
    private final String name;
    ClothType(String name) {
        this.name = name;
    }

    public String getType() {
        return name;
    }
}
