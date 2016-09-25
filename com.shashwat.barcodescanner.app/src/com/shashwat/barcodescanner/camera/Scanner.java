package com.shashwat.barcodescanner.camera;

import java.awt.image.BufferedImage;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.GlobalHistogramBinarizer;

public class Scanner implements Runnable {
	private CameraComp control;
	private ScannerListener listener;
	
	public Scanner(CameraComp control) {
		this.control = control;
	}
	
	public void startScanning(ScannerListener listener) {
		this.listener = listener;
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		boolean flag = false;
		do {
			try {
				BufferedImage image;
				if ((image = this.control.getFrameImage()) == null) {
					continue;
				}
				LuminanceSource  luminanceSource  = new BufferedImageLuminanceSource(image);
				BinaryBitmap source = new BinaryBitmap(new GlobalHistogramBinarizer(luminanceSource));
				Result decode = new MultiFormatReader().decode(source);
				listener.resolved(new ScanResult(decode.getText()));
				break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (!flag);
	}
}
