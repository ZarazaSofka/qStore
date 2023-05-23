package ru.mirea.store.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mirea.store.entity.OrderItem;
import ru.mirea.store.entity.User;
import ru.mirea.store.service.OrderService;
import ru.mirea.store.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final OrderService orderService;

    @GetMapping("/")
    String redirectToShop() {
        return "redirect:/shop";
    }

    @GetMapping("/signup")
    String signUpGet(@ModelAttribute("user") User user) {
        return "signup";
    }

    @PostMapping("/signup")
    String signUpPost(@ModelAttribute("user") User user) {
        if (!userService.signUpUser(user))
            return "signup";
        return "redirect:/login";
    }

    @GetMapping("login")
    public String getLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/userpage", method = RequestMethod.GET)
    public String user_page(Model model, Principal principal) {
        User user = userService.findUserByUsername(principal.getName());

        List<OrderItem> orderItems = orderService.findById(user.getBasket_id()).getOrderItems();
        model.addAttribute("order_items", orderItems);
        model.addAttribute("basket_price",
                orderItems.stream().reduce(0, (i, orderItem) -> i + orderItem.getCloth().getPrice(), Integer::sum));
        return "user_page";
    }

    @RequestMapping(value = "/userpage/{user_id}/{id}", method = RequestMethod.POST)
    public String add(@PathVariable Long user_id, @PathVariable Long id)  {
        orderService.addToBasket(user_id, id);
        return "redirect:/shop";
    }

    @RequestMapping(value = "/userpage/{user_id}/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long user_id, @PathVariable Long id)  {
        orderService.deleteFromBasket(user_id, id);
        return "redirect:/userpage";
    }

    @RequestMapping(value = "/userpage/{user_id}", method = RequestMethod.POST)
    public String acceptOrder(@PathVariable Long user_id)  {
        orderService.acceptOrder(user_id);
        return "redirect:/userpage";
    }
}