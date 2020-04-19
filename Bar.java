/*Bar java*/
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Bar{
     //bar�̈ړ�  
     private static final int LEFT = 0; //��
     private static final int RIGHT = 1;//�E
     //bar�̓�����
     public static final int NO_COLLISION = 0;//�������ĂȂ�
     public static final int HIT_LEFT = 1;//bar�̍����ɓ�������
     public static final int HIT_RIGHT = 2;//bar�̉E���ɓ�������

     private static final int SPEED = 10;//����
     private int x;//bar��x�ʒu
     private int y;//bar�̂��ʒu
     public static int width;//bar�̍���
     public static int height;//bar�̉���
     private Image image;//bar�̉摜

     //Mainpanel�̎Q�� 
     private MainPanel panel;
     public Bar(int x, int y,MainPanel panel){
                  this.x = x;
                  this.y = y;
                  this.panel = panel;

                  loadImage();//�C���[�W�̃��[�h
      }

      //�ړ��R�}���h�Ɖ�ʊO����
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
      
     
      //bar�̕`��
      public void draw(Graphics g) {
             g.drawImage(image,x,y,null);
      }
      //bar�̈ʒu
        public Point getPos(){
           return new Point(x,y);
      }
      //bar�̕�
      public int getWidth(){
           return width;
      }
      //bar�̍���
      public int getHeight(){
      return height;
      }
      //�C���[�W�̓ǂݍ���
       private void loadImage(){
        ImageIcon icon = new ImageIcon(getClass().
         getResource("image/bar.gif"));
        image = icon.getImage();
      //���ƍ����̃Z�b�g
      width =image.getWidth(panel);
      height = image.getHeight(panel);
     }
      
    //�����蔻��̐ݒ�
    public int collideWith(Ball ball) {
      //bar�̓����蔻��͈̔�
        Rectangle barRectLeft = new Rectangle(x, y,
                     width / 2,height);
        Rectangle barRectRight = new Rectangle(x + width /2, y,
                     width / 2, height);
      //ball�̓����蔻��͈̔�
          Rectangle ballRect = new Rectangle(
            ball.getX(),ball.getY(),ball.getSize(),ball.getSize());
     //�d�Ȃ����瓖�����Ă���
        if (barRectLeft.intersects(ballRect)) {
         return HIT_LEFT;
  }else if (barRectRight.intersects(ballRect)){
         return HIT_RIGHT;
        }
         return NO_COLLISION;
       }
   }


 
            
                  
     