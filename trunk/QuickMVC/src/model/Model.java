package model;
/**
 * A Singleton Model; needs to be filled in with implementation!
 * @author krm
 *
 */
public class Model extends AbstractModel {

	private static Model model;
		
	private Model(){
	}
	
	/**
	 * Provides Singleton access to the Model
	 * @return the Model
	 */
	public static Model getModel(){
		if (model == null)
			model = new Model();
		
		return model;
	}
	
	
	/**
	 * toString is a useful testing function
	 */
	public String toString(){
		return "";
	}
	
	
}

