import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//Server side of java app
public class Server {

  public static void main(String[] args) throws IOException {
	//socket binded to port = 55555  
    ServerSocket serversock = new ServerSocket(55555);
    System.out.println("....Server Running ....");
    //sending file
    File myFile = new File("SendFile.txt");
    System.out.println("Sending file name : "+myFile.toString()+" File Size : "+myFile.length());
    
    while (true) {
    	//waiting for the connection to establish	
      Socket sock = serversock.accept();
      //bytearray to write the data of the file to bytearray using bufferinputstream
      byte[] bytearray = new byte[(int) myFile.length()];
      BufferedInputStream buf = new BufferedInputStream(new FileInputStream(myFile));
      buf.read(bytearray, 0, bytearray.length);
      
      OutputStream output = sock.getOutputStream();
      System.out.println(bytearray);
      //write the data to the output stream
      output.write(bytearray, 0, bytearray.length);
      output.flush();
      //closes after one connection
      sock.close();
      System.out.println(".....Server Closed ....");
      return;
    }
  }
}