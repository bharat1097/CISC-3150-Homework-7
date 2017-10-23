
public class IllegalOperationException  extends IllegalArgumentException {
	public IllegalOperationException() {
		super("Unsupported operator entered. Supported operators are: ( ), +, -, /, *, and %");
	}
}