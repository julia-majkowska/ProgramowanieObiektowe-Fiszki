public class StopException extends Exception{
	public StopException() { super(); }
	public StopException(String m) { super(m); }
	public StopException(String m, Throwable c) { super(m, c); }
	public StopException(Throwable c) { super(c); }
}
