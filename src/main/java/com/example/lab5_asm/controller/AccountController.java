package com.example.lab5_asm.controller;

import com.example.lab5_asm.model.User;
import com.example.lab5_asm.repository.UserRepository;
import com.example.lab5_asm.service.CookieService;
import com.example.lab5_asm.service.ParamService;
import com.example.lab5_asm.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CookieService cookieService;

    @Autowired
    private ParamService paramService;

    @Autowired
    private SessionService sessionService;

    // Hiển thị form đăng nhập
    @GetMapping("/")
    public String loginForm() {
        return "Login";
    }

    // Xử lý đăng nhập
    @PostMapping("/account/login")
    public String loginSubmit(RedirectAttributes redirectAttributes) {
        String username = paramService.getString("username", "");
        String password = paramService.getString("password", "");
        boolean remember = paramService.getBoolean("remember", false);

        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(password)) { // Nên dùng mã hóa bcrypt thay vì lưu plain text
                sessionService.set("username", username);
                sessionService.set("role", user.getRole());

                if (remember) {
                    cookieService.add("user", username, 10);
                } else {
                    cookieService.remove("user");
                }

                // Điều hướng dựa vào vai trò
                if ("ADMIN".equals(user.getRole())) {
                    return "redirect:/admin"; // Chuyển hướng đến trang quản trị
                } else {
                    return "redirect:/products"; // Chuyển hướng đến trang sản phẩm
                }
            }
        }

        redirectAttributes.addFlashAttribute("error", "Invalid username or password.");
        return "redirect:/";
    }
}
