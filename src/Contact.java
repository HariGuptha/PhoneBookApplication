import java.util.ArrayList;

public class Contact {

	private String name;
	private ArrayList<String> mobile = new ArrayList<String>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getMobile() {
		return mobile;
	}

	public void setMobile(String number) {
		mobile.add(number);
	}

}
