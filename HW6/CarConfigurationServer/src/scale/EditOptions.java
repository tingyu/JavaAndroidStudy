package scale;


/** EditOptions implements Multithreading  */

public class EditOptions extends Thread {
	/** Field Description of EditOptions */
	private EditThread editAutoInterf;
	private String modelName;
	private String optsetName;
	private String optName;
	private float newPrice;
	private String newOptsetName;
	private int count = 6;
	private int op;

	 
	/** Constructor Description of EditOptions(), Pass an  */
	public EditOptions(String threadName, int operation, EditThread thread) {
		super(threadName);
		this.op = operation;
		this.editAutoInterf = thread;
	}

	/** Enable Pass parameters edit Option Price from Driver */
	public void EditOptionPrice(String modelName, String optsetName,
			String optName, float newPrice) {
		this.modelName = modelName;
		this.optsetName = optsetName;
		this.optName = optName;
		this.newPrice = newPrice;
	}

	/** Enable Pass parameters to edit EditOptionSetName from Driver */
	public void EditOptionSetName(String modelName, String optsetName,
			String newOptsetName) {
		this.modelName = modelName;
		this.optsetName = optsetName;
		this.newOptsetName = newOptsetName;
	}

	/** Sleep for some seconds */
	void randomWait() {
		try {
			Thread.currentThread();
			Thread.sleep((long) (3000 * Math.random()));
		} catch (InterruptedException e) {
			System.out.println("Interrupted!");
		}
	}

	/** Run threads in EditOptions() */
	public void run() {
		if(editAutoInterf!= null){
		//Object locking is implemented here and in Automobile Class
		synchronized (editAutoInterf) {
			for (int i = 0; i < 6; i++) {
				//Make threads to sleep for some seconds
				randomWait();
				
				//Get the Name of the current thread
				System.out.println(getName());
				
				//Print the Counter to show the synchronized block function 
				System.out.println("Counter:" + count);
				
				//choose the modify function according to the input
				switch (op) {
				case 0:
					editAutoInterf.EditOptionPrice(modelName, optsetName,
							optName, newPrice);
					break;
				case 1:
					editAutoInterf.EditOptionSetName(modelName, optsetName,
							newOptsetName);
					break;
				}
				count--;
			}
		}
		}
	}


}
