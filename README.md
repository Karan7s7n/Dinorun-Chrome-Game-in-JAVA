🦖 DinoRun – A Chrome Dinosaur Game Clone in Java

## 🎮 Overview

**DinoRun** is a fun, fast-paced endless runner game inspired by the classic Chrome dinosaur game. Built entirely using Java's AWT and Swing libraries, the game features responsive controls, animated sprites, obstacle spawning, score tracking, and persistent high score storage — all without using any external game engine.

---

## ✨ Features

✅ **Classic Gameplay** – Endless side-scrolling action where you dodge cacti by jumping.
✅ **Animated Dino** – Uses animated GIFs for realistic running and jumping.
✅ **Obstacle Variety** – Includes 3 different types of cactus images randomly selected and placed.
✅ **Score & High Score** – Real-time score tracking and persistent high score using `highscore.txt`.
✅ **Lives System** – Start with 3 lives, lose one for every collision.
✅ **Pause/Resume** – Press `P` to pause or resume the game at any point.
✅ **Game Over & Restart** – After losing all lives, press `SPACE` to restart the game.
✅ **Dynamic Difficulty** – Game speed increases every 500 points to keep the challenge alive.

---

## 🕹️ Controls

| Key     | Action                         |
| ------- | ------------------------------ |
| `SPACE` | Jump / Restart after Game Over |
| `P`     | Pause / Resume the Game        |

---

## 📂 Project Structure

```
/dinorun
│── App.java               # Main launcher class with JFrame setup
│── Dinorun.java           # Game logic, rendering, and controls
│── highscore.txt          # Stores highest score locally
```

---

## 🧠 How It Works

### 🎨 Graphics & UI

* Uses `JPanel` for the canvas and `JFrame` as the main window.
* The dinosaur and cacti are displayed using `ImageIcon` with remote image URLs.
* Lives are shown as `"I"`, one for each life (like ancient Roman style: III).

### 🚀 Game Loop

* A `javax.swing.Timer` runs at \~60 FPS to update game state and repaint the screen.
* A separate timer spawns cacti at set intervals (`1500ms`).

### 💡 Game Mechanics

* Jumping is simulated using gravity and vertical velocity (`vy`).
* Cactus obstacles move horizontally and check for collisions with the dino.
* Speed increases as score milestones (every 500 points) are reached.
* Game state (`Game Over`, `Paused`, `Running`) is toggled via key events.

### 💾 High Score Handling

* On game over, the current score is compared with the saved high score in `highscore.txt`.
* If it's higher, it overwrites the old score.

---

## 🛠 Requirements

* Java JDK 8 or higher
* An internet connection (for image loading via URLs)

---

## 🧩 Possible Enhancements

* Add background scrolling and day/night cycle
* Add sound effects and music
* Add flying obstacles (like birds)
* Save score history or leaderboard system
* Offline asset loading instead of online image URLs


---

Images used are pulled from GitHub URLs:

* 🏃‍♂️ Dino Running GIF
* 🦴 Dino Jump
* 💀 Dino Dead
* 🌵 Cactus variants (1x, 2x, 3x)

* 👨‍💻 Developer

Karan Singh Negi
Frontend Developer | React | TypeScript | Java | Flutter

GitHub: https://github.com/Karan7s7n

Portfolio: 

LinkedIn: www.linkedin.com/in/karan-singh-negi-0098532b5




## Gameplay images:

<img width="754" height="281" alt="d1" src="https://github.com/user-attachments/assets/fd06a0d8-9ff9-4de2-8e58-5de5d423e2d9" />
<img width="758" height="294" alt="d2" src="https://github.com/user-attachments/assets/142c8349-c6fc-410e-b948-4d44dc00390b" />


