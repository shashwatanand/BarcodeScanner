package com.shashwat.barcodescanner.app;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.shashwat.barcodescanner.view.ViewPart;

public class Perspective implements IPerspectiveFactory {

	@Override	
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);
		
		layout.addStandaloneView(ViewPart.ID, false, IPageLayout.LEFT, 1.0f, editorArea);
	}
}
