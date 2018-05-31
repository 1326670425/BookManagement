import java.awt.Container;  
import java.io.*;  
  
import javax.swing.*;  
  
public class Test{  
    public static void main(String[] temp) {  
        byte b[] = new byte[2];  
        try{  
            FileInputStream fis = new FileInputStream("G://test.txt");  
            JFrame jf=new JFrame();  
            jf.setLayout(null);  
            Container container=jf.getContentPane();  
              
            JTextArea jta=new JTextArea();  
            JScrollPane jsp=new JScrollPane(jta);  
            jsp.setBounds(0,0, 1200,300);  
              
            container.add(jsp);  
            jf.setTitle("test");  
            jf.setVisible(true);  
            jf.setSize(1300,800);  
            jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
            ProgressMonitorInputStream in = new ProgressMonitorInputStream(jf,"读取文件",fis);  
            while(in.read(b)!=-1){  
                String s = new String(b);  
                jta.append(s);  
                Thread.sleep(300);//设置读取速度，100毫秒一次  
            }  
            in.close();
         }catch (Exception e) {  
            e.printStackTrace();  
         }  
    }  
} 