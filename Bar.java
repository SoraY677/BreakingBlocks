/*Bar java*/
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Bar{
     //barの移動  
     private static final int LEFT = 0; //左
     private static final int RIGHT = 1;//右
     //barの当たり具合
     public static final int NO_COLLISION = 0;//当たってない
     public static final int HIT_LEFT = 1;//barの左側に当たった
     public static final int HIT_RIGHT = 2;//barの右側に当たった

     private static final int SPEED = 10;//速さ
     private int x;//barのx位置
     private int y;//barのｙ位置
     public static int width;//barの高さ
     public static int height;//barの横幅
     private Image image;//barの画像

     //Mainpanelの参照 
     private MainPanel panel;
     public Bar(int x, int y,MainPanel panel){
                  this.x = x;
                  this.y = y;
                  this.panel = panel;

                  loadImage();//イメージのロード
      }

      //移動コマンドと画面外処理
      public void move(int dir) {
             if (dir == LEFT){
                x -= SPEED;
           } else if(dir ==RIGHT){
                x += SPEED;
           }
             if(x<0){
               x = 0;
           }
             if(x > MainPanel.WIDTH - width){
                x = MainPanel.WIDTH - width;
           }
       } 
      
     
      //barの描写
      public void draw(Graphics g) {
             g.drawImage(image,x,y,null);
      }
      //barの位置
        public Point getPos(){
           return new Point(x,y);
      }
      //barの幅
      public int getWidth(){
           return width;
      }
      //barの高さ
      public int getHeight(){
      return height;
      }
      //イメージの読み込み
       private void loadImage(){
        ImageIcon icon = new ImageIcon(getClass().
         getResource("image/bar.gif"));
        image = icon.getImage();
      //幅と高さのセット
      width =image.getWidth(panel);
      height = image.getHeight(panel);
     }
      
    //当たり判定の設定
    public int collideWith(Ball ball) {
      //barの当たり判定の範囲
        Rectangle barRectLeft = new Rectangle(x, y,
                     width / 2,height);
        Rectangle barRectRight = new Rectangle(x + width /2, y,
                     width / 2, height);
      //ballの当たり判定の範囲
          Rectangle ballRect = new Rectangle(
            ball.getX(),ball.getY(),ball.getSize(),ball.getSize());
     //重なったら当たっている
        if (barRectLeft.intersects(ballRect)) {
         return HIT_LEFT;
  }else if (barRectRight.intersects(ballRect)){
         return HIT_RIGHT;
        }
         return NO_COLLISION;
       }
   }


 
            
                  
     