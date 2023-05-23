package ru.mirea.store.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletResponse response)
    {
        if (response.getStatus() == HttpStatus.NOT_FOUND.value())
            return "error404";
        else if (response.getStatus() == HttpStatus.FORBIDDEN.value())
            return "error403";
        else if (response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value())
            return "error500";
        else
            return "error";
    }
}