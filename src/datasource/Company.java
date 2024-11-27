package datasource;

public class Company {
	
	private static final String secretCredentials = "WEARE24-1";
	private int tryCount = 0;
	private static Company dataCompany;
	
	private Company() {
		
	}

	public static Company getInstance () {
		if(dataCompany == null) {
			dataCompany = new Company();
		}
		return dataCompany;
	}
	
	public String isTryValid(String name) {
		if(this.tryCount == 3) {
			return " You have reach maximum try. Please contact the CEO to reset";
		}
		if(name.equals(Company.secretCredentials)) {
			return "Success";
		}
		this.tryCount++;
		return " Incorrect credentials. Please try again.\nRemaining try: " + (3 - this.tryCount);
	}
	
	public void resetTry() {
		this.tryCount = 0;
	}

}
