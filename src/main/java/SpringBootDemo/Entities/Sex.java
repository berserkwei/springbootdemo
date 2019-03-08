package SpringBootDemo.Entities;

public enum Sex {
	MALE(0), FEMALE(1);
	
	int value;
		
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	Sex(int value) {
		this.value = value;
	}
}
