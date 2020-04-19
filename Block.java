/*Block class*/
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block {
       //幅と高さ
       public static final int WIDTH = 60;
       public static final int HEIGHT =30;
      
       //ボールの当たり判定
       public static final int NO_COLLISION = 0;//未衝突
       public static final int DOWN = 1;//下
       public static final int LEFT = 2;//左
       public static final int RIGHT = 3;//右
       public static final int UP = 4;//上
       public static final int DOWN_LEFT = 5;//左下
       public static final int DOWN_RIGHT = 6;//右下
       public static final int UP_LEFT = 7;//左上
       public static final int UP_RIGHT = 8;//右上
       
       //位置
       private int x,y;
      
       //ボールが当たったか
       private boolean isDeleted;

       public Block(int x,int y) {
           this.x = x;
           this.y = y;
           isDeleted = false;
       }
       
       //blockの描写
       public void draw(Graphics g){
            //塗りつぶしの色はシアン
            g.setColor(Color.CYAN);
            g.fillRect(x,y,WIDTH,HEIGHT);
            //外線
            g.setColor(Color.BLACK);
            g.drawRect(x,y,WIDTH,HEIGHT);
        }
         
       //ボールとの衝突判定 
       public int collideWith(Ball ball){
          Rectangle blockRect = new Rectangle(x,y,WIDTH,HEIGHT);
           
       int ballX = ball.getX();
       int ballY = ball.getY();
       int ballSize =ball.getSize();
       //ballがブロックの下から衝突 
      if (blockRect.contains(ballX,ballY)
             && blockRect.contains(ballX + ballSize,ballY)){ 
             return DOWN;
       //ballがブロックの左から衝突
      }else if (blockRect.contains(ballX + ballSize,ballY)
             && blockRect.contains(ballX + ballSize,ballY + ballSize)){
             return LEFT;
       //ballがブロックの右からの衝突
      }else if (blockRect.contains(ballX,ballY)
             && blockRect.contains(ballX,ballY + ballSize)) {
              return RIGHT;                
       //ballがブロックの上からの衝突
      }else if (blockRect.contains(ballX ,ballY + ballSize)
             && blockRect.contains(ballX + ballSize,ballY + ballSize)){
          return UP;
       //ballがブロックの左下からの衝突
      }else if (blockRect.contains(ballX + ballSize, ballY)){
          return DOWN_LEFT;
       //ballがブロックの右下からの衝突
      }else if(blockRect.contains(ballX , ballY)){
          return DOWN_RIGHT;
       //ballがブロックの左上からの衝突
      }else if(blockRect.contains(ballX +ballSize,ballY +ballSize)) {
                return UP_LEFT;
       //ballがブロックの右上から衝突
      }else if(blockRect.contains(ballX,ballY +ballSize)) {
           return UP_RIGHT; 
      }   
      //当たっていない
      return NO_COLLISION;
      }


    //ブロック壊れた
    public void delete() {
       isDeleted = true;
   }
    
   public int getX() {
         return x;
   }
   public int getY(){
         return y;
   }
   public boolean isDeleted() {
         return isDeleted;
   }
 }

















       
 