import java.io.*;

public class Question2 implements Serializable {

	public static void main(String[] args) throws Exception{
		
		Rectangle rectangle1 = new Rectangle();
		rectangle1.length = 15;
		rectangle1.width = 7;
		
		System.out.println(rectangle1.toString());
		
		// Serializing
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("q2output.txt"));
		
		oos.writeObject(rectangle1);
		oos.close();
		
		// Deserializing
		Rectangle rectangle2 = null;
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("q2output.txt"));
		
		rectangle2 = (Rectangle) ois.readObject();
		ois.close();
		System.out.println(rectangle2.toString());
	}

}

class Rectangle implements Serializable {
	static int length = 10;
	transient int width = 5; 
	
	public String toString() {
		return "Radius is : " + length + "\nDiameter is :" + width; 
	}
}
