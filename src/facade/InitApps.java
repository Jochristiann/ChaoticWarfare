package facade;

import database.Connect;
import datasource.Data;

public class InitApps {
	
	public static void initApps() {
		if(Connect.getInstance() != null ) {
			Data.migrateFreshData();
		}
	}
	
	
}
