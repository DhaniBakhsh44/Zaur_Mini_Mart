import java.awt.*;
import java.awt.print.*;
import java.util.*;
class PrinterDemo{
  public static void main(String arg[]) throws Exception {
    
     Book book = new Book ();

     PageFormat pageFormat=getPageFormat();
     Vector v=DBManager.getStudents();
      
     int totalNumOfStd=v.size();
     int perPage=46;
     int totalPages=totalNumOfStd/perPage;
     int remaingStudent=totalNumOfStd%perPage;

     int num=-1;
         
              
     if(totalPages>0){
  StudentBean bean[]=new StudentBean[perPage];            
        for(int k=1; k<=totalPages; k++){
            for(int i=0; i<bean.length; i++){
               bean[i]=(StudentBean)v.elementAt(i); 
            }
            book.append(new Document(bean),pageFormat); 
        }
     }
   if(remaingStudent>0){
      StudentBean bean[]=new StudentBean[remaingStudent];  
       for(int i=0; i<bean.length; i++){
                bean[i]=(StudentBean)v.elementAt(i); 
                book.append(new Document(bean),pageFormat); 
            }
              
     }


 //    Document doc1=new Document();
  //   Document doc2=new Document();
   //  book.append (doc1, pageFormat);
    // book.append (doc2, pageFormat);
     
     PrinterJob printJob=PrinterJob.getPrinterJob ();
     printJob.setPageable (book);
     
     if(printJob.printDialog()) 
	printJob.print();
  }//end main  


   private static PageFormat getPageFormat(){
     Paper paper=new Paper();


     paper.setSize(PageSetupInterface.PAPER_WIDTH,PageSetupInterface.PAPER_HEIGHT);

     paper.setImageableArea(PageSetupInterface.LEFT_MARGIN,PageSetupInterface.TOP_MARGIN,
                            paper.getWidth() -(PageSetupInterface.LEFT_MARGIN + PageSetupInterface.RIGHT_MARGIN),
                            paper.getHeight()-(PageSetupInterface.TOP_MARGIN + PageSetupInterface.BOTTOM_MARGIN));


     PageFormat pageFormat = new PageFormat ();
     pageFormat.setPaper(paper);

//     pageFormat.setOrientation (PageFormat.LANDSCAPE);
     pageFormat.setOrientation (PageFormat.PORTRAIT);

     return pageFormat;
 }
}//end class