import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.*;

import javax.swing.*;

public class MainPanel extends JPanel implements Runnable,KeyListener{
        //パネルサイズ　縦=600，横=550
        public static final int WIDTH = 550;
        public static final int HEIGHT = 600;
        //ブロックの縦×横	
        private static final int NUM_BLOCK_ROW = 10;
        private static final int NUM_BLOCK_COL = 7;
        private static final int NUM_BLOCK = NUM_BLOCK_ROW*NUM_BLOCK_COL;
        //方向定数
        private static final int LEFT = 0;
        private static final int RIGHT = 1;
        //ゲームの状態
        private static final int START = 0;
        private static final int PLAY = 1;
        private static final int GAMEOVER = 2;
        private int gameState;
           
        private Bar bar;//バー
        private Ball ball;//ボール
        private Block[] block;//ブロック

        private Image BBtitleImage;
        private Image BBgameoverImage;
        //KEYの状態
        private boolean leftPressed = false;
        private boolean rightPressed = false;
        //ゲームループのスレッドの実装
        private Thread gameLoop;

      //メインパネル
      public MainPanel(){
          setPreferredSize(new Dimension(WIDTH,HEIGHT));//pack()用
          setFocusable(true);//パネルがキーボードを受け付けるようにする
          
          loadImage();
           
          addKeyListener(this);//キーイベントリスナーと登録
          
          gameState = START;
         
           }
       
      
      //ニューゲーム
       public void newGame() {
           bar = new Bar(WIDTH / 2,HEIGHT - 20,this);//barの作成
           ball = new Ball(this);//ballの作成
           block = new Block[NUM_BLOCK];
          //ブロックを並べる
           for(int i = 0; i< NUM_BLOCK_ROW;i++){
           for(int j = 0; j< NUM_BLOCK_COL;j++){
              int x = j * Block.WIDTH + Block.WIDTH;
              int y = i * Block.HEIGHT + Block.HEIGHT;
              block[i * NUM_BLOCK_COL + j] = new Block(x,y);
            }
         }
          //ゲームループの開始      
         gameLoop = new Thread(this);
         gameLoop.start();

         gameState =PLAY;//ゲーム状態はプレイ

          }
        //ゲームオーバー
        public void gameOver(){
             gameState = GAMEOVER;
         }

     //ゲームループ
     public void run(){
           while(true){

           if (gameState != PLAY)
                 break;
          
            if(ball.getY() > HEIGHT){
               gameOver();
              }
                 
             bar_move(); //バーの移動
                           
             ball.move(); //ballの移動

             collisionDetection();//当たり判定
             
             blockjudge();//ブロック当たり判定
            
            repaint();//再描写

            try{
               Thread.sleep(20);
           } catch (InterruptedException e){
               e.printStackTrace();
           }
          }
         repaint();//再描写
      
       }
 
  
      //barの移動
      private void bar_move() { 
                  if(leftPressed){
                     bar.move(LEFT);//左への移動
            }else if(rightPressed) {
                     bar.move(RIGHT);//右への移動
            } 
         }
    //バーの当たり判定
    private void collisionDetection() {
          int collidePos = bar.collideWith(ball);
          //barに当たっていたら
          if (collidePos != Bar.NO_COLLISION) { 
               switch (collidePos) {
               //左だったら
               case Bar.HIT_LEFT:
                 if (ball.getVX() > 0 ) ball.boundX();
                 ball.boundY();
                 break;
               //右だったら
               case Bar.HIT_RIGHT:
                 if (ball.getVX() < 0) ball.boundX();
                 ball.boundY();
                 break;   
           }
         }
       } 
     //ブロックの当たり判定
       private void blockjudge() {
            for(int i = 0;i<NUM_BLOCK;i++){
              if (block[i].isDeleted())
                  continue;
            int collidePos = block[i].collideWith(ball);
             //ブロックに当たってたら
             if (collidePos != Block.NO_COLLISION) {
                  block[i].delete();
               switch (collidePos) {
                case Block.DOWN :
                case Block.UP:
                   ball.boundY();
                   break;
                case Block.LEFT :
                case Block.RIGHT:
                   ball.boundX();
                   break;
                case Block.UP_LEFT :
                case Block.UP_RIGHT:
                case Block.DOWN_LEFT :
                case Block.DOWN_RIGHT:
                   ball.boundXY();
                   break;
                }
              break;//一回に一つのみしか壊せない
             }
            }   
           }
         

    //描画
    public void paintComponent(Graphics g){
           super.paintComponent(g);

       if (gameState == START) {
                g.drawImage(BBtitleImage,0,0,this);
      }else if (gameState == PLAY) {
           //背景は黒
           g.setColor(Color.BLACK);
           g.fillRect(0,0,getWidth(),getHeight());

           bar.draw(g);
           ball.draw(g);
           for(int i=0;i <NUM_BLOCK;i++){
                if (!block[i].isDeleted()) {
                 block[i].draw(g);
              }
          }
            }else if (gameState == GAMEOVER) {
           g.drawImage(BBgameoverImage,0,0,this);
         }
       }
           private void loadImage() {
            MediaTracker tracker =new MediaTracker(this);
            BBtitleImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/BBtitle.gif"));
            BBgameoverImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/BBgameover.gif"));
            tracker.addImage(BBtitleImage,0);
            tracker.addImage(BBgameoverImage,0);
          try{
               tracker.waitForAll();
          }catch (InterruptedException e){
               e.printStackTrace();
              }
          }        
 
   //キーイベント    
    public void keyTyped(KeyEvent e){
       }

    
     public void keyPressed(KeyEvent e){
                  int key = e.getKeyCode();
            //スタート画面ではスペースキーでニューゲーム
          if(gameState == START){
               if(key == KeyEvent.VK_SPACE) {
                  gameState = PLAY;
                //ニューゲーム
                 newGame();
             }
             //プレイ画面での設定
          }else if (gameState == PLAY){   
            if (key == KeyEvent.VK_LEFT){
                leftPressed = true;
            }
            if (key == KeyEvent.VK_RIGHT){
                rightPressed = true;
             }
            //ゲームオーバー画面ではスペースキーを押してスタートに戻る
          }else if (gameState == GAMEOVER){
                if (key == KeyEvent.VK_SPACE) {
                        gameState = START;
             }
           }
           repaint();
          } 
     public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            
          if(key == KeyEvent.VK_LEFT){
             leftPressed = false;
         }
          if(key == KeyEvent.VK_RIGHT){
             rightPressed = false;
           }
         }
       }      
    
       
               
                     
        



  
