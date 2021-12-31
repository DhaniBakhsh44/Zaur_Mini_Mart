import java.io.*;
class CreatsFile {
public static void main(String arg[])throws Exception{

File file=null;

for(int i=1; i<=100; i++){
file=new File("text"+1+".txt");
file.createNewFile();

System.out.println(" "+i);

}

}
}