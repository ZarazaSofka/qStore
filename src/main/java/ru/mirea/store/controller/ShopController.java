package ru.mirea.store.controller;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mirea.store.entity.cloth.Cloth;
import ru.mirea.store.entity.cloth.ClothColor;
import ru.mirea.store.entity.cloth.ClothType;
import ru.mirea.store.service.ClothService;

@Controller
@AllArgsConstructor
@RequestMapping(value = "shop")
public class ShopController {
    private final ClothService clothService;

    @GetMapping
    public String shop(@RequestParam(required = false) ClothType type, @RequestParam(required = false) ClothColor color,
                       @RequestParam(required = false) Integer size, @RequestParam(defaultValue = "0") Integer min,
                       @RequestParam(defaultValue = "9999") Integer max, @RequestParam(defaultValue = "id") String sortBy, Model model, Pageable pageable) {
        Sort sorting;
        if (sortBy.equals("priceAsc"))
            sorting = Sort.by("price").ascending().and(Sort.by("id"));
        else if (sortBy.equals("priceDesc"))
            sorting = Sort.by("price").descending().and(Sort.by("id"));
        else {
            sortBy = "id";
            sorting = Sort.by("id");
        }
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sorting);
        Page<Cloth> clothes;
        if (type != null && color != null && size != null)
            clothes = clothService.findByTypeAndColorAndSizeAndPriceBetween(type, color, size, min, max, pageable);
        else if (type != null && color != null)
            clothes = clothService.findByTypeAndColorAndPriceBetween(type, color, min, max, pageable);
        else if (type != null && size != null)
            clothes = clothService.findByTypeAndSizeAndPriceBetween(type, size, min, max, pageable);
        else if (color != null && size != null)
            clothes = clothService.findByColorAndSizeAndPriceBetween(color, size, min, max, pageable);
        else if (type != null)
            clothes = clothService.findByTypeAndPriceBetween(type, min, max, pageable);
        else if (color != null)
            clothes = clothService.findByColorAndPriceBetween(color, min, max, pageable);
        else if (size != null)
            clothes = clothService.findBySizeAndPriceBetween(size, min, max, pageable);
        else
            clothes = clothService.findByPriceBetween(min, max, pageable);
        model.addAttribute("clothes", clothes);
        model.addAttribute("types", ClothType.values());
        model.addAttribute("colors", ClothColor.values());
        model.addAttribute("sort_type", type);
        model.addAttribute("sort_color", color);
        model.addAttribute("size", size);
        model.addAttribute("min", min);
        model.addAttribute("max", max);
        model.addAttribute("sorting", sortBy);
        return "shop";
    }
}