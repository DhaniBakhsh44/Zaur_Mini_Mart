import java.util.*;
import java.io.*;
class RandomAccessRead{
    public static void main(String arg[])throws Exception{
        Scanner ob=new Scanner(System.in);
    RandomAccessFile f=new    RandomAccessFile("Abc.txt","r");
        
        System.out.println("Which Record You want to Access ,Enter Record No? ");
        int recordNo=ob.nextInt();
        f.seek(0);
       f.skipBytes((recordNo-1)*13);
        int a=f.readInt();
        long b=f.readLong();
        boolean c=f.readBoolean();
        System.out.println("Int "+a);
        System.out.println("Long "+b);
        System.out.println("Boolean "+c);
        
        f.close();
        
    }

}