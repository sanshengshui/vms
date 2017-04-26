package com.mektec.vms.view.Chart;

import com.mektec.vms.domain.Defect;
import com.mektec.vms.domain.DefectRecord;
import com.mektec.vms.domain.ProductLine;
import com.mektec.vms.domain.WorkStation;
import com.vaadin.ui.*;
import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.metadata.LegendPlacements;
import org.dussan.vaadin.dcharts.metadata.SeriesToggles;
import org.dussan.vaadin.dcharts.metadata.locations.LegendLocations;
import org.dussan.vaadin.dcharts.metadata.renderers.LegendRenderers;
import org.dussan.vaadin.dcharts.metadata.renderers.SeriesRenderers;
import org.dussan.vaadin.dcharts.options.Legend;
import org.dussan.vaadin.dcharts.options.Options;
import org.dussan.vaadin.dcharts.options.SeriesDefaults;
import org.dussan.vaadin.dcharts.renderers.legend.EnhancedLegendRenderer;
import org.dussan.vaadin.dcharts.renderers.series.PieRenderer;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;

/**
 * Created by mektec on 16-6-1.
 */
public class PieChart extends PieChartConfigUI {

    private Map<String,Map<String,Integer>> resultMap;   //<时间段，<工站，记录数>>
    private Set dateSet;       //时间段的集合
    private Set categorySet;   //工站编号的集合
    private Map<String,Integer> recordsMap;
    private DataSeries dataSeries;
    private Integer count;         //计数（缺陷的记录）
    private Integer currentDay;     //具体，某天、月、年的日期

    public PieChart() {

        dateSet = new TreeSet();         //时间段的集合

        productLineTree.addValueChangeListener(event ->{

            optionGroup.addValueChangeListener(event1 ->{

                dateText.setValue(new Date());
                Calendar c = Calendar.getInstance();
                c.setTime(dateText.getValue());

                if(optionGroup.getValue().equals("日")) {

                    dateText.setDateFormat("yyyy-MM-dd");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    currentDay = Integer.valueOf(sdf.format(dateText.getValue()));

                    //日期的增减(在向前\向后的按键上进行修改)
                    leftButton.addClickListener(e1 -> {
                        c.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)-1);
                        dateText.setValue(c.getTime());
                        dateText.setDateFormat("yyyy-MM-dd");
                        currentDay = Integer.valueOf(sdf.format(dateText.getValue()));
                    });
                    rightButton.addClickListener(e1 -> {
                        c.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)+1);
                        dateText.setValue(c.getTime());
                        dateText.setDateFormat("yyyy-MM-dd");
                        currentDay = Integer.valueOf(sdf.format(dateText.getValue()));
                    });
                }
                if(optionGroup.getValue().equals("月")) {
                    dateText.setDateFormat("yyyy-MM");

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
                    Date date = dateText.getValue();
                    currentDay = Integer.valueOf(sdf.format(date));

                    //日期的增减
                    leftButton.addClickListener(e1 -> {
                        c.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH)-1,c.get(Calendar.DAY_OF_MONTH));
                        dateText.setValue(c.getTime());
                        dateText.setDateFormat("yyyy-MM");
                        currentDay = Integer.valueOf(sdf.format(dateText.getValue()));
                    });
                    rightButton.addClickListener(e1 -> {
                        c.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH)+1,c.get(Calendar.DAY_OF_MONTH));
                        dateText.setValue(c.getTime());
                        dateText.setDateFormat("yyyy-MM");
                        currentDay = Integer.valueOf(sdf.format(dateText.getValue()));
                    });
                }
                if(optionGroup.getValue().equals("年")) {
                    dateText.setDateFormat("yyyy");

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                    Date date = dateText.getValue();
                    currentDay = Integer.valueOf(sdf.format(date));

                    //日期的增减
                    leftButton.addClickListener(e1 -> {
                        c.set(c.get(Calendar.YEAR)-1,c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
                        dateText.setValue(c.getTime());
                        dateText.setDateFormat("yyyy");
                        currentDay = Integer.valueOf(sdf.format(dateText.getValue()));
                    });
                    rightButton.addClickListener(e1 -> {
                        c.set(c.get(Calendar.YEAR)+1,c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
                        dateText.setValue(c.getTime());
                        dateText.setDateFormat("yyyy");
                        currentDay = Integer.valueOf(sdf.format(dateText.getValue()));
                    });
                }
            });
        });

        ensureButton.addClickListener(e2 ->{
            statisticsDefectCounts();

            chartLayout.removeAllComponents();
            chartLayout.addComponent(doPieChart());
        });
    }

    public void statisticsDefectCounts() {
        List<DefectRecord> recordList = defectRecordService.findAllRecord();    //记录的集合

        dateSet = new TreeSet<>();
        dateSet.add(currentDay);

        resultMap = new HashMap();               //最终集合
        count = 0;

        List<ProductLine> lineList = productLineService.findAllLine();

        for (DefectRecord record : recordList) {
            WorkStation ws = workStationService.findWorkStationById(record.getstationId());

            categorySet = new TreeSet();
            categorySet.add(ws.getStationName());

            recordsMap = new HashMap<>();                      //把原先的recordsMap清空

            if (productLineTree.getValue().equals(ws.getStationCode()+":"+ws.getStationName())) {

                for (Object currentDate : dateSet) {                       //各个时间段
                    for (Object stationName : categorySet) {                  //各个工站

                        for (DefectRecord record1 : recordList) {                     //所有的记录
                            if (record1.getstationId().equals(ws.getStationId())) {

                                WorkStation workStation = workStationService.
                                        findWorkStationById(record1.getstationId());

                                Defect defect = record1.getDefect();
                                String recordDate = String.valueOf(record1.getDate());

                                if (recordDate.contains(currentDate.toString())) {             //具体的时间
                                    if (workStation.getStationName().equals(stationName)) {          //具体的工站
                                        count++;
                                        for (String s : recordsMap.keySet()) {
                                            if (s.equals(defect.getDefectName())) {
                                                count = recordsMap.get(s) + 1;
                                            }
                                        }
                                        recordsMap.put(defect.getDefectName(), count);
                                    }
                                }
                            }
                            count = 0;
                        }
                    }
                    resultMap.put(currentDate.toString(), recordsMap);
                }
            }
            else{
            for (ProductLine line : lineList) {
                List<WorkStation> list = workStationService.findWorkStationsByLineCode(line.getLineCode());

                categorySet = new TreeSet();
                for (WorkStation work : list) {
                    categorySet.add(work.getStationName());            //具体到每一个工站
                }

                if (productLineTree.getValue().equals(line.getLineCode())) {

                    recordsMap = new HashMap<>();                     //把原先的recordsMap清空

                    for (Object currentDate : dateSet) {                     //各个时间段
                        for (Object stationName : categorySet) {                   //各个工站

                            for (DefectRecord record1 : recordList) {                    //所有的记录

                                WorkStation workStation = workStationService.
                                        findWorkStationById(record1.getstationId());

                                String recordDate = String.valueOf(record1.getDate());

                                if (recordDate.contains(currentDate.toString())) {          //具体的时间
                                    if (workStation.getStationName().equals(stationName)) {        //具体的工站
                                        count++;
                                        for (String s : recordsMap.keySet()) {
                                            if (s.equals(stationName)) {
                                                count = recordsMap.get(s) + 1;
                                            }
                                        }
                                        recordsMap.put((String) stationName, count);
                                    }
                                }
                                count = 0;
                            }
                            resultMap.put(currentDate.toString(), recordsMap);
                        }
                    }
                }
            }
            }
        }

    }

    public GridLayout doPieChart(){

        GridLayout layout = new GridLayout();
        dataSeries = new DataSeries();

        for(String date : resultMap.keySet()) {
            dataSeries = new DataSeries();    //因为到了新的date，要把原先的dataseries清空

            Map map = resultMap.get(date);
            for(Object object : map.keySet()) {

                dataSeries.newSeries();
                dataSeries.add(object+","+map.get(object), map.get(object));
            }
        }
        resultMap = new HashMap<>();

        SeriesDefaults seriesDefaults = new SeriesDefaults()
                .setRenderer(SeriesRenderers.PIE)
                .setRendererOptions(
                        new PieRenderer()
                                .setDataLabelCenterOn(true)
                                .setShowDataLabels(true));

        Legend legend = new Legend()
                .setShow(true)
                .setPlacement(LegendPlacements.INSIDE_GRID)
                .setLocation(LegendLocations.WEST)
                .setRenderer(LegendRenderers.ENHANCED)
                .setShowLabels(true)
                .setRendererOptions(
                        new EnhancedLegendRenderer()
                                .setSeriesToggle(SeriesToggles.NORMAL)
                                .setSeriesToggleReplot(true));

        Options options = new Options()
                .setSeriesDefaults(seriesDefaults)
                .setTitle(currentDay +"--"+productLineTree.getValue()+"--不良项统计")
                .setLegend(legend);

        DCharts chart = new DCharts()
                .setDataSeries(dataSeries)
                .setOptions(options)
                .show();

        layout.addComponents(chart);
        layout.setWidth(700,Unit.POINTS);
        layout.setHeight(100,Unit.PERCENTAGE);
        layout.setSpacing(true);

        return layout;
    }

}
