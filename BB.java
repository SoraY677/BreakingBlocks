/* BB�t���[���̍쐬 */

import java.awt.Container;

import javax.swing.JFrame;

public class BB extends JFrame {
        public BB() {
           setTitle ("�ɂԂ��Q�[���@�`�u���b�N�����`");//�^�C�g��
           setResizable(false);            //�T�C�Y�̕ύX�͕s��
        //���C���p�l���̍쐬 
        MainPanel panel = new MainPanel();
        Container contentPane = getContentPane();
        contentPane.add(panel);
        
        pack();
      }
     
    public static void main(String[] args){
        BB frame = new BB();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
      }
  }               