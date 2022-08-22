package com.baimao.wechatReminder;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

//@SpringBootTest
public class MusicTest {

    @Test
    public void load() {
        String path = "mp3/jjc.mp3";
        try {
            ClassPathResource classPathResource = new ClassPathResource(path);
            InputStream file = classPathResource.getInputStream();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
