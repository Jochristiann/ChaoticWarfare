package main;

import controller.AppController;
import facade.InitApps;


public class Main {

	public Main() {
		AppController apps = new AppController();
		InitApps.initApps();
		apps.start();
	}

	public static void main(String[] args) {
		new Main();
	}

}
