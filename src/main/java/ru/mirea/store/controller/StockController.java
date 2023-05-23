package ru.mirea.store.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mirea.store.entity.cloth.Cloth;
import ru.mirea.store.entity.cloth.ClothColor;
import ru.mirea.store.entity.cloth.ClothType;
import ru.mirea.store.service.ClothService;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping(value = "stock")
public class StockController {
    private final ClothService clothService;

    @GetMapping
    public String stock(Model model, @ModelAttribute("cloth") Cloth cloth) {
        cloth.setType(ClothType.T_SHIRT);
        cloth.setColor(ClothColor.BROWN);
        cloth.setAmount(1L);
        cloth.setPrice(100);
        cloth.setSize(50);
        model.addAttribute("types", ClothType.values());
        model.addAttribute("colors", ClothColor.values());
        model.addAttribute("clothes", clothService.findAll(Sort.by("id")));
        return "stock";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String add(@ModelAttribute("cloth") Cloth cloth) {
        clothService.add(cloth);
        return "redirect:/stock";
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String increase(@ModelAttribute Cloth cloth) {
        clothService.changeAmount(cloth.getId(), cloth.getAmount());
        return "redirect:/stock";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String reduce(@PathVariable Long id) {
        Optional<Cloth> cloth = clothService.findById(id);
        cloth.ifPresent((cloth1 -> clothService.changeAmount(id, -cloth1.getAmount())));
        return "redirect:/stock";
    }
}
