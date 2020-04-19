/* BBフレームの作成 */

import java.awt.Container;

import javax.swing.JFrame;

public class BB extends JFrame {
        public BB() {
           setTitle ("暇つぶしゲーム①～ブロック崩し～");//タイトル
           setResizable(false);            //サイズの変更は不可
        //メインパネルの作成 
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