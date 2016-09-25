package com.shashwat.barcodescanner.camera;

import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.media.Buffer;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.control.FormatControl;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.protocol.DataSource;
import javax.media.util.BufferToImage;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;

import com.shashwat.barcodescanner.app.Activator;

public class CameraComp {
	private Composite composite;
	private Player player;
	private FrameGrabbingControl frameGrabbingControl;
	
	public CameraComp() {
		super();
	}
	
	public void init() throws CoreException {
		MediaLocator locator = new MediaLocator("vfw://0");
		try {
			DataSource source = Manager.createDataSource(locator);
			this.player = Manager.createPlayer(source);
			this.frameGrabbingControl = (FrameGrabbingControl) player.getControl("javax.media.control.FrameGrabbingControl");
			FormatControl formatControl = (FormatControl) player.getControl ( "javax.media.control.FormatControl" );
		} catch (Exception e) {
			throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, 
					"Error in init"));
		}
	}
	
	private void destroyComposite() {
		if (this.composite != null && !this.composite.isDisposed()) {
			this.composite.dispose();
		}
	}
	
	public BufferedImage getFrameImage() {
		Buffer frame = this.frameGrabbingControl.grabFrame();
		VideoFormat format = (VideoFormat)frame.getFormat();
		BufferToImage bufferToImage = new BufferToImage(format);
		Image img = bufferToImage.createImage(frame);
		BufferedImage bufferedImage = new BufferedImage(format.getSize().width, format.getSize().height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bufferedImage.createGraphics();
		g.drawImage(img, null, null);
		return bufferedImage;
	}
	
	public Composite initViewComposite(Composite parent) {
		Assert.isNotNull(this.player, "Can't init view without a player");
		destroyComposite();
		this.composite = new Composite(parent, SWT.NONE);
		Component visualComponent = this.player.getVisualComponent();
		Frame frame = SWT_AWT.new_Frame(this.composite);
		frame.add(visualComponent);
		parent.layout(true);
		player.start();
		return parent;
	}
	
	public void disposePlayer() {
		this.player.stop();
		this.player.deallocate();
		this.player = null;
	}
}
