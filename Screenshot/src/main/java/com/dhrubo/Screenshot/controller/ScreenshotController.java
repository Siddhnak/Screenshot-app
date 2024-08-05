package com.dhrubo.Screenshot.controller;

import com.dhrubo.Screenshot.service.ScreenshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/api")
public class ScreenshotController {
    @Autowired
    private ScreenshotService screenshotService;

    @PostMapping("/screenshot")
    public String takeScreenshot(@RequestParam String url) {
        try {
            String filePath = screenshotService.takeScreenshot(url);
            return "Screenshot saved at: " + filePath;
        } catch (IOException e) {
            return "Failed to take screenshot: " + e.getMessage();
        }
    }
}
