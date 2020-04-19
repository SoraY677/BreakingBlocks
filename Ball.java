/*Ball Class*/
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Ball{
       
        public static final int SIZE = 8; //�T�C�Y
        private int x,y;//�ʒu
        private int vx,vy;//���x
        private Image image;//ball�̉摜
        //ball�̕��ƍ���
        private int width;
        private int height;
       
        private MainPanel panel; //MainPanel�̎Q��

   public Ball(MainPanel panel){
          //�ʒu�̏�����
          x = 0;
          y = 290;
          panel = panel;
          
          //���x�̏�����
          vx = 15;
          vy = 15;
         //�C���[�W�̃��[�h
          loadImage();
         }
         
         public void draw(Graphics g){          
                  g.drawImage(image,x,y,null);
          }
          
          //ball�̈ړ�
          public void move() {
             x += vx;
             y += vy;
          
         
          //�o�E���h
          //���E�̕ǂ̏ꍇ
          if(x < 0 || x >MainPanel.WIDTH -SIZE){
            boundX();
            }
          //��̕ǂ̏ꍇ
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
          //�T�C�Y��Ԃ�
          public int getSize() {
            return SIZE;
            }
         
          //�摜�̃��[�h
          private void loadImage(){
          ImageIcon icon = new ImageIcon(getClass().getResource("image/ball.gif"));
          image = icon.getImage();
          
          width = image.getWidth(panel);
          height =image.getHeight(panel);
          
           }
        }

     
        
          
          
                            

        