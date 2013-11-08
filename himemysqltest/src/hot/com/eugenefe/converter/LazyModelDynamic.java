package com.eugenefe.converter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import com.eugenefe.entity.IEntity;
import com.eugenefe.entity.IMarketVariableHis;
import com.eugenefe.entity.VcvMatrixHis;
import com.eugenefe.util.MapUtil;
import com.eugenefe.util.PivotTableModel;
import com.eugenefe.util.PivotTableModelNew;

//@Name("lazyModelVcvHis")
//@Scope(ScopeType.CONVERSATION)
/*public class LazyModelDynamic<T extends IEntity> extends LazyDataModel<PivotTableModelNew<T, String, TableDynamicContent>> {

 // @Logger
 // private Log log;

 private List<PivotTableModelNew<T, String, TableDynamicContent>> datasource;

 public List<PivotTableModelNew<T, String, TableDynamicContent>> getDatasource() {
 return datasource;
 }

 public void setDatasource(List<PivotTableModelNew<T, String, TableDynamicContent>> datasource) {
 this.datasource = datasource;
 }

 public LazyModelDynamic(List<PivotTableModelNew<T, String, TableDynamicContent>> datasource) {
 this.datasource = datasource;
 }

 @Override
 public PivotTableModel<T, String, TableDynamicContent> getRowData(String rowKey) {
 for (PivotTableModel<T, String, TableDynamicContent> aa : datasource) {
 for(Annotation bb : aa.getData().getClass().getAnnotations()){
 if (bb.annotationType().)
 return aa;

 }

 }
 return null;
 }

 @Override
 public Object getRowKey(PivotTableModelNew<T, String, TableDynamicContent> rowKey) {
 return rowKey.getData().getId();
 }


 @Override
 public List<PivotTableModelNew<T, String, TableDynamicContent>> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String,String> filters) {
 //		throw new UnsupportedOperationException("Lazy loading is not implemented.");

 System.out.println("filter1 key :");
 List<PivotTableModelNew<T, String, TableDynamicContent>> data = new ArrayList<PivotTableModelNew<T, String, TableDynamicContent>>();
 Class klass = PivotTableModel.class;
 Object  navi;
 String rst = new String();

 for (PivotTableModelNew<T, String, TableDynamicContent> aa : datasource) {
 boolean match = true;
 for (String it : filters.keySet()) {
 navi =aa;
 System.out.println("filter key :"+ it);
 try {
 String filterValue = filters.get(it).toUpperCase();

 for (String filiterProperty : it.split("\\.")) {
 System.out.println("filter Prop:"+ filiterProperty);
 navi = navi.getClass().getDeclaredField(filiterProperty).get(navi); 
 rst = String.valueOf(navi);
 System.out.println("rst Value :"+ rst);
 //						recu(aa, filiterProperty, rst);
 }
 String fieldValue = rst.toUpperCase();

 // String filterProperty = it;
 // String filterValue = filters.get(filterProperty);
 // String fieldValue =
 // String.valueOf(aa.getClass().getField(filterProperty).get(aa));

 // if(filterValue == null || fieldValue.startsWith(filterValue)) {
 if (filterValue == null || fieldValue.contains(filterValue)) {
 match = true;
 } else {
 match = false;
 break;
 }
 } catch (Exception e) {
 match = false;
 }
 }

 if (match) {
 data.add(aa);
 }
 }

 if(multiSortMeta!=null && !multiSortMeta.isEmpty()){
 for(int i = multiSortMeta.size()-1;  i>=0 ; i--){
 //        		Collections.sort(data, new LazySorterVolatilityHis(multiSortMeta.get(i).getSortField(), multiSortMeta.get(i).getSortOrder()));
 }
 //        	for(SortMeta aa: multiSortMeta){
 //        		Collections.sort(data, new LazySorterVolatilityHis(aa.getSortField(), aa.getSortOrder()));
 //        	}
 }

 int dataSize = data.size();
 this.setRowCount(dataSize);

 // paginate
 if (dataSize > pageSize) {
 // System.out.println("in the pagination" + dataSize);
 try {
 return data.subList(first, first + pageSize);
 } catch (IndexOutOfBoundsException e) {
 return data.subList(first, first + (dataSize % pageSize));
 }
 } else {
 return data;
 }
 }

 @Override
 public List<PivotTableModel<MarketVariable, MarketVariable, VolatilityHis>> load(int first, int pageSize, String sortField, SortOrder sortOrder,
 Map<String, String> filters) {
 List<PivotTableModel<MarketVariable, MarketVariable, VolatilityHis>> data 
 = new ArrayList<PivotTableModel<MarketVariable, MarketVariable, VolatilityHis>>();
 Class<PivotTableModel> klass1 = PivotTableModel.class;
 Object  navi;
 String rst = new String();

 for (PivotTableModel<MarketVariable, MarketVariable, VolatilityHis> aa : datasource) {
 boolean match = true;
 for (String it : filters.keySet()) {
 navi =aa;
 System.out.println("filter key :"+ it);
 try {
 String filterValue = filters.get(it).toUpperCase();

 for (String filiterProperty : it.split("\\.")) {
 System.out.println("filter Prop:"+ filiterProperty);
 navi = navi.getClass().getDeclaredField(filiterProperty).get(navi); 
 rst = String.valueOf(navi);
 System.out.println("rst Value :"+ rst);
 //						recu(aa, filiterProperty, rst);
 }
 String fieldValue = rst.toUpperCase();

 // String filterProperty = it;
 // String filterValue = filters.get(filterProperty);
 // String fieldValue =
 // String.valueOf(aa.getClass().getField(filterProperty).get(aa));

 // if(filterValue == null || fieldValue.startsWith(filterValue)) {
 if (filterValue == null || fieldValue.contains(filterValue)) {
 match = true;
 } else {
 match = false;
 break;
 }
 } catch (Exception e) {
 match = false;
 }
 }

 if (match) {
 data.add(aa);
 }
 }

 if (sortField != null) {
 Collections.sort(data, new LazySorterVolatilityHis(sortField, sortOrder));
 }


 int dataSize = data.size();
 this.setRowCount(dataSize);

 // paginate
 if (dataSize > pageSize) {
 // System.out.println("in the pagination" + dataSize);
 try {
 return data.subList(first, first + pageSize);
 } catch (IndexOutOfBoundsException e) {
 return data.subList(first, first + (dataSize % pageSize));
 }
 } else {
 return data;
 }
 }


 /*
 * @Override public void setRowIndex(int rowIndex) { if (getPageSize() == 0)
 * { rowIndex = -11; } super.setRowIndex(rowIndex); }

 @Override
 public void setRowIndex(int rowIndex) {

 * The following is in ancestor (LazyDataModel): this.rowIndex =
 * rowIndex == -1 ? rowIndex : (rowIndex % pageSize);

 if (rowIndex == -1 || getPageSize() == 0) {
 // System.out.println("Row Index : " + rowIndex +":"+
 // getPageSize());
 super.setRowIndex(-1);
 } else {
 // System.out.println("Row Index1 : " + rowIndex +":"+
 // getPageSize());
 super.setRowIndex(rowIndex % getPageSize());
 }
 }

 private void recursive(String fieldName, Class fieldKlazz, List<String> rst) {
 if (fieldKlazz.getPackage() != null) {
 if (fieldKlazz.getPackage().getName().contains("com.eugenefe.entity")
 || fieldKlazz.getPackage().getName().contains("com.eugenefe.controller")) {
 for (Field ff : fieldKlazz.getDeclaredFields()) {
 recursive(fieldName + "." + ff.getName(), ff.getType(), rst);
 }
 } else {
 rst.add(fieldName);
 }
 } else {
 rst.add(fieldName);
 }
 }

 private void recu(Object aa, String prop, String rst) {
 try {
 if (aa.getClass().getDeclaredField(prop).get(aa) != null) {
 recu(aa.getClass().getField(prop).get(aa), prop, String.valueOf(aa.getClass().getField(prop).get(aa)));
 System.out.println("In the Recu" + rst);
 }
 } catch (Exception e) {

 }
 }

 // for (Field aa : VolatilityHis.class.getFields()) {
 // try {
 // log.info("Field2222:#0,#1", aa.get(volatilityHisList.get(0)));
 // } catch (IllegalArgumentException e) {
 // // TODO Auto-generated catch block
 // e.printStackTrace();
 // } catch (IllegalAccessException e) {
 // // TODO Auto-generated catch block
 // e.printStackTrace();
 // }
 //
 // // log.info("Field2222:#0,#1", );
 // // recursive(aa.getName(), aa.getType(), tempRst);
 // // recursive(aa.getName(), aa, tempRst);
 // }
 // for (String st : tempRst) {
 // log.info("Field2222:#0,#1", st);
 // }
 */

public class LazyModelDynamic extends LazyDataModel<Map<String, String>> {
	private static final long serialVersionUID = 1L;

	// @Logger
	// private Log log;

	private List<Map<String, String>> datasource;

	public List<Map<String, String>> getDatasource() {
		return datasource;
	}

	public void setDatasource(List<Map<String, String>> datasource) {
		this.datasource = datasource;
	}

	public LazyModelDynamic(List<Map<String, String>> datasource) {
		this.datasource = datasource;
//		setRowCount(datasource.size());
	}

	@Override
	public Map<String, String> getRowData(String rowKey) {
		for (Map<String, String> aa : datasource) {
			if (aa.get("getStringId").equals(rowKey))
				return aa;
		}
		return null;
	}

	@Override
	public Object getRowKey(Map<String, String> rowKey) {
		return rowKey.get("getStringId");
	}
	

/*	@Override
	public List<Map<String, String>> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, String> filters) {
		// throw new
		// UnsupportedOperationException("Lazy loading is not implemented.");

		System.out.println("filter1 key :");
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		Object navi;
		String rst = new String();

		for (Map<String, String> aa : datasource) {
//			for (Map.Entry<String, String> bb : aa.entrySet()) {
//				System.out.println(bb.getKey() + "_" + bb.getValue());
//			}
			boolean match = true;
			for (Map.Entry<String, String> it : filters.entrySet()) {
//				System.out.println(it.getKey() + "_" + it.getValue() + "______");
				if (!aa.get(it.getKey()).toLowerCase().contains(it.getValue().toLowerCase())) {
					match = false;
					break;
				}
			}
			if (match) {
				data.add(aa);
			}
		}

		if (sortField != null) {
			Collections.sort(data, new LazySorterDynamicModel(sortField, sortOrder));
			// System.out.println("in the sort1 :"+ data.size()+sortField +":" +
			// sortOrder.toString());
			// Collections.sort(data);
			// System.out.println("in the sort2");
		}

		int dataSize = data.size();
		this.setRowCount(dataSize);

		// paginate
		if (dataSize > pageSize) {
			// System.out.println("in the pagination" + dataSize);
			try {
				return data.subList(first, first + pageSize);
			} catch (IndexOutOfBoundsException e) {
				return data.subList(first, first + (dataSize % pageSize));
			}
		} else {
			return data;
		}
	}*/

	

	@Override
	public List<Map<String, String>> load(int first, int pageSize, List<SortMeta> multiSortMeta,
			Map<String, String> filters) {
		// throw new
		// UnsupportedOperationException("Lazy loading is not implemented.");

		System.out.println("filter1 key 1:" + String.valueOf(getRowCount()));
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		Object navi;
		String rst = new String();

		for (Map<String, String> aa : datasource) {
//			for (Map.Entry<String, String> bb : aa.entrySet()) {
//				System.out.println(bb.getKey() + "_" + bb.getValue());
//			}
			
			boolean match = true;
			for (Map.Entry<String, String> it : filters.entrySet()) {
//				System.out.println(it.getKey() + "_" + it.getValue() + "______");
				// if (!aa.get(it.getKey()).contains(it.getValue())) {
				if (!aa.get(it.getKey()).toLowerCase().contains(it.getValue().toLowerCase())) {
					match = false;
					break;
				}
			}
			if (match) {
				data.add(aa);
			}
		}

		if (multiSortMeta != null && !multiSortMeta.isEmpty()) {
			for (int i = multiSortMeta.size() - 1; i >= 0; i--) {
				Collections.sort(data, new LazySorterDynamicModel(multiSortMeta.get(i).getSortField(), multiSortMeta
						.get(i).getSortOrder()));
				// LazySorterVolatilityHis(multiSortMeta.get(i).getSortField(),
				// multiSortMeta.get(i).getSortOrder()));
			}
			// for(SortMeta aa: multiSortMeta){
			// Collections.sort(data, new
			// LazySorterVolatilityHis(aa.getSortField(), aa.getSortOrder()));
			// }
		}

		int dataSize = data.size();
		this.setRowCount(dataSize);

		// paginate
		if (dataSize > pageSize) {
			// System.out.println("in the pagination" + dataSize);
			try {
				return data.subList(first, first + pageSize);
			} catch (IndexOutOfBoundsException e) {
				return data.subList(first, first + (dataSize % pageSize));
			}
		} else {
			return data;
		}
	}

	@Override
	public void setRowIndex(int rowIndex) {
		/*
		 * The following is in ancestor (LazyDataModel): this.rowIndex =
		 * rowIndex == -1 ? rowIndex : (rowIndex % pageSize);
		 */
		if (rowIndex == -1 || getPageSize() == 0) {
			// System.out.println("Row Index : " + rowIndex +":"+
			// getPageSize());
			super.setRowIndex(-1);
		} else {
			// System.out.println("Row Index1 : " + rowIndex +":"+
			// getPageSize());
			super.setRowIndex(rowIndex % getPageSize());
		}
	}
//	@Override
//	public void setRowCount(int rowCount) {
//		super.setRowCount(rowCount);
//	}
	

//	private void recursive(String fieldName, Class fieldKlazz, List<String> rst) {
//		if (fieldKlazz.getPackage() != null) {
//			if (fieldKlazz.getPackage().getName().contains("com.eugenefe.entity")
//					|| fieldKlazz.getPackage().getName().contains("com.eugenefe.controller")) {
//				for (Field ff : fieldKlazz.getDeclaredFields()) {
//					recursive(fieldName + "." + ff.getName(), ff.getType(), rst);
//				}
//			} else {
//				rst.add(fieldName);
//			}
//		} else {
//			rst.add(fieldName);
//		}
//	}
//
//	private void recu(Object aa, String prop, String rst) {
//		try {
//			if (aa.getClass().getDeclaredField(prop).get(aa) != null) {
//				recu(aa.getClass().getField(prop).get(aa), prop, String.valueOf(aa.getClass().getField(prop).get(aa)));
//				System.out.println("In the Recu" + rst);
//			}
//		} catch (Exception e) {
//
//		}
//	}

	// for (Field aa : VolatilityHis.class.getFields()) {
	// try {
	// log.info("Field2222:#0,#1", aa.get(volatilityHisList.get(0)));
	// } catch (IllegalArgumentException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IllegalAccessException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// // log.info("Field2222:#0,#1", );
	// // recursive(aa.getName(), aa.getType(), tempRst);
	// // recursive(aa.getName(), aa, tempRst);
	// }
	// for (String st : tempRst) {
	// log.info("Field2222:#0,#1", st);
	// }
}
