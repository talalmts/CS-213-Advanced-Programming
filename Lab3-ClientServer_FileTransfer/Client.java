import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.net.Socket;
import java.io.File;

//client side of the java app
public class Client {

//function to the client
  long ClientSide(int port){
    long size =0;
    //exception hanlding
    try{
        Socket sock = new Socket("127.0.0.1", port);
        byte[] bytearray = new byte[1024];
        InputStream input = sock.getInputStream();
        FileOutputStream fileoutput = new FileOutputStream("TransferFile_JAVA.txt");
        BufferedOutputStream buffer = new BufferedOutputStream(fileoutput);
        int read = input.read(bytearray, 0, bytearray.length);
        buffer.write(bytearray, 0, read);
        buffer.close();
        sock.close();
        System.out.println("");
        File f = new File("TransferFile_JAVA.txt");
        size = f.length();
       



    }catch (IOException e){
  
}
    return size;

  }

  //unittest to check the completeness of the code
  void UnitTest(int port){
        System.out.println("---File Sending from Server to Client---");
        System.out.println("---Unit Test for File Size---");
        long size = ClientSide(port);

        if(size == 23){
         System.out.println("File Size is: "+ size);
         System.out.println("----TEST SUCESSFULL-----");
        }
        else{
                 System.out.println("---TEST FAILED---");
    
         }


    }

  




  public static void main(String[] argv) throws Exception {
        Client C = new Client();
        //JAVA Server
        C.UnitTest(55556);

        //C SERVER
        //C.UnitTest(55556);

  }


}

