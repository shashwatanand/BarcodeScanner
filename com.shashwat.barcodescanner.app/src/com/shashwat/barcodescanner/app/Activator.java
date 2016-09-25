package com.shashwat.barcodescanner.app;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.shashwat.barcodescanner.camera.CameraComp;

public class Activator extends AbstractUIPlugin {
	// The plug-in ID
	public static final String PLUGIN_ID = "com.shashwat.barcodescanner";

	// The shared instance
	private static Activator plugin;
	private CameraComp control;
	
	public Activator() {
	}
	
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		this.control = new CameraComp();
	}
	
	@Override
	public void stop(BundleContext context) throws Exception {
		this.control = null;
		plugin = null;
		super.stop(context);
	}
	
	public static Activator getDefault() {
		return plugin;
	}
	
	public CameraComp getCameraComp() {
		return this.control;
	}
	
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
