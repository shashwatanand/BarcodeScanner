package com.shashwat.barcodescanner.view;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import com.shashwat.barcodescanner.app.Activator;
import com.shashwat.barcodescanner.camera.CameraComp;
import com.shashwat.barcodescanner.camera.ScanResult;
import com.shashwat.barcodescanner.camera.Scanner;
import com.shashwat.barcodescanner.camera.ScannerListener;

public class ViewPart extends org.eclipse.ui.part.ViewPart {
	public static final String ID = "com.shashwat.barcodescanner.app.view";
	private Scanner scanner;
	private Composite parentComp;

	@Override
	public void createPartControl(Composite parent) {
		this.parentComp = parent;
		parent.setLayout(new FillLayout());
		CameraComp comp = startControl(parent);
		startScanning(comp);
	}

	private void startScanning(CameraComp comp) {
		if (this.scanner == null) {
			this.scanner = new Scanner(comp);

			this.scanner.startScanning(new ScannerListener() {

				@Override
				public void resolved(final ScanResult result) {
					getViewSite().getShell().getDisplay().syncExec(new Runnable() {

						@Override
						public void run() {
							recieved(result);
						}
					});
				}
			});
		}
	}

	private void recieved(ScanResult result) {
		CameraComp control = Activator.getDefault().getCameraComp();
		control.destroyComposite();
		Display display = this.parentComp.getDisplay();
		display.beep();
		/*Clipboard cb = new Clipboard(display);
		TextTransfer transfer = TextTransfer.getInstance();
		cb.setContents(new Object[] { result.getText() }, new Transfer[] { transfer });
		cb.dispose();*/
		Browser browser = new Browser(this.parentComp, SWT.NONE);
		browser.setUrl(result.getText());
		this.parentComp.getShell().setMaximized(true);
		this.parentComp.layout(true);
	}

	private CameraComp startControl(final Composite parent) {
		CameraComp comp = Activator.getDefault().getCameraComp();
		try {
			comp.init();
		} catch (CoreException e) {
			MessageDialog.openError(parent.getShell(), "Error", "Error in init : " + e.toString());
		}
		comp.initViewComposite(parent);
		return comp;
	}

	@Override
	public void setFocus() {
	}
}
