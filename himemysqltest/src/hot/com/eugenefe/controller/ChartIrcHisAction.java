package com.eugenefe.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;

import com.eugenefe.entity.IMarketVariableHis;
import com.eugenefe.entity.IntRate;
import com.eugenefe.entity.IntRateHis;
import com.eugenefe.entity.IrCurve;
import com.eugenefe.entity.MarketVariable;
import com.eugenefe.enums.EMaturity;
import com.eugenefe.util.CrossTableModelOld;
import com.eugenefe.util.PivotTableModel;

@Name("chartIrcHisAction")
@Scope(ScopeType.CONVERSATION)
public class ChartIrcHisAction {
	@Logger
	private Log log;

	// @In(required = false, value =
	// "#{tableIrCurveHisAction.selectedTableModel}")
	@In(required = false)
	private List<PivotTableModel<IrCurve, EMaturity,IntRate>> selectedTableModel;

	// public List<PivotTableModel<IrCurve, EMaturity>> getSelectedTableModel()
	// {
	// return selectedTableModel;
	// }
	//
	// public void setSelectedTableModel(List<PivotTableModel<IrCurve,
	// EMaturity>> selectedTableModel) {
	// this.selectedTableModel = selectedTableModel;
	// }

	private CartesianChartModel linearModel;

	public ChartIrcHisAction() {
		linearModel = new CartesianChartModel();
		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Series 1");
		series1.set(" ", 0);
		linearModel.addSeries(series1);
	}

	public CartesianChartModel getLinearModel() {
		return linearModel;
	}

	public void setLinearModel(CartesianChartModel linearModel) {
		this.linearModel = linearModel;
	}

	public void loadChart1(TabChangeEvent event) {
		log.info("Tab: #0,#1", event.getTab().getClientId(), event.getTab().getId());
		linearModel = new CartesianChartModel();
		LineChartSeries tempSeries = new LineChartSeries();

		if (selectedTableModel == null || selectedTableModel.isEmpty()) {
			log.info("in the load chart");
			tempSeries.set(" ", 0);
			linearModel.addSeries(tempSeries);
		} else {
			for (PivotTableModel<IrCurve, EMaturity,IntRate> aa : selectedTableModel) {
				log.info("in the load chart11 :#0 ", aa.getData().getIrcId());
				tempSeries = new LineChartSeries();
				tempSeries.setLabel(aa.getData().getIrcId());

				for (Map.Entry<EMaturity, IntRate> entry : aa.getContentMap().entrySet()) {
					tempSeries.set(entry.getKey().getName(), entry.getValue().getIntRateHisList().get(0).getIntRate());
				}
				linearModel.addSeries(tempSeries);
			}

		}
	}

	@Observer(value = "evtReloadTermStructure")
	public void onReloadChart(List<PivotTableModel<IrCurve, EMaturity,IntRate>> tableModel) {
		List<PivotTableModel<IrCurve, EMaturity,IntRate>> tempList = new ArrayList<PivotTableModel<IrCurve, EMaturity,IntRate>>();

		if (selectedTableModel != null) {
			for (PivotTableModel<IrCurve, EMaturity,IntRate> aa : tableModel) {
				for (PivotTableModel<IrCurve, EMaturity,IntRate> bb : selectedTableModel) {
					if (bb.getData().getIrcId().equals(aa.getData().getIrcId())) {
						tempList.add(aa);
					}
				}
			}
			selectedTableModel = tempList;
			loadChart();
		}
	}

	public void loadChart() {
		log.info("In the TermStructure Chart: #0,#1");
		linearModel = new CartesianChartModel();
		LineChartSeries tempSeries = new LineChartSeries();
		List<EMaturity> tempList = new ArrayList<EMaturity>();

		if (selectedTableModel == null || selectedTableModel.isEmpty()) {
			log.info("in the load chart");
			tempSeries.set(" ", 0);
			linearModel.addSeries(tempSeries);
		} 
		else {
			tempList.add(EMaturity.Y01);					//null ¹æÁö
			for (PivotTableModel<IrCurve, EMaturity,IntRate> aa : selectedTableModel) {
				for (EMaturity key : aa.getContentMap().keySet()) {
					if (!tempList.contains(key)) {
						tempList.add(key);
					}
				}
				Collections.sort(tempList);
//				log.info("in the load chart1: #0", tempList.size());
			}
			for (PivotTableModel<IrCurve, EMaturity,IntRate> aa : selectedTableModel) {
				tempSeries = new LineChartSeries();
				tempSeries.setLabel(aa.getData().getIrcId());

				for (EMaturity key : tempList) {
					if (aa.getContentMap().keySet().contains(key)) {
						tempSeries.set(key.getName(), aa.getContentMap().get(key).getIntRateHisList().get(0).getIntRate());
					} else {
						tempSeries.set(key.getName(), null);
					}
				}
//				for (Map.Entry<EMaturity, BigDecimal> entry : aa.getContentMap().entrySet()) {
//					tempSeries.set(entry.getKey().getName(), entry.getValue());
//					// log.info("in the load chart11 :#0,#1 ",aa.getData().getIrcId(), entry.getValue());
//				}
				linearModel.addSeries(tempSeries);
//				log.info("in the load chart2");
			}

		}
	}

	public String getDatatipFormat() {
		return "<span style=\"display:none;\">%s</span><span>%.4f</span>";
		// return "<span>%s</span><span>%.5f</span>";
	}
}
