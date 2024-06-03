//package com.example.demo.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class HomeController {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private AuthController authController;
//
//    @GetMapping("/home")
//    public String home(Model model) {
//        // Получаем имя пользователя (предположим, что у вас есть метод getLoggedInUsername, который возвращает имя текущего пользователя)
//        String username = getLoggedInUsername();
//
//        // Получаем идентификатор пользователя по его имени
//        Optional<Long> userIdOptional = userService.findIdByUsername(username);
//
//        // Добавляем идентификатор пользователя в модель
//        userIdOptional.ifPresent(userId -> model.addAttribute("userId", userId));
//
//        return "home";
//    }
//}
