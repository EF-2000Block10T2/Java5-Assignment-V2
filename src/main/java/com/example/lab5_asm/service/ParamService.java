package com.example.lab5_asm.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class ParamService {
    @Autowired
    private HttpServletRequest request;

    public String getString(String name, String defaultValue) {
        return Optional.ofNullable(request.getParameter(name)).orElse(defaultValue);
    }

    public int getInt(String name, int defaultValue) {
        try {
            return Integer.parseInt(request.getParameter(name));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public double getDouble(String name, double defaultValue) {
        try {
            return Double.parseDouble(request.getParameter(name));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        return Optional.ofNullable(request.getParameter(name))
                .map(Boolean::parseBoolean)
                .orElse(defaultValue);
    }

    public Date getDate(String name, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(request.getParameter(name));
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format", e);
        }
    }

    public static File save(MultipartFile file, String path) {
        if (file.isEmpty()) return null;
        try {
            File destination = new File(path, file.getOriginalFilename());
            file.transferTo(destination);
            return destination;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file", e);
        }
    }
}
