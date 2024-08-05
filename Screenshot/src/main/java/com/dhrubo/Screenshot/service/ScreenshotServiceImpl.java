package com.dhrubo.Screenshot.service;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.io.IOException;

@Service
public class ScreenshotServiceImpl implements ScreenshotService {

    private static final String SCREENSHOT_DIRECTORY = "C:\\Users\\Siddhant Naik\\Desktop\\Screenshot Proj\\";


    @Override
    public String takeScreenshot(String url) throws IOException {
        String fileName = "screenshot.png";
        String filePath = SCREENSHOT_DIRECTORY + fileName;
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            Page page = browser.newPage();
            page.navigate(url);
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filePath)));
            browser.close();
        }
        return filePath;
    }
}
