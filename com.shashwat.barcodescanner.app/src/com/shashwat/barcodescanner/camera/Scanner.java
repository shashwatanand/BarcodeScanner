package com.shashwat.barcodescanner.camera;

public class Scanner implements Runnable {
	private CameraComp control;
	
	public Scanner(CameraComp control) {
		this.control = control;
	}
	
	public void startScanning() {
		
	}
	
	@Override
	public void run() {
		boolean flag = false;
		do {
			try {
				new BufferedImageMonochromeBitmapSource()
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (!flag);
	}
}
