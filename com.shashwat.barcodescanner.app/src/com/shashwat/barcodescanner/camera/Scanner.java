package com.shashwat.barcodescanner.camera;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
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
				MultiFormatReader reader = new MultiFormatReader();
				Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
		        hints.put(DecodeHintType.TRY_HARDER, BarcodeFormat.CODE_39);
		        reader.setHints(hints);
				Result decode = reader.decodeWithState(source);
				BarcodeFormat barcodeFormat = decode.getBarcodeFormat();
				if(barcodeFormat.compareTo(BarcodeFormat.QR_CODE) != 0) {
					System.out.println("Barcode found : " + decode.getText());
				} else {					
					listener.resolved(new ScanResult(decode.getText()));
				}
				flag = true;
				break;
			} catch (Exception e) {
				e.printStackTrace();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		} while (!flag);
	}
}
