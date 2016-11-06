
package com.shashwat.nattable.app.view;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.data.IColumnPropertyAccessor;
import org.eclipse.nebula.widgets.nattable.data.IDataProvider;
import org.eclipse.nebula.widgets.nattable.data.ListDataProvider;
import org.eclipse.nebula.widgets.nattable.data.ReflectiveColumnPropertyAccessor;
import org.eclipse.nebula.widgets.nattable.grid.GridRegion;
import org.eclipse.nebula.widgets.nattable.grid.data.DefaultColumnHeaderDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.layer.ColumnHeaderLayer;
import org.eclipse.nebula.widgets.nattable.layer.CompositeLayer;
import org.eclipse.nebula.widgets.nattable.layer.DataLayer;
import org.eclipse.nebula.widgets.nattable.layer.ILayer;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer;
import org.eclipse.nebula.widgets.nattable.viewport.ViewportLayer;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.shashwat.nattable.app.model.Person;
import com.shashwat.nattable.app.model.Service;

public class NatTableCompositionViewPart {
	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout());
		String[] propertyNames = { "firstName", "lastName", "gender", "married", "birthday" };

		// mapping from property to label, needed for column header labels
		Map<String, String> propertyToHeaderMap = new HashMap<String, String>();
		propertyToHeaderMap.put("firstName", "Firstname");
		propertyToHeaderMap.put("lastName", "Lastname");
		propertyToHeaderMap.put("gender", "Gender");
		propertyToHeaderMap.put("married", "Married");
		propertyToHeaderMap.put("birthday", "Birthday");
				
		// data provider
		IColumnPropertyAccessor<Person> columnPropertyAccessor = new ReflectiveColumnPropertyAccessor<>(propertyNames);
		IDataProvider dataProvider = new ListDataProvider<>(Service.getPersons(50), columnPropertyAccessor);

		// Layer
		DataLayer dataLayer = new DataLayer(dataProvider);
		SelectionLayer selectionLayer = new SelectionLayer(dataLayer);
		ViewportLayer viewportLayer = new ViewportLayer(selectionLayer);
		
		// Column header Layer
		IDataProvider headerDataProvider = new DefaultColumnHeaderDataProvider(propertyNames, propertyToHeaderMap);
		DataLayer headerDataLayer = new DataLayer(headerDataProvider);
		ILayer columnHeaderLayer = new ColumnHeaderLayer(headerDataLayer, viewportLayer, selectionLayer);
		
		// composition
		CompositeLayer compositeLayer = new CompositeLayer(1, 2);
		compositeLayer.setChildLayer(GridRegion.COLUMN_HEADER, columnHeaderLayer, 0, 0);
		compositeLayer.setChildLayer(GridRegion.BODY, viewportLayer, 0, 1);
		
		NatTable nattable = new NatTable(parent, compositeLayer);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(nattable);
	}
}