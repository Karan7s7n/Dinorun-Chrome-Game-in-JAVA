import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;

public class Dinorun extends JPanel implements ActionListener, KeyListener {
    int bw = 750;
    int bh = 250;

    Image dinosourImg;
    Image dinosourDeadImg;
    Image dinosourJumpImg;
    Image cactus1Img;
    Image cactus2Img;
    Image cactus3Img;

    class Block {
        int x, y, w, h;
        Image img;

        Block(int x, int y, int w, int h, Image img) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.img = img;
        }
    }

    int dw = 88, dh = 94, dx = 50, dy = bh - dh;
    Block dinosour;

    int c1w = 34, c2w = 69, c3w = 102, ch = 70, cx = 700, cy = bh - ch;
    ArrayList<Block> cacarr;

    int vx = -12, vy = 0, gravity = 1;
    boolean GmO = false;
    boolean paused = false;
    int score = 0, highScore = 0, lives = 3;

    Timer gameLoop;
    Timer placecac;

    public Dinorun() {
        setPreferredSize(new Dimension(bw, bh));
        setBackground(Color.lightGray);
        setFocusable(true);
        addKeyListener(this);
    try {
        dinosourImg = new ImageIcon(new URL("https://raw.githubusercontent.com/Karan7s7n/Dinorun-Chrome-Game-in-JAVA/main/dino-run.gif")).getImage();
        dinosourDeadImg = new ImageIcon(new URL("https://raw.githubusercontent.com/Karan7s7n/Dinorun-Chrome-Game-in-JAVA/main/dino-dead.png")).getImage();
        dinosourJumpImg = new ImageIcon(new URL("https://raw.githubusercontent.com/Karan7s7n/Dinorun-Chrome-Game-in-JAVA/main/dino-jump.png")).getImage();
        cactus1Img = new ImageIcon(new URL("https://raw.githubusercontent.com/Karan7s7n/Dinorun-Chrome-Game-in-JAVA/main/cactus1.png")).getImage();
        cactus2Img = new ImageIcon(new URL("https://raw.githubusercontent.com/Karan7s7n/Dinorun-Chrome-Game-in-JAVA/main/cactus2.png")).getImage();
        cactus3Img = new ImageIcon(new URL("https://raw.githubusercontent.com/Karan7s7n/Dinorun-Chrome-Game-in-JAVA/main/cactus3.png")).getImage();
        catch (MalformedURLException e) {
        intStackTrace();
    }


        dinosour = new Block(dx, dy, dw, dh, dinosourImg);
        cacarr = new ArrayList<>();
        loadHighScore();

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();

        placecac = new Timer(1500, e -> placeCactus());
        placecac.start();
    }

    void placeCactus() {
        if (GmO) return;
        double placeCacChance = Math.random();
        Block cactus;
        if (placeCacChance > .90) cactus = new Block(cx, cy, c3w, ch, cactus3Img);
        else if (placeCacChance > .70) cactus = new Block(cx, cy, c2w, ch, cactus2Img);
        else cactus = new Block(cx, cy, c1w, ch, cactus1Img);
        cacarr.add(cactus);

        if (cacarr.size() > 10) cacarr.remove(0);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(dinosour.img, dinosour.x, dinosour.y, dinosour.w, dinosour.h, null);
        for (Block cactus : cacarr) g.drawImage(cactus.img, cactus.x, cactus.y, cactus.w, cactus.h, null);

        g.setColor(Color.black);
        g.setFont(new Font("Courier", Font.PLAIN, 32));
        g.drawString("Score: " + score, 10, 35);
        g.drawString("High Score: " + highScore, bw - 250, 35);
        g.drawString("Lives: " + "I".repeat(lives), 350, 35);

        if (GmO) g.drawString("Game Over - Press Space to Restart", 150, 100);
    }

    public void move() {
        if (paused) return;
        vy += gravity;
        dinosour.y += vy;

        if (score % 500 == 0 && score > 0) vx -= 1;
        if (dinosour.y > dy) {
            dinosour.y = dy;
            vy = 0;
            dinosour.img = dinosourImg;
        }

        for (Block cactus : cacarr) {
            cactus.x += vx;
            if (coll(dinosour, cactus)) {
                lives--;
                if (lives <= 0) {
                    GmO = true;
                    dinosour.img = dinosourDeadImg;
                    if (score > highScore) {
                        highScore = score;
                        saveHighScore();
                    }
                }
            }
        }
        score++;
    }

    boolean coll(Block a, Block b) {
        return a.x < b.x + b.w && a.x + a.w > b.x && a.y < b.y + b.h && a.y + a.h > b.y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (GmO) {
            placecac.stop();
            gameLoop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (dinosour.y == dy) vy = -17;
            dinosour.img = dinosourJumpImg;

            if (GmO) {
                // Restart the game after Game Over
                resetGame();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            paused = !paused;  // Toggle pause state
            if (paused) {
                gameLoop.stop();
                placecac.stop();
            } else {
                gameLoop.start();
                placecac.start();
            }
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}

    void resetGame() {
        score = 0;
        lives = 3;
        vx = -12;
        GmO = false;
        cacarr.clear();
        dinosour.img = dinosourImg;
        gameLoop.start();
        placecac.start();
    }

    void loadHighScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader("highscore.txt"))) {
            highScore = Integer.parseInt(reader.readLine());
        } catch (Exception e) {
            highScore = 0;
        }
    }

    void saveHighScore() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("highscore.txt"))) {
            writer.write(String.valueOf(highScore));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
