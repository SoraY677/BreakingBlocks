/*Block class*/
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block {
       //���ƍ���
       public static final int WIDTH = 60;
       public static final int HEIGHT =30;
      
       //�{�[���̓����蔻��
       public static final int NO_COLLISION = 0;//���Փ�
       public static final int DOWN = 1;//��
       public static final int LEFT = 2;//��
       public static final int RIGHT = 3;//�E
       public static final int UP = 4;//��
       public static final int DOWN_LEFT = 5;//����
       public static final int DOWN_RIGHT = 6;//�E��
       public static final int UP_LEFT = 7;//����
       public static final int UP_RIGHT = 8;//�E��
       
       //�ʒu
       private int x,y;
      
       //�{�[��������������
       private boolean isDeleted;

       public Block(int x,int y) {
           this.x = x;
           this.y = y;
           isDeleted = false;
       }
       
       //block�̕`��
       public void draw(Graphics g){
            //�h��Ԃ��̐F�̓V�A��
            g.setColor(Color.CYAN);
            g.fillRect(x,y,WIDTH,HEIGHT);
            //�O��
            g.setColor(Color.BLACK);
            g.drawRect(x,y,WIDTH,HEIGHT);
        }
         
       //�{�[���Ƃ̏Փ˔��� 
       public int collideWith(Ball ball){
          Rectangle blockRect = new Rectangle(x,y,WIDTH,HEIGHT);
           
       int ballX = ball.getX();
       int ballY = ball.getY();
       int ballSize =ball.getSize();
       //ball���u���b�N�̉�����Փ� 
      if (blockRect.contains(ballX,ballY)
             && blockRect.contains(ballX + ballSize,ballY)){ 
             return DOWN;
       //ball���u���b�N�̍�����Փ�
      }else if (blockRect.contains(ballX + ballSize,ballY)
             && blockRect.contains(ballX + ballSize,ballY + ballSize)){
             return LEFT;
       //ball���u���b�N�̉E����̏Փ�
      }else if (blockRect.contains(ballX,ballY)
             && blockRect.contains(ballX,ballY + ballSize)) {
              return RIGHT;                
       //ball���u���b�N�̏ォ��̏Փ�
      }else if (blockRect.contains(ballX ,ballY + ballSize)
             && blockRect.contains(ballX + ballSize,ballY + ballSize)){
          return UP;
       //ball���u���b�N�̍�������̏Փ�
      }else if (blockRect.contains(ballX + ballSize, ballY)){
          return DOWN_LEFT;
       //ball���u���b�N�̉E������̏Փ�
      }else if(blockRect.contains(ballX , ballY)){
          return DOWN_RIGHT;
       //ball���u���b�N�̍��ォ��̏Փ�
      }else if(blockRect.contains(ballX +ballSize,ballY +ballSize)) {
                return UP_LEFT;
       //ball���u���b�N�̉E�ォ��Փ�
      }else if(blockRect.contains(ballX,ballY +ballSize)) {
           return UP_RIGHT; 
      }   
      //�������Ă��Ȃ�
      return NO_COLLISION;
      }


    //�u���b�N��ꂽ
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

















       
 