

public class MyFile {

	String name = null;
	String Path = null;
	String Content = null;



	MyFile( String name,String Path, String Content){
		//this.index = index;
		this.name =name;
		this.Path =Path;
		this.Content = Content;
	}


	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getPath() {
		return Path;
	}




	public void setPath(String path) {
		Path = path;
	}




	public String getContent() {
		return Content;
	}




	public void setContent(String content) {
		Content = content;
	}




	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
