 
package com.shashwat.nattable.app.view;

import javax.annotation.PostConstruct;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.data.IColumnPropertyAccessor;
import org.eclipse.nebula.widgets.nattable.data.IDataProvider;
import org.eclipse.nebula.widgets.nattable.data.ListDataProvider;
import org.eclipse.nebula.widgets.nattable.data.ReflectiveColumnPropertyAccessor;
import org.eclipse.nebula.widgets.nattable.layer.DataLayer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.shashwat.nattable.app.model.Person;
import com.shashwat.nattable.app.model.Service;

public class NattableExampleViewPart {
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout());
		String[] propertyNames = {"firstName", "lastName", "gender", "married", "birthday"};
		
		// data provider
		IColumnPropertyAccessor<Person> columnPropertyAccessor = new ReflectiveColumnPropertyAccessor<>(propertyNames);
		IDataProvider dataProvider = new ListDataProvider<>(Service.getPersons(10), columnPropertyAccessor);
		
		final DataLayer dataLayer = new DataLayer(dataProvider);
		final NatTable table = new NatTable(parent, SWT.NO_REDRAW_RESIZE | SWT.DOUBLE_BUFFERED | SWT.BORDER, dataLayer);
		GridDataFactory.fillDefaults().grab(true, true).align(SWT.FILL, SWT.FILL).applyTo(table);
	}
}