package com.eugenefe.controller;

import java.math.BigDecimal;
import java.util.Collection;
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
import com.eugenefe.entity.IntRateHis;
import com.eugenefe.entity.MarketVariable;
import com.eugenefe.util.CrossTableModel;

@Name("chartIrcHisAction")
@Scope(ScopeType.CONVERSATION)
public class ChartIrcHisAction {
	@Logger
	private Log log;

	@In(required = false)
	private List<CrossTableModel> selectedTableModel;

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
		} 
		else {
			for (CrossTableModel aa : selectedTableModel) {
				log.info("in the load chart11 :#0 ", aa.getLabel());
				tempSeries = new LineChartSeries();
				tempSeries.setLabel(aa.getLabel());
				
				for(Map.Entry<String,BigDecimal> entry : aa.getContentMap().entrySet())	{
					tempSeries.set(entry.getKey(),entry.getValue());
				}
				linearModel.addSeries(tempSeries);
			}

		}
	}
	public void loadChart() {
//		log.info("Tab: #0,#1", event.getTab().getClientId(), event.getTab().getId());
		linearModel = new CartesianChartModel();
		LineChartSeries tempSeries = new LineChartSeries();

		if (selectedTableModel == null || selectedTableModel.isEmpty()) {
			log.info("in the load chart");
			tempSeries.set(" ", 0);
			linearModel.addSeries(tempSeries);
		} 
		else {
			for (CrossTableModel aa : selectedTableModel) {
				log.info("in the load chart11 :#0 ", aa.getLabel());
				tempSeries = new LineChartSeries();
				tempSeries.setLabel(aa.getLabel());
				
				for(Map.Entry<String,BigDecimal> entry : aa.getContentMap().entrySet())	{
					tempSeries.set(entry.getKey(),entry.getValue());
				}
				linearModel.addSeries(tempSeries);
			}

		}
	}
}
