import java.util.*;
import java.io.*;

public class Crawler {
	
	ArrayList<MyFile> list = new ArrayList<MyFile>();
	threadHandler Workerque;
	static int i = 0;
	
	String ReadFile(String Path) throws IOException{
		 BufferedReader BufReader = new BufferedReader(new FileReader(Path));
		 String FileContent;
		    try {
		        StringBuilder Stringbuilder = new StringBuilder();
		        String ReadLine = null;
				try {
					ReadLine = BufReader.readLine();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		        while (ReadLine != null) {
		            Stringbuilder.append(ReadLine);
		            Stringbuilder.append(System.lineSeparator());
		            try {
						ReadLine = BufReader.readLine();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		         FileContent = Stringbuilder.toString();
		        //System.out.println(FileContent);
		    } finally {
		        try {
					BufReader.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    return FileContent; 
	}
	
	int Search(String value){
		int status =0;
		System.out.println("--- Searching  \""+value+"\" ----");
		for(int i =0 ; i<list.size(); i++){
			if(list.get(i) ==null){
				continue;
			}
			if(list.get(i).name.contains(value)){
				System.out.println("----Content Found----");
				System.out.println("File Found : "+ list.get(i).name);
				System.out.println("File Path : "+ list.get(i).Path);
				System.out.println("File Content : "+ list.get(i).Content);
				System.out.println("---------------------");
				status =1;
				
			}
			if(list.get(i).Content.contains(value)){
				System.out.println("----Content Found----");
				System.out.println("File Found : "+ list.get(i).name);
				System.out.println("File Path : "+ list.get(i).Path);
				System.out.println("File Content : "+ list.get(i).Content);
				System.out.println("---------------------");
				status =1;	
			}
		//System.out.println("T = "+status);
		}

		System.out.println("--- Search END ----");	
		return status;
	}

	private class WorkerThread implements Runnable {

		private threadHandler queue;

		public WorkerThread(threadHandler que) {
			queue = que;
		}


		public void run() {
			String name;
			while ((name = queue.remove()) != null) {
				File file = new File(name);
				String DirContent[] = file.list();
				if (DirContent == null)
					continue;
				for (String FileName : DirContent) {
					String FilePath = name + "\\" + FileName;
					//System.out.println("FileName :"+FileName);
					//System.out.println(FilePath);
					
					//Read from the .txt File
					if(FileName.contains("txt") || FileName.contains("java") || FileName.contains("html")){
						String FileContent ="";
						//System.out.println("txt :" + FileName);
						try {
							FileContent = ReadFile(FilePath);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						list.add( new MyFile(FileName,FilePath,FileContent) );
					}
					//Add the content of the file to the ArrayList 
					else{
						String FileContent ="";
						list.add( new MyFile(FileName,FilePath,FileContent)  );
					}
					
				}
			}
		}
	}

	public Crawler() {
		Workerque = new threadHandler();
	}


	public WorkerThread createWorker() {
		return new WorkerThread(Workerque);
	}


	// need try ... catch below in case the directory is not legal

	public void CrawlDir(String dir) {
		try{
			File file = new File(dir);
			if (file.isDirectory()) {
				String DirContent[] = file.list();
				if (DirContent != null)
					Workerque.add(dir);

				for (String FileName : DirContent) {
					String subdir;
					if (dir.endsWith("\\"))
						subdir = dir+FileName;
					else
						subdir = dir+"\\"+FileName;
					CrawlDir(subdir);
				}
			}}catch(Exception e){}
	}

}
