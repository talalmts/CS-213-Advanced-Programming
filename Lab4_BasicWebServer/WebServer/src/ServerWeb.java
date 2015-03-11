import java.io.*;
import java.net.*;
import java.util.*;

 public class ServerWeb extends Thread {
 static final String Title =
         "<html>" +
         "<title>Web Server -- Lab-04</title>" +
         "<body>";
 
 static final String Closing_tags =
         "</body>" +
         "</html>";
 
 Socket sock = null;
 BufferedReader inputstream = null;
 DataOutputStream toClient = null;
 
 public ServerWeb(Socket client) {
     sock = client;
 }    
 
 public void run() {
   String Readline = null, filename = null;
   PrintWriter output = null;
   try {
     System.out.println( "Chrome Browser is connected on "+
     sock.getInetAddress() + ":" + sock.getPort());
 
     inputstream = new BufferedReader(new InputStreamReader (sock.getInputStream()));          
     toClient = new DataOutputStream(sock.getOutputStream());
     Readline = inputstream.readLine();
     if(Readline == null){
    	 return;
     }
     String Line = Readline;       
     StringBuffer Responsebuf = new StringBuffer();
     StringTokenizer token = new StringTokenizer(Line);
     String typerequest = token.nextToken();
     String httprequest = token.nextToken();
     System.out.println(Readline);
     
     if (typerequest.equals("GET")) {
    	 GetRequest(httprequest, Responsebuf );
         
     }
     else if (typerequest.equals("HEAD")) {
			if (httprequest.equals("/")) {
				// The default home page
				ResponseFunt(200, Responsebuf.toString(), false);
			} else {
				//This is interpreted as a file name
				String fileName = httprequest.replaceFirst("/", "");
				fileName = URLDecoder.decode(fileName);
				if (new File(fileName).isFile()){
					ResponseFunt(200, fileName, true);
				}
				else {
					ResponseFunt(404, "<b>Not Found </b>", false);
				}
			}
     }
     else { //POST request
    	 GetPOST( Readline, filename, output );
     }//else
    } catch (Exception e) {
          e.printStackTrace();
    }
  }
 
 
 public void GetRequest(String httprequest, StringBuffer responsebuf ) throws Exception{
	 System.out.println("GET request");
		if (httprequest.equals("/")) {
			ResponseFunt(200, responsebuf.toString(), false);
		} else {
			String fileName = httprequest.replaceFirst("/", "");
			fileName = URLDecoder.decode(fileName);
			if (new File(fileName).isFile()){
				ResponseFunt(200, fileName, true);
			}
			else {
				ResponseFunt(404, "<b>ERROR : 404 NOT FOUND </b>", false);
			}
		}
	 
 }
 
 public void GetHEAD(){
	 
 }
 
 public void GetPOST(String Readline,String filename,PrintWriter output ) throws Exception{
	 
     System.out.println("POST request");
     do {
         Readline = inputstream.readLine();
                             
         if (Readline.indexOf("Content-Type: multipart/form-data") != -1) {
           String boundary = Readline.split("boundary=")[1];
           while (true) {
               Readline = inputstream.readLine();
               if (Readline.indexOf("--" + boundary) != -1) {
                   filename = inputstream.readLine().split("filename=")[1].replaceAll("\"", "");                                
                   String [] filelist = filename.split("\\" + System.getProperty("file.separator"));
                   filename = filelist[filelist.length - 1];                  
                   System.out.println("File is being uploaded using POST filename = " + filename);
                   break;
                }              
            }
    
            String Filetype = inputstream.readLine().split(" ")[1];
            System.out.println("File content type = " + Filetype);
    
            inputstream.readLine();
            output = new PrintWriter(filename);
            String prevLine = inputstream.readLine();
            Readline = inputstream.readLine();      
    
            //Here we upload the actual file contents
            while (true) {
                if (Readline.equals("--" + boundary + "--")) {
                    output.print(prevLine);
                    break;
                }
                else {
                    output.println(prevLine);
                }
                prevLine = Readline;              
                Readline = inputstream.readLine();
            }
    
            ResponseFunt(200, "File is being uploaded using POST" + filename + " Done..", false);
            output.close();           
          } //if                                              
      }while (inputstream.ready()); //End of do-while
    }//else
	 

 
  public void ResponseFunt (int statusCode, String responseString, boolean isFile) throws Exception {
      String statusLine = null;
      String serverdetails = "Server: Java HTTPServer";
      String contentLengthLine = null;
      String fileName = null;
      String contentTypeLine = "Content-Type: text/html" + "\r\n";
      FileInputStream fin = null;
      if (statusCode == 200)
          statusLine = "HTTP/1.1 200 OK" + "\r\n";
      else
          statusLine = "HTTP/1.1 404 Not Found" + "\r\n";
  
      if (isFile) {
          fileName = responseString;    
          fin = new FileInputStream(fileName);
          contentLengthLine = "Content-Length: " + Integer.toString(fin.available()) + "\r\n";
          if (!fileName.endsWith(".htm") && !fileName.endsWith(".html"))
              contentTypeLine = "Content-Type: \r\n";
      }                
      else {
          responseString = ServerWeb.Title + responseString + ServerWeb.Closing_tags;
          contentLengthLine = "Content-Length: " + responseString.length() + "\r\n";
      }    
      toClient.writeBytes(statusLine);
      toClient.writeBytes(serverdetails);
      toClient.writeBytes(contentTypeLine);
      toClient.writeBytes(contentLengthLine);
      toClient.writeBytes("Connection: close\r\n");
      toClient.writeBytes("\r\n");
      if (isFile) File_Send(fin, toClient);
      else toClient.writeBytes(responseString);
      toClient.close();
  }
  public void File_Send (FileInputStream fin, DataOutputStream out) throws Exception {
      byte[] buffer = new byte[1024] ;
      int bytesRead;
    while ((bytesRead = fin.read(buffer)) != -1 ) {
        out.write(buffer, 0, bytesRead);
        }
         fin.close();
     }
     
     public static void main (String args[]) throws Exception {
    	 int port = 5001;
         ServerSocket Server = new ServerSocket (port, 10, InetAddress.getByName("127.0.0.1")); 
         System.out.println ("My Web Server Waiting for Client on port 127.0.0.1:"+port) ;
                         
         while(true) {                                 
                 Socket connected = Server.accept();
                 (new ServerWeb(connected)).start();
         }
     }
 }
