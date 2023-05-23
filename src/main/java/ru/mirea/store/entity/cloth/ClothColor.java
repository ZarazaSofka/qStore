package ru.mirea.store.entity.cloth;

import java.awt.*;

public enum ClothColor {
    VIOLET("фиолетовый", new Color(130, 38, 128)),
    PINK("розовый", new Color(242, 35, 212)),
    RED("красный", new Color(255, 0, 0)),
    ORANGE("оранжевый", new Color(239, 126, 38)),
    BROWN("коричневый", new Color(150, 75, 0)),
    CREAM("бежевый", new Color(245, 194, 166)),
    YELLOW("желтый", new Color(255, 255, 0)),
    CHARTREUSE("салатовый", new Color(127, 255, 0)),
    GREEN("зеленый", new Color(20, 170, 20)),
    BLUE("синий", new Color(0, 0, 255)),
    LIGHT_BLUE("голубой", new Color(95, 180, 240)),
    TURQUOISE("бирюзовый", new Color(0, 167, 151)),
    GRAY("серый", new Color(128, 128, 128)),
    BLACK("черный", new Color(0, 0, 0)),
    WHITE("белый", new Color(255, 255, 255));
    private final String name;
    private final Color color;
    ClothColor(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getColor() {
        return name;
    }
    public String getRGB() {return "#" + String.format("%08X", color.getRGB()).substring(2);}
}
