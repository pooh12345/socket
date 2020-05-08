import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws Exception{
    	
        try {
            System.out.println("Server Start");
            String BasicBase64format = null;
            String message;
            String capitalize;
            String mes = null;
            int p = 8080;
            
            String ip = "127.0.0.1";
            String locString = "D:/TA";
            Scanner scanner = new Scanner(new File("server.config"));
            int h = 0;
			while (scanner.hasNextLine()) {
				//System.out.println(scanner.nextLine());
				h++;
				if(h == 1) {
				
					ip = scanner.nextLine();
				}else if (h == 2) {
				
					p = Integer.parseInt(scanner.nextLine());
				}else {
				locString = scanner.nextLine();
				}
			}
			scanner.close();
			//System.out.println(p + " " + ip + " " + locString);
			
            // socket() and bind() include in new ServerSocket(port)
            //ServerSocket serverSocket = new ServerSocket(8080, 1);
			
			File file = new File("D://TA//testFile1.txt");
	
			FileWriter writer = new FileWriter(file);
			
			
			 //Scanner sc = new Scanner(System.in);
           // ServerSocket serverSocket = new ServerSocket(p, 1);
            
            
           // Socket connectionSocket = serverSocket.accept();
           // Scanner scan = new Scanner(System.in);
            //scan.nextLine();
int c = 0;
boolean ch = false;
boolean check = false;
boolean alive = true;
Socket connectionSocket = null;

ServerSocket serverSocket = null;
serverSocket = new ServerSocket(p, 1);
            while(true) {
            	if(alive) {
            		System.out.println("waiting");
            		//ServerSocket serverSocket = new ServerSocket(p, 1);
            		//serverSocket = new ServerSocket(p, 1);
            	 connectionSocket = serverSocket.accept();
            	 
            	}
            	alive = false;
            	Calendar calendar = new GregorianCalendar();
           	 
            	int year       = calendar.get(Calendar.YEAR);
            	int month      = calendar.get(Calendar.MONTH); 
            	int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            	int m = month + 1;
            	
                LocalTime time = LocalTime.now();
                // Pattern 
                DateTimeFormatter pattern = DateTimeFormatter.ofPattern("hh:mm:ss a");
                //System.out.println("Before Accept");
               // Socket connectionSocket = serverSocket.accept();
                //System.out.println("After Accept");

                // This code below is used to read message from client
                InputStreamReader in = new InputStreamReader(connectionSocket.getInputStream());
                BufferedReader bf = new BufferedReader(in);
if(check) {
    message = bf.readLine().replaceAll("\\\\n","");
    String[] parts = message.split(":");
   // System.out.println("test" + mes + ":" + parts[0]);
    if(parts[0].equals(BasicBase64format)) {
    	System.out.println(year + "-" +  m + "-" + dayOfMonth + " " +  time.format(pattern) + "from " + mes + ": " + parts[1] );
        //System.out.println("from" + mes + ":" + parts[1]);
    	if(parts[1].equalsIgnoreCase("Quit")) {
    		writer.write(year + "-" +  m + "-" + dayOfMonth + " " +  time.format(pattern) + "from " + mes + ": " + "Client quit" );
    		writer.write(System.getProperty( "line.separator" ));
    		writer.flush();
    		//writer.close();
    		check = false;
    	    connectionSocket.close();
    	    alive = true;
    	    c=0;
    	    continue;
    		
    		
    		
    	}
    	writer.write(year + "-" +  m + "-" + dayOfMonth + " " +  time.format(pattern) + "from " + mes + ": " + parts[1] );
    	writer.write(System.getProperty( "line.separator" ));
    	writer.flush();
    	//writer.close();
    }else{

	if(parts[1].equalsIgnoreCase("Quit")) {
		writer.write(year + "-" +  m + "-" + dayOfMonth + " " +  time.format(pattern) + "from " + mes + ": " + "Invalid token – Client quit" );
		check = false;
		writer.write(System.getProperty( "line.separator" ));
		writer.flush();
	    connectionSocket.close();
	    alive = true;
	    c=0;
	    continue;
		//writer.close();
		
    	}
        System.out.println("from" + mes + ":" + "Invalid token");
        writer.write(year + "-" +  m + "-" + dayOfMonth + " " +  time.format(pattern) + "from " + mes + ": " + "Invalid token" );
        writer.write(System.getProperty( "line.separator" ));
        writer.flush();
       // writer.close();
    }

continue;

}
                 String mes1 = bf.readLine();
                String pass1 = bf.readLine();
                mes = mes1.replaceAll("\\\\n","");
                String pass = pass1.replaceAll("\\\\n",""); 
                System.out.println("user" + mes);
                System.out.println("pass" + pass);
                
ch = true;
               if(!pass.equals("0264")) {
            	   System.out.println("invalid password");
                    c++;
ch=false;
if(c >= 4) {
   // break;
	//serverSocket.close();
    connectionSocket.close();
    alive = true;
    c=0;
    continue;
  
}
                }
                //System.out.println("sucess");
                String user = mes + "." + pass;

                //System.out.println(user);
                 BasicBase64format
                        = Base64.getEncoder()
                        .encodeToString(user.getBytes());

               PrintWriter pr = new PrintWriter(connectionSocket.getOutputStream());

               // message = bf.readLine();
                //capitalize = message.toUpperCase();
if(ch) {
    pr.println(BasicBase64format);
    check = true;
}else{

    //pr.println("invalid password");
}
                pr.flush();

                //connectionSocket.close();
            }
        }catch (Exception e){
            throw e;
        }
        
    }
}
