
public class DumbassException extends IllegalArgumentException {
	public DumbassException() {
		super("You didn't enter anything, please run the program again with a valid input.");
	}
}