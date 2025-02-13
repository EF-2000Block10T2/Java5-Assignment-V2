package com.example.lab5_asm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.lab5_asm.service.CookieService;
import com.example.lab5_asm.service.ParamService;
import com.example.lab5_asm.service.SessionService;

import java.io.File;

@Controller
public class AccountController {

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
    public String loginSubmit() {
        String username = paramService.getString("username", "");
        String password = paramService.getString("password", "");
        boolean remember = paramService.getBoolean("remember", false);

        // Kiểm tra đăng nhập (giả sử username: poly, password: 123)
        if ("poly".equals(username) && "123".equals(password)) {
            // Lưu username vào session
            sessionService.set("username", username);
            // Xử lý cookie ghi nhớ đăng nhập
            if (remember) {
                cookieService.add("user", username, 10); // 10 ngày
            } else {
                cookieService.remove("user");
            }
            return "redirect:/products";
        }
        // Nếu đăng nhập thất bại, quay lại trang đăng nhập
        return "products";
    }

    // Hiển thị form đăng ký
    @GetMapping("/account/register")
    public String registerForm() {
        return "Register"; // trỏ đến file account/register.html
    }

    // Xử lý đăng ký có upload hình
    @PostMapping("/account/register")
    public ModelAndView registerSubmit(@RequestParam("username") String username,
                                       @RequestParam("password") String password,
                                       @RequestParam("file") MultipartFile file) {
        ModelAndView mav = new ModelAndView("account/register");
        try {
            // Sử dụng đối tượng paramService được tiêm để gọi phương thức save()
            File savedFile = ParamService.save(file, "uploads/");
            // Ở đây bạn có thể xử lý lưu thông tin tài khoản vào database nếu cần\n
            mav.addObject("message", "User registered successfully. Uploaded file: " + savedFile.getName());
        } catch (Exception e) {
            mav.addObject("message", "Registration failed: " + e.getMessage());
        }
        return mav;
    }
}
