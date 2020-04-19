import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.*;

import javax.swing.*;

public class MainPanel extends JPanel implements Runnable,KeyListener{
        //�p�l���T�C�Y�@�c=600�C��=550
        public static final int WIDTH = 550;
        public static final int HEIGHT = 600;
        //�u���b�N�̏c�~��	
        private static final int NUM_BLOCK_ROW = 10;
        private static final int NUM_BLOCK_COL = 7;
        private static final int NUM_BLOCK = NUM_BLOCK_ROW*NUM_BLOCK_COL;
        //�����萔
        private static final int LEFT = 0;
        private static final int RIGHT = 1;
        //�Q�[���̏��
        private static final int START = 0;
        private static final int PLAY = 1;
        private static final int GAMEOVER = 2;
        private int gameState;
           
        private Bar bar;//�o�[
        private Ball ball;//�{�[��
        private Block[] block;//�u���b�N

        private Image BBtitleImage;
        private Image BBgameoverImage;
        //KEY�̏��
        private boolean leftPressed = false;
        private boolean rightPressed = false;
        //�Q�[�����[�v�̃X���b�h�̎���
        private Thread gameLoop;

      //���C���p�l��
      public MainPanel(){
          setPreferredSize(new Dimension(WIDTH,HEIGHT));//pack()�p
          setFocusable(true);//�p�l�����L�[�{�[�h���󂯕t����悤�ɂ���
          
          loadImage();
           
          addKeyListener(this);//�L�[�C�x���g���X�i�[�Ɠo�^
          
          gameState = START;
         
           }
       
      
      //�j���[�Q�[��
       public void newGame() {
           bar = new Bar(WIDTH / 2,HEIGHT - 20,this);//bar�̍쐬
           ball = new Ball(this);//ball�̍쐬
           block = new Block[NUM_BLOCK];
          //�u���b�N����ׂ�
           for(int i = 0; i< NUM_BLOCK_ROW;i++){
           for(int j = 0; j< NUM_BLOCK_COL;j++){
              int x = j * Block.WIDTH + Block.WIDTH;
              int y = i * Block.HEIGHT + Block.HEIGHT;
              block[i * NUM_BLOCK_COL + j] = new Block(x,y);
            }
         }
          //�Q�[�����[�v�̊J�n      
         gameLoop = new Thread(this);
         gameLoop.start();

         gameState =PLAY;//�Q�[����Ԃ̓v���C

          }
        //�Q�[���I�[�o�[
        public void gameOver(){
             gameState = GAMEOVER;
         }

     //�Q�[�����[�v
     public void run(){
           while(true){

           if (gameState != PLAY)
                 break;
          
            if(ball.getY() > HEIGHT){
               gameOver();
              }
                 
             bar_move(); //�o�[�̈ړ�
                           
             ball.move(); //ball�̈ړ�

             collisionDetection();//�����蔻��
             
             blockjudge();//�u���b�N�����蔻��
            
            repaint();//�ĕ`��

            try{
               Thread.sleep(20);
           } catch (InterruptedException e){
               e.printStackTrace();
           }
          }
         repaint();//�ĕ`��
      
       }
 
  
      //bar�̈ړ�
      private void bar_move() { 
                  if(leftPressed){
                     bar.move(LEFT);//���ւ̈ړ�
            }else if(rightPressed) {
                     bar.move(RIGHT);//�E�ւ̈ړ�
            } 
         }
    //�o�[�̓����蔻��
    private void collisionDetection() {
          int collidePos = bar.collideWith(ball);
          //bar�ɓ������Ă�����
          if (collidePos != Bar.NO_COLLISION) { 
               switch (collidePos) {
               //����������
               case Bar.HIT_LEFT:
                 if (ball.getVX() > 0 ) ball.boundX();
                 ball.boundY();
                 break;
               //�E��������
               case Bar.HIT_RIGHT:
                 if (ball.getVX() < 0) ball.boundX();
                 ball.boundY();
                 break;   
           }
         }
       } 
     //�u���b�N�̓����蔻��
       private void blockjudge() {
            for(int i = 0;i<NUM_BLOCK;i++){
              if (block[i].isDeleted())
                  continue;
            int collidePos = block[i].collideWith(ball);
             //�u���b�N�ɓ������Ă���
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
              break;//���Ɉ�݂̂����󂹂Ȃ�
             }
            }   
           }
         

    //�`��
    public void paintComponent(Graphics g){
           super.paintComponent(g);

       if (gameState == START) {
                g.drawImage(BBtitleImage,0,0,this);
      }else if (gameState == PLAY) {
           //�w�i�͍�
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
 
   //�L�[�C�x���g    
    public void keyTyped(KeyEvent e){
       }

    
     public void keyPressed(KeyEvent e){
                  int key = e.getKeyCode();
            //�X�^�[�g��ʂł̓X�y�[�X�L�[�Ńj���[�Q�[��
          if(gameState == START){
               if(key == KeyEvent.VK_SPACE) {
                  gameState = PLAY;
                //�j���[�Q�[��
                 newGame();
             }
             //�v���C��ʂł̐ݒ�
          }else if (gameState == PLAY){   
            if (key == KeyEvent.VK_LEFT){
                leftPressed = true;
            }
            if (key == KeyEvent.VK_RIGHT){
                rightPressed = true;
             }
            //�Q�[���I�[�o�[��ʂł̓X�y�[�X�L�[�������ăX�^�[�g�ɖ߂�
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
    
       
               
                     
        



  
