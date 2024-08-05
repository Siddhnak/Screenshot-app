package com.dhrubo.Screenshot.service;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ScreenshotServiceImpl implements ScreenshotService {

    private static final String SAVE_DIR = "C:\\Users\\Siddhant Naik\\Desktop\\Screenshot Proj\\";


    @Override
    public String takeScreenshot(String url) throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String fileName = "screenshot_" + timestamp + ".png";
        String filePath = SAVE_DIR + fileName;

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
