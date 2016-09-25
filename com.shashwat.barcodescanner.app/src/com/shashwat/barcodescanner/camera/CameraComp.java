package com.shashwat.barcodescanner.camera;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.image.BufferedImage;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.shashwat.barcodescanner.app.Activator;

public class CameraComp {
	private Webcam webcam = null;
	private Composite composite;
	
	public CameraComp() {
		super();
	}
	
	public void init() throws CoreException {
		try {
			Dimension size = WebcamResolution.QVGA.getSize();
			this.webcam = Webcam.getDefault();
			this.webcam.setViewSize(size);			
		} catch (Exception e) {
			throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, 
					"Error in init"));
		}
	}
	
	public void destroyComposite() {
		if (this.composite != null && !this.composite.isDisposed()) {
			this.composite.dispose();
		}
	}
	
	public BufferedImage getFrameImage() {
		if (this.webcam != null && this.webcam.isOpen()) {
			return this.webcam.getImage();
		}
		return null;
	}
	
	public Composite initViewComposite(Composite parent) {
		Assert.isNotNull(this.webcam, "Can't init view without a webcam");
		destroyComposite();
		this.composite = new Composite(parent, SWT.EMBEDDED);
		WebcamPanel panel = new WebcamPanel(webcam);
		panel.setPreferredSize(WebcamResolution.QVGA.getSize());
		
		Frame frame = SWT_AWT.new_Frame(this.composite);
		frame.add(panel);
		parent.layout(true);
		return parent;
	}
}
