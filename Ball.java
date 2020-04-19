/*Ball Class*/
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Ball{
       
        public static final int SIZE = 8; //サイズ
        private int x,y;//位置
        private int vx,vy;//速度
        private Image image;//ballの画像
        //ballの幅と高さ
        private int width;
        private int height;
       
        private MainPanel panel; //MainPanelの参照

   public Ball(MainPanel panel){
          //位置の初期化
          x = 0;
          y = 290;
          panel = panel;
          
          //速度の初期化
          vx = 15;
          vy = 15;
         //イメージのロード
          loadImage();
         }
         
         public void draw(Graphics g){          
                  g.drawImage(image,x,y,null);
          }
          
          //ballの移動
          public void move() {
             x += vx;
             y += vy;
          
         
          //バウンド
          //左右の壁の場合
          if(x < 0 || x >MainPanel.WIDTH -SIZE){
            boundX();
            }
          //上の壁の場合
          if(y < 0) {
            boundY();
            }
         }
           public void boundX() {
             vx = -vx;
          }
           public void boundY() {
             vy = -vy;
          }
           public void boundXY() {
             vx = -vx;
             vy = -vy;
          }
          public int getX(){
             return x;
          }
          public int getY(){
             return y;
          }
          public int getVX() {
              return vx;
         }
          public int getVY() {
              return vy;
         }
          public void setVX(int v) {
           vx = v;
         }
          public void setVY(int v) {
           vy = v;
         } 
          //サイズを返す
          public int getSize() {
            return SIZE;
            }
         
          //画像のロード
          private void loadImage(){
          ImageIcon icon = new ImageIcon(getClass().getResource("image/ball.gif"));
          image = icon.getImage();
          
          width = image.getWidth(panel);
          height =image.getHeight(panel);
          
           }
        }

     
        
          
          
                            

        