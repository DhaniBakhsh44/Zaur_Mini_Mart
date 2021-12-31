import java.util.*;
import java.io.*;
class RandomAccessWrite{
    public static void main(String arg[])throws Exception{
        Scanner ob=new Scanner(System.in);
        RandomAccessFile f = new RandomAccessFile("Abc.txt","rw");
    char ch;
        do{
            System.out.println("Enter Int Value ?");
            int a=ob.nextInt();
            System.out.println("Enter Long Value ?");
            int b=ob.nextInt();
            System.out.println("Enter Boolean value ?");
            boolean c=ob.nextBoolean();
        
            f.writeInt(a);
            f.writeLong(b);
            f.writeBoolean(c);
            System.out.println("Do You want to Save more Records ? Y/N");
            ch=ob.next().charAt(0);
            
            
        }while(ch=='y' || ch=='Y');
        f.close();
    }
}