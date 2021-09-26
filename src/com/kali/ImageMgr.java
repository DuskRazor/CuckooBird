package com.kali;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ImageMgr {
    static BufferedImage background,ground,pipe,gameOver,start,ready;
    static BufferedImage[] bird = new BufferedImage[3];
    private static final ImageMgr INSTANCE = new ImageMgr();
    //nothing
    private ImageMgr(){
        initImage();
    };

    private void initImage(){
        try {
            background = ImageIO.read(Objects.requireNonNull(Cuckoo.class.getClassLoader().getResourceAsStream("images/bg_light.png")));
            ground = ImageIO.read(Objects.requireNonNull(Cuckoo.class.getClassLoader().getResourceAsStream("images/ground.png")));
            pipe = ImageIO.read(Objects.requireNonNull(Cuckoo.class.getClassLoader().getResourceAsStream("images/pipe.png")));
            gameOver = ImageIO.read(Objects.requireNonNull(Cuckoo.class.getClassLoader().getResourceAsStream("images/gameover.png")));
            start = ImageIO.read(Objects.requireNonNull(Cuckoo.class.getClassLoader().getResourceAsStream("images/start.png")));
            ready = ImageIO.read(Objects.requireNonNull(Cuckoo.class.getClassLoader().getResourceAsStream("images/ready.png")));

            for(int i = 0;i < 3;i++){
                bird[i] = ImageIO.read(Objects.requireNonNull(Cuckoo.class.getClassLoader().getResourceAsStream("images/blueBird_" + (i+1) + ".png")));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ImageMgr getInstance(){
        return INSTANCE;
    }
}
