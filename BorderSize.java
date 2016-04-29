package quiz;

public enum BorderSize {
	
	HEIGHT(800), WIDTH(600);
	
	private int value;
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	private BorderSize(int value){
		this.value = value;
	}
	
	

}
