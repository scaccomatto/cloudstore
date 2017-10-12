package com.paru.cloudstore;

import com.paru.cloudstore.model.Food;
import com.paru.cloudstore.services.GoogleCloudStoreService;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertTrue;

public class GoogleCloudStoreServiceTest {

    GoogleCloudStoreService ss =null;

    @Before
    public  void setUp() throws IOException {
        ss= new GoogleCloudStoreService();
    }

    @Test
    public void testImageUpload() throws FileNotFoundException {

        InputStream is = new BufferedInputStream(new FileInputStream("/home/teo/Pictures/IMG_20171003_142557.vr.jpg"));
        Food food = new Food();
        food.setName("unnomeacazzo");

        assertTrue((ss.savePicture(is, food)));

    }
}
