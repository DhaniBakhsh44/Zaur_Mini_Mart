import java.io.*;
//File class
class FileFoundDemo {
public static void main(String arg[])throws Exception{
File f=new File("C:/jdk1.8.0/bin/I O Stream/Abc.txt");

boolean found=f.exists();
String path=f.getAbsolutePath();
System.out.println(found);
System.out.println(path);
//boolean b=f.canExecute();
//System.out.println(b);
System.out.println("Use Compare Method");
    
int num=f.compareTo(new File("abc.txt"));
       System.out.println(num);
    
 System.out.println("canRead Method ");
     boolean b=f.canRead();
     System.out.println(b);
    
System.out.println("Can Write Method()");
boolean canWrite=f.canWrite();
System.out.println(canWrite);

System.out.println("Calling getName method");
String getName=f.getName();
System.out.println(getName);
    
System.out.println("Using  getParent() Method");
String getParent=f.getParent();
    System.out.println(getParent);
    
    System.out.println("Using getPath() Method");
    String getpath=f.getPath();
    System.out.println(getpath);
    
    
    boolean isAbsolute=f.isAbsolute();
    System.out.println(isAbsolute);
    
    boolean exists=f.exists();
    System.out.println(exists);
    
    
    
    
    
    
    
    

    
    
    
}
}