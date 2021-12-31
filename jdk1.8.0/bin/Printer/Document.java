import java.awt.print.*;
import java.awt.image.*;
import java.awt.*;
import java.util.*;

public class Document extends Component implements Printable{
//  private boolean b=false;

  StudentBean bean[];
  Document (StudentBean bean[]){
    this.bean=bean;

  }
  
  public int print (Graphics g, PageFormat pageFormat, int page) {

//   if(!b){
//     b=true;
//     return this.PAGE_EXISTS;
//   }

   try{
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate (pageFormat.getImageableX (), pageFormat.getImageableY ());
        g2d.setPaint (Color.black);

        int x=(int)pageFormat.getImageableX();
        int y=(int)pageFormat.getImageableY();

        int width=(int) pageFormat.getImageableWidth();
        int height=(int)pageFormat.getImageableHeight();

//        g2d.drawRect(x-4,y-4,width-(x*2-4),height-(y*4));
//      g2d.drawLine(width/2-(x/2),y-4,width/2-(x/2),height-(y*3+4));

//        g2d.drawRect(x,y,width-x*2,height-y*2);
       g2d.drawRect(10,10,width-20,height-20);

//  Font   font = new Font ("Old English Text MT", Font.PLAIN, 35);
// g2d.setFont(font);
 FontMetrics  fontMetrics = g2d.getFontMetrics ();
 //for(int k=1; k<=46; k++){
 Vector v=DBManager.getStudents();
 StudentBean bean=null;
 for(int i=0; i<v.size(); i++){
  bean=(StudentBean)v.elementAt(i);
  int batchId=bean.getBatchId();
  int stdId=bean.getStdId();
  String stdName=bean.getStdName();
  String fname=bean.getFName();
  String rollNo=bean.getRollNo();

  String add="  "+batchId+"  "+stdId+"  "+stdName+"  "+fname+" "+rollNo;
 System.out.println(add);


  g2d.drawString(add,x,y);
  y+=fontMetrics.getHeight();
//}
  //g2d.drawString(" \n",x,y);

}

//y+=fontMetrics.getHeight()*0.4;
   }catch(Exception ee){
      ee.printStackTrace();
      javax.swing.JOptionPane.showMessageDialog(null,"Error: "+ee.getMessage());
   }
//   b=false;
   return this.PAGE_EXISTS;
 
 }//end method

}//end class