package com.shashwat.barcodescanner.view;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.shashwat.barcodescanner.app.Activator;
import com.shashwat.barcodescanner.camera.CameraComp;

public class ViewPart extends org.eclipse.ui.part.ViewPart {
	public static final String ID = "com.shashwat.barcodescanner.app.view";
	
	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		CameraComp comp = startControl(parent);
		startScanning(comp);
	}

	private void startScanning(CameraComp comp) {
	}

	private CameraComp startControl(final Composite parent) {
		CameraComp comp = Activator.getDefault().getCameraComp();
		try {
			comp.init();
		} catch (CoreException e) {
			MessageDialog.openError(parent.getShell(), 
					"Error", 
					"Error in init : " + e.toString());
		}
		comp.initViewComposite(parent);
		return comp;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
