import java.io.File;

public class getParentFile {
   public static void main(String[] args) {      
      File f = null;
      File f1 = null;
      String v;
      boolean bool = false;
      
      try {
         // create new file
         f = new File("test.txt");
         
         // returns abstract parent pathname
         f1 = f.getParentFile();
         
         // absolute path from abstract pathname
         v = f1.getAbsolutePath();
         
         // true if the file path exists
         bool = f.exists();
         
         // if file exists
         if(bool) {
         
            // prints
            System.out.print("Parent file path: "+v);
         }
         
      } catch(Exception e) {
         // if any error occurs
         e.printStackTrace();
      }
   }
}