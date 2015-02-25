package pk.com.lab2;

public class Variable {
	
	String name;
	int value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	Variable (String a,int v){
		this.name = a;
		this.value = v;
		
	}
	void print(){
		System.out.println(name + " = "+ value);
		
	}
	
}
