
package com.shashwat.nattable.app.view;

import javax.annotation.PostConstruct;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.data.IColumnPropertyAccessor;
import org.eclipse.nebula.widgets.nattable.data.IDataProvider;
import org.eclipse.nebula.widgets.nattable.data.ListDataProvider;
import org.eclipse.nebula.widgets.nattable.data.ReflectiveColumnPropertyAccessor;
import org.eclipse.nebula.widgets.nattable.grid.GridRegion;
import org.eclipse.nebula.widgets.nattable.layer.DataLayer;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer;
import org.eclipse.nebula.widgets.nattable.viewport.ViewportLayer;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.shashwat.nattable.app.model.Person;
import com.shashwat.nattable.app.model.Service;

public class NatTableLayerViewPart {
	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout());
		String[] propertyNames = { "firstName", "lastName", "gender", "married", "birthday" };

		// data provider
		IColumnPropertyAccessor<Person> columnPropertyAccessor = new ReflectiveColumnPropertyAccessor<>(propertyNames);
		IDataProvider dataProvider = new ListDataProvider<>(Service.getPersons(50), columnPropertyAccessor);
		
		// Layer
		DataLayer dataLayer = new DataLayer(dataProvider);
		SelectionLayer selectionLayer = new SelectionLayer(dataLayer);
		ViewportLayer viewportLayer = new ViewportLayer(selectionLayer);
		viewportLayer.setRegionName(GridRegion.BODY);
		
		NatTable natTable = new NatTable(parent, viewportLayer);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(natTable);
	}
}