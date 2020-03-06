package poker;

public class EmptyDeckException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyDeckException(){
    }

    public EmptyDeckException(String message){
        super(message);
    }

    public EmptyDeckException(Throwable cause){
        super(cause);
    }

    public EmptyDeckException(String message, Throwable cause){
        super(message, cause);
    }
}