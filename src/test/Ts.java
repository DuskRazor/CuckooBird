package test;

import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Ts {
    @Test
    public void get(){
        try {
            BufferedImage image = ImageIO.read(Ts.class.getClassLoader().getResourceAsStream("images/bg_light.png"));
            Assert.assertNotNull(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
