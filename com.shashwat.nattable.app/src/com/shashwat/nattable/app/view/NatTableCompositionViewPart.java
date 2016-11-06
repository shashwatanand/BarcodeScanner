 
package com.shashwat.nattable.app.view;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class NatTableCompositionViewPart {
	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout());
		String[] propertyNames = {"firstName", "lastName", "gender", "married", "birthday"};
		
		// mapping from property to label, needed for column header labels
	    Map<String, String> propertyToHeaderMap = new HashMap<String, String>();
	    propertyToHeaderMap.put("firstName", "Firstname");
	    propertyToHeaderMap.put("lastName", "Lastname");
	    propertyToHeaderMap.put("gender", "Gender");
	    propertyToHeaderMap.put("married", "Married");
	    propertyToHeaderMap.put("birthday", "Birthday");
	}
}