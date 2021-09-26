package com.kali;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * 1.窗口
 * 2.背景
 * 3.移动墙
 * 4.布谷鸟
 * 5.障碍物
 * 6.背景音乐
 * 7.音效
 * 8.欢迎界面
 * 9.game over
 */
public class Cuckoo extends Frame {
    private int step;
    private int score;
    private int height;
    private int moveLeft;
    private int pipeMove;

    boolean crash = false;
    private boolean over = false;
    private boolean start = false;

    private static int bird_y = 270;
    private static final int L_X = 800;
    private static final int L_Y = 200;
    private static final int SPEED = 2;
    private static final int BIRD_X = 100;
    private static final int GAME_X_Y_0 = 0;
    private static final int GAME_WIDTH = 288;
    private static final int GAME_HEIGHT = 512;
    private static final int PIPE_WIDTH = ImageMgr.pipe.getWidth();
    private static final int PIPE_HEIGHT = ImageMgr.pipe.getHeight();
    private static final int READY_WIDTH = ImageMgr.ready.getWidth();
    private static final int READY_HEIGHT = ImageMgr.ready.getHeight();
    private static final int START_WIDTH = ImageMgr.start.getWidth();
    private static final int BIRD_WIDTH = ImageMgr.bird[0].getWidth();
    private static final int BIRD_HEIGHT = ImageMgr.bird[0].getWidth();
    private static final int GROUND_HEIGHT = ImageMgr.ground.getHeight();
    private static final int GAME_OVER_WIDTH = ImageMgr.gameOver.getWidth();
    private static final int GAME_OVER_HEIGHT = ImageMgr.gameOver.getHeight();
    private static final int BACKGROUND_HEIGHT = ImageMgr.background.getHeight();

    private static Image offScreenImage;
    private static Rectangle pipeRectangle1;
    private static Rectangle pipeRectangle2;
    private static Rectangle birdRectangle;

    Cuckoo(){
        setVisible(true);
        setResizable(false);
        //setTitle("cuckoo");
        setBounds(L_X, L_Y,GAME_WIDTH,GAME_HEIGHT);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_SPACE : start = true;break;
                    case KeyEvent.VK_ENTER : reopen();break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    bird_y -= 20;
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void update(Graphics g) {
        if(offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {
        background(g);
        ground(g);
        start(g);

        if(start){
            bird(g);
            pipe(g);
            colliedWith();
            gameOver(g);
            score(g);
        }

    }

    //如果碰撞后，可以嗯enter键重开
    private void reopen(){
        if(crash){
            score = 0;
            over = false;
            crash = false;
            start = false;
            moveLeft = 0;
            pipeMove = 0;
            //bird_y = 270;//继上次死的位置开始
        }
    }

    private void score(Graphics g){
        Font font = new Font("微软雅黑",Font.BOLD,18);
        g.setFont(font);
        Color c = g.getColor();
        g.setColor(Color.lightGray);
        g.drawString("score " + score,20,70);
        g.setColor(c);
    }

    private void background(Graphics g){
        g.drawImage(ImageMgr.background,
                GAME_X_Y_0 - moveLeft,GAME_X_Y_0,
                GAME_WIDTH - moveLeft,GAME_HEIGHT,
                GAME_X_Y_0,
                GAME_X_Y_0,
                GAME_WIDTH,
                GAME_HEIGHT, null);
        g.drawImage(ImageMgr.background,
                GAME_WIDTH-1 - moveLeft,GAME_X_Y_0,
                GAME_WIDTH*2 - moveLeft, GAME_HEIGHT,
                GAME_X_Y_0,
                GAME_X_Y_0,
                GAME_WIDTH,
                GAME_HEIGHT, null);
        moveLeft += SPEED;
        //游戏启动计分
        if(start && moveLeft % 100 == 0){
            score += 1;
        }
        if(moveLeft >= GAME_WIDTH){
            moveLeft = 0;
        }
    }

    private void ground(Graphics g){
        g.drawImage(ImageMgr.ground,0,BACKGROUND_HEIGHT - GROUND_HEIGHT,null);
    }

    private void bird(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.BLUE);
        g.drawRect(BIRD_X, bird_y,BIRD_WIDTH,BIRD_HEIGHT);
        g.setColor(c);

        g.drawImage(ImageMgr.bird[step++], BIRD_X, bird_y,null);
        if(step >= ImageMgr.bird.length){
            step = 0;
        }

        if(start){
            bird_y += 4;
        }

        birdRectangle = new Rectangle(BIRD_X,bird_y,BIRD_WIDTH,BIRD_HEIGHT);
    }

    private void pipe(Graphics g){
        drawPipe(g);
        pipeMove += SPEED;
        if(pipeMove >= GAME_WIDTH + PIPE_WIDTH){
            pipeMove = 0;
            Random ran = new Random();
            height = ran.nextInt(10)*20;
            drawPipe(g);
        }

    }

    private void drawPipe(Graphics g){
        g.setColor(Color.RED);
//        g.drawRect(GAME_WIDTH - pipeMove,GAME_HEIGHT/5*3 - height,PIPE_WIDTH,PIPE_HEIGHT);
//        g.drawRect(GAME_WIDTH - pipeMove,-GAME_HEIGHT/5*4 - height,PIPE_WIDTH,PIPE_HEIGHT);

        g.drawImage(ImageMgr.pipe,GAME_WIDTH - pipeMove,GAME_HEIGHT/5*3 - height,null);
        g.drawImage(ImageMgr.pipe,GAME_WIDTH - pipeMove,-GAME_HEIGHT/5*4 - height,null);

        pipeRectangle1 = new Rectangle(GAME_WIDTH - pipeMove,GAME_HEIGHT/5*3 - height,PIPE_WIDTH,PIPE_HEIGHT);
        pipeRectangle2 = new Rectangle(GAME_WIDTH - pipeMove,-GAME_HEIGHT/5*4 - height,PIPE_WIDTH,PIPE_HEIGHT);
    }

    private void gameOver(Graphics g){
        if(over){
            g.drawImage(ImageMgr.gameOver,GAME_WIDTH/2 - GAME_OVER_WIDTH/2, GAME_HEIGHT/2 - GAME_OVER_HEIGHT,null);
        }
    }

    private void start(Graphics g){
        if(!start){
            g.drawImage(ImageMgr.ready,GAME_WIDTH/2 - READY_WIDTH/2, GAME_HEIGHT/2 - READY_HEIGHT,null);
            g.drawImage(ImageMgr.start,GAME_WIDTH/2 - START_WIDTH/2, GAME_HEIGHT/2,null);
        }
    }

    private void colliedWith(){
        if(birdRectangle.intersects(pipeRectangle1) || birdRectangle.intersects(pipeRectangle2)){
            crash = true;
            over = true;
            //gameOver(g);
        }
    }
}
