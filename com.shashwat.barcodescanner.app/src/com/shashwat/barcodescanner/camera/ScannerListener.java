package com.shashwat.barcodescanner.camera;

import java.util.EventListener;

public interface ScannerListener extends EventListener {
	public void resolved(ScanResult result);
}
