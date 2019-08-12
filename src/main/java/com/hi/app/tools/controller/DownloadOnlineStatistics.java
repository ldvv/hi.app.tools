package com.hi.app.tools.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.*;

@Controller
@RequestMapping(value = "/")
public class DownloadOnlineStatistics {

    private static final Logger logger = LoggerFactory.getLogger(DownloadOnlineStatistics.class);

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(HttpServletResponse response) throws Exception{
        String dataStr = "{\n" +
                "    \"datas\":[\n" +
                "        {\n" +
                "            \"pickup_over_24h_onlineNums\":9,\n" +
                "            \"order_72h_onlineNums\":16,\n" +
                "            \"onlineRate\":\"100.00%\",\n" +
                "            \"pickup_3h_onlineNums\":0,\n" +
                "            \"queryDate\":\"2019-07-02\",\n" +
                "            \"order_24h_onlineNums\":0,\n" +
                "            \"order_48h_onlineRate\":\"30.76%\",\n" +
                "            \"pickup_6h_onlineNums\":0,\n" +
                "            \"channelCode\":\"1#\",\n" +
                "            \"order_over_72h_onlineNums\":10,\n" +
                "            \"order_24h_onlineRate\":\"0.00%\",\n" +
                "            \"pickup_24h_onlineNums\":17,\n" +
                "            \"pickup_6h_onlineRate\":\"0.00%\",\n" +
                "            \"order_48h_onlineNums\":8,\n" +
                "            \"order_over_72h_onlineRate\":\"38.46%\",\n" +
                "            \"pickup_12h_onlineNums\":0,\n" +
                "            \"pickupNums\":26,\n" +
                "            \"pickupEfficiency\":21.7,\n" +
                "            \"pickup_over_24h_onlineRate\":\"34.61%\",\n" +
                "            \"onlineNums\":26,\n" +
                "            \"pickup_3h_onlineRate\":\"0.00%\",\n" +
                "            \"pickup_24h_onlineRate\":\"65.38%\",\n" +
                "            \"orderEfficiency\":92.18,\n" +
                "            \"delayDays\":1,\n" +
                "            \"pickup_12h_onlineRate\":\"0.00%\",\n" +
                "            \"pickupDate\":\"2019-07-01\",\n" +
                "            \"pickupCode\":\"D\",\n" +
                "            \"order_72h_onlineRate\":\"61.53%\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"pickup_over_24h_onlineNums\":20,\n" +
                "            \"order_72h_onlineNums\":23,\n" +
                "            \"onlineRate\":\"100.00%\",\n" +
                "            \"pickup_3h_onlineNums\":0,\n" +
                "            \"queryDate\":\"2019-07-03\",\n" +
                "            \"order_24h_onlineNums\":0,\n" +
                "            \"order_48h_onlineRate\":\"53.33%\",\n" +
                "            \"pickup_6h_onlineNums\":0,\n" +
                "            \"channelCode\":\"1#\",\n" +
                "            \"order_over_72h_onlineNums\":7,\n" +
                "            \"order_24h_onlineRate\":\"0.00%\",\n" +
                "            \"pickup_24h_onlineNums\":10,\n" +
                "            \"pickup_6h_onlineRate\":\"0.00%\",\n" +
                "            \"order_48h_onlineNums\":16,\n" +
                "            \"order_over_72h_onlineRate\":\"23.33%\",\n" +
                "            \"pickup_12h_onlineNums\":0,\n" +
                "            \"pickupNums\":30,\n" +
                "            \"pickupEfficiency\":26.73,\n" +
                "            \"pickup_over_24h_onlineRate\":\"66.66%\",\n" +
                "            \"onlineNums\":30,\n" +
                "            \"pickup_3h_onlineRate\":\"0.00%\",\n" +
                "            \"pickup_24h_onlineRate\":\"33.33%\",\n" +
                "            \"orderEfficiency\":65.07,\n" +
                "            \"delayDays\":1,\n" +
                "            \"pickup_12h_onlineRate\":\"0.00%\",\n" +
                "            \"pickupDate\":\"2019-07-02\",\n" +
                "            \"pickupCode\":\"D\",\n" +
                "            \"order_72h_onlineRate\":\"76.66%\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"pickup_over_24h_onlineNums\":24,\n" +
                "            \"order_72h_onlineNums\":18,\n" +
                "            \"onlineRate\":\"100.00%\",\n" +
                "            \"pickup_3h_onlineNums\":0,\n" +
                "            \"queryDate\":\"2019-07-04\",\n" +
                "            \"order_24h_onlineNums\":0,\n" +
                "            \"order_48h_onlineRate\":\"46.15%\",\n" +
                "            \"pickup_6h_onlineNums\":0,\n" +
                "            \"channelCode\":\"1#\",\n" +
                "            \"order_over_72h_onlineNums\":8,\n" +
                "            \"order_24h_onlineRate\":\"0.00%\",\n" +
                "            \"pickup_24h_onlineNums\":2,\n" +
                "            \"pickup_6h_onlineRate\":\"0.00%\",\n" +
                "            \"order_48h_onlineNums\":12,\n" +
                "            \"order_over_72h_onlineRate\":\"30.76%\",\n" +
                "            \"pickup_12h_onlineNums\":0,\n" +
                "            \"pickupNums\":26,\n" +
                "            \"pickupEfficiency\":27.93,\n" +
                "            \"pickup_over_24h_onlineRate\":\"92.30%\",\n" +
                "            \"onlineNums\":26,\n" +
                "            \"pickup_3h_onlineRate\":\"0.00%\",\n" +
                "            \"pickup_24h_onlineRate\":\"7.69%\",\n" +
                "            \"orderEfficiency\":74.68,\n" +
                "            \"delayDays\":1,\n" +
                "            \"pickup_12h_onlineRate\":\"0.00%\",\n" +
                "            \"pickupDate\":\"2019-07-03\",\n" +
                "            \"pickupCode\":\"D\",\n" +
                "            \"order_72h_onlineRate\":\"69.23%\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"pickup_over_24h_onlineNums\":28,\n" +
                "            \"order_72h_onlineNums\":28,\n" +
                "            \"onlineRate\":\"100.00%\",\n" +
                "            \"pickup_3h_onlineNums\":0,\n" +
                "            \"queryDate\":\"2019-07-05\",\n" +
                "            \"order_24h_onlineNums\":0,\n" +
                "            \"order_48h_onlineRate\":\"26.19%\",\n" +
                "            \"pickup_6h_onlineNums\":0,\n" +
                "            \"channelCode\":\"1#\",\n" +
                "            \"order_over_72h_onlineNums\":14,\n" +
                "            \"order_24h_onlineRate\":\"0.00%\",\n" +
                "            \"pickup_24h_onlineNums\":14,\n" +
                "            \"pickup_6h_onlineRate\":\"0.00%\",\n" +
                "            \"order_48h_onlineNums\":11,\n" +
                "            \"order_over_72h_onlineRate\":\"33.33%\",\n" +
                "            \"pickup_12h_onlineNums\":0,\n" +
                "            \"pickupNums\":42,\n" +
                "            \"pickupEfficiency\":28.14,\n" +
                "            \"pickup_over_24h_onlineRate\":\"66.66%\",\n" +
                "            \"onlineNums\":42,\n" +
                "            \"pickup_3h_onlineRate\":\"0.00%\",\n" +
                "            \"pickup_24h_onlineRate\":\"33.33%\",\n" +
                "            \"orderEfficiency\":69.17,\n" +
                "            \"delayDays\":1,\n" +
                "            \"pickup_12h_onlineRate\":\"0.00%\",\n" +
                "            \"pickupDate\":\"2019-07-04\",\n" +
                "            \"pickupCode\":\"D\",\n" +
                "            \"order_72h_onlineRate\":\"66.66%\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"pickup_over_24h_onlineNums\":20,\n" +
                "            \"order_72h_onlineNums\":17,\n" +
                "            \"onlineRate\":\"100.00%\",\n" +
                "            \"pickup_3h_onlineNums\":0,\n" +
                "            \"queryDate\":\"2019-07-06\",\n" +
                "            \"order_24h_onlineNums\":0,\n" +
                "            \"order_48h_onlineRate\":\"25.00%\",\n" +
                "            \"pickup_6h_onlineNums\":0,\n" +
                "            \"channelCode\":\"1#\",\n" +
                "            \"order_over_72h_onlineNums\":7,\n" +
                "            \"order_24h_onlineRate\":\"0.00%\",\n" +
                "            \"pickup_24h_onlineNums\":4,\n" +
                "            \"pickup_6h_onlineRate\":\"0.00%\",\n" +
                "            \"order_48h_onlineNums\":6,\n" +
                "            \"order_over_72h_onlineRate\":\"29.16%\",\n" +
                "            \"pickup_12h_onlineNums\":0,\n" +
                "            \"pickupNums\":24,\n" +
                "            \"pickupEfficiency\":26.96,\n" +
                "            \"pickup_over_24h_onlineRate\":\"83.33%\",\n" +
                "            \"onlineNums\":24,\n" +
                "            \"pickup_3h_onlineRate\":\"0.00%\",\n" +
                "            \"pickup_24h_onlineRate\":\"16.66%\",\n" +
                "            \"orderEfficiency\":94.2,\n" +
                "            \"delayDays\":1,\n" +
                "            \"pickup_12h_onlineRate\":\"0.00%\",\n" +
                "            \"pickupDate\":\"2019-07-05\",\n" +
                "            \"pickupCode\":\"D\",\n" +
                "            \"order_72h_onlineRate\":\"70.83%\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"pickup_over_24h_onlineNums\":31,\n" +
                "            \"order_72h_onlineNums\":10,\n" +
                "            \"onlineRate\":\"100.00%\",\n" +
                "            \"pickup_3h_onlineNums\":0,\n" +
                "            \"queryDate\":\"2019-07-07\",\n" +
                "            \"order_24h_onlineNums\":0,\n" +
                "            \"order_48h_onlineRate\":\"0.00%\",\n" +
                "            \"pickup_6h_onlineNums\":0,\n" +
                "            \"channelCode\":\"1#\",\n" +
                "            \"order_over_72h_onlineNums\":21,\n" +
                "            \"order_24h_onlineRate\":\"0.00%\",\n" +
                "            \"pickup_24h_onlineNums\":0,\n" +
                "            \"pickup_6h_onlineRate\":\"0.00%\",\n" +
                "            \"order_48h_onlineNums\":0,\n" +
                "            \"order_over_72h_onlineRate\":\"67.74%\",\n" +
                "            \"pickup_12h_onlineNums\":0,\n" +
                "            \"pickupNums\":31,\n" +
                "            \"pickupEfficiency\":46.54,\n" +
                "            \"pickup_over_24h_onlineRate\":\"100.00%\",\n" +
                "            \"onlineNums\":31,\n" +
                "            \"pickup_3h_onlineRate\":\"0.00%\",\n" +
                "            \"pickup_24h_onlineRate\":\"0.00%\",\n" +
                "            \"orderEfficiency\":101.59,\n" +
                "            \"delayDays\":1,\n" +
                "            \"pickup_12h_onlineRate\":\"0.00%\",\n" +
                "            \"pickupDate\":\"2019-07-06\",\n" +
                "            \"pickupCode\":\"D\",\n" +
                "            \"order_72h_onlineRate\":\"32.25%\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"pickup_over_24h_onlineNums\":1,\n" +
                "            \"order_72h_onlineNums\":5,\n" +
                "            \"onlineRate\":\"100.00%\",\n" +
                "            \"pickup_3h_onlineNums\":0,\n" +
                "            \"queryDate\":\"2019-07-08\",\n" +
                "            \"order_24h_onlineNums\":0,\n" +
                "            \"order_48h_onlineRate\":\"37.50%\",\n" +
                "            \"pickup_6h_onlineNums\":0,\n" +
                "            \"channelCode\":\"1#\",\n" +
                "            \"order_over_72h_onlineNums\":3,\n" +
                "            \"order_24h_onlineRate\":\"0.00%\",\n" +
                "            \"pickup_24h_onlineNums\":7,\n" +
                "            \"pickup_6h_onlineRate\":\"0.00%\",\n" +
                "            \"order_48h_onlineNums\":3,\n" +
                "            \"order_over_72h_onlineRate\":\"37.50%\",\n" +
                "            \"pickup_12h_onlineNums\":0,\n" +
                "            \"pickupNums\":8,\n" +
                "            \"pickupEfficiency\":19.4,\n" +
                "            \"pickup_over_24h_onlineRate\":\"12.50%\",\n" +
                "            \"onlineNums\":8,\n" +
                "            \"pickup_3h_onlineRate\":\"0.00%\",\n" +
                "            \"pickup_24h_onlineRate\":\"87.50%\",\n" +
                "            \"orderEfficiency\":83.68,\n" +
                "            \"delayDays\":1,\n" +
                "            \"pickup_12h_onlineRate\":\"0.00%\",\n" +
                "            \"pickupDate\":\"2019-07-07\",\n" +
                "            \"pickupCode\":\"D\",\n" +
                "            \"order_72h_onlineRate\":\"62.50%\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"pickup_over_24h_onlineNums\":1,\n" +
                "            \"order_72h_onlineNums\":33,\n" +
                "            \"onlineRate\":\"100.00%\",\n" +
                "            \"pickup_3h_onlineNums\":0,\n" +
                "            \"queryDate\":\"2019-07-09\",\n" +
                "            \"order_24h_onlineNums\":0,\n" +
                "            \"order_48h_onlineRate\":\"32.00%\",\n" +
                "            \"pickup_6h_onlineNums\":0,\n" +
                "            \"channelCode\":\"1#\",\n" +
                "            \"order_over_72h_onlineNums\":17,\n" +
                "            \"order_24h_onlineRate\":\"0.00%\",\n" +
                "            \"pickup_24h_onlineNums\":49,\n" +
                "            \"pickup_6h_onlineRate\":\"0.00%\",\n" +
                "            \"order_48h_onlineNums\":16,\n" +
                "            \"order_over_72h_onlineRate\":\"34.00%\",\n" +
                "            \"pickup_12h_onlineNums\":0,\n" +
                "            \"pickupNums\":50,\n" +
                "            \"pickupEfficiency\":18.91,\n" +
                "            \"pickup_over_24h_onlineRate\":\"2.00%\",\n" +
                "            \"onlineNums\":50,\n" +
                "            \"pickup_3h_onlineRate\":\"0.00%\",\n" +
                "            \"pickup_24h_onlineRate\":\"98.00%\",\n" +
                "            \"orderEfficiency\":65.09,\n" +
                "            \"delayDays\":1,\n" +
                "            \"pickup_12h_onlineRate\":\"0.00%\",\n" +
                "            \"pickupDate\":\"2019-07-08\",\n" +
                "            \"pickupCode\":\"D\",\n" +
                "            \"order_72h_onlineRate\":\"66.00%\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"pickup_over_24h_onlineNums\":30,\n" +
                "            \"order_72h_onlineNums\":36,\n" +
                "            \"onlineRate\":\"100.00%\",\n" +
                "            \"pickup_3h_onlineNums\":0,\n" +
                "            \"queryDate\":\"2019-07-10\",\n" +
                "            \"order_24h_onlineNums\":0,\n" +
                "            \"order_48h_onlineRate\":\"48.00%\",\n" +
                "            \"pickup_6h_onlineNums\":0,\n" +
                "            \"channelCode\":\"1#\",\n" +
                "            \"order_over_72h_onlineNums\":14,\n" +
                "            \"order_24h_onlineRate\":\"0.00%\",\n" +
                "            \"pickup_24h_onlineNums\":20,\n" +
                "            \"pickup_6h_onlineRate\":\"0.00%\",\n" +
                "            \"order_48h_onlineNums\":24,\n" +
                "            \"order_over_72h_onlineRate\":\"28.00%\",\n" +
                "            \"pickup_12h_onlineNums\":0,\n" +
                "            \"pickupNums\":50,\n" +
                "            \"pickupEfficiency\":25.39,\n" +
                "            \"pickup_over_24h_onlineRate\":\"60.00%\",\n" +
                "            \"onlineNums\":50,\n" +
                "            \"pickup_3h_onlineRate\":\"0.00%\",\n" +
                "            \"pickup_24h_onlineRate\":\"40.00%\",\n" +
                "            \"orderEfficiency\":76.28,\n" +
                "            \"delayDays\":1,\n" +
                "            \"pickup_12h_onlineRate\":\"0.00%\",\n" +
                "            \"pickupDate\":\"2019-07-09\",\n" +
                "            \"pickupCode\":\"D\",\n" +
                "            \"order_72h_onlineRate\":\"72.00%\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"pickup_over_24h_onlineNums\":13,\n" +
                "            \"order_72h_onlineNums\":38,\n" +
                "            \"onlineRate\":\"100.00%\",\n" +
                "            \"pickup_3h_onlineNums\":0,\n" +
                "            \"queryDate\":\"2019-07-11\",\n" +
                "            \"order_24h_onlineNums\":2,\n" +
                "            \"order_48h_onlineRate\":\"48.93%\",\n" +
                "            \"pickup_6h_onlineNums\":0,\n" +
                "            \"channelCode\":\"1#\",\n" +
                "            \"order_over_72h_onlineNums\":8,\n" +
                "            \"order_24h_onlineRate\":\"4.25%\",\n" +
                "            \"pickup_24h_onlineNums\":33,\n" +
                "            \"pickup_6h_onlineRate\":\"0.00%\",\n" +
                "            \"order_48h_onlineNums\":23,\n" +
                "            \"order_over_72h_onlineRate\":\"17.02%\",\n" +
                "            \"pickup_12h_onlineNums\":0,\n" +
                "            \"pickupNums\":47,\n" +
                "            \"pickupEfficiency\":22.9,\n" +
                "            \"pickup_over_24h_onlineRate\":\"27.65%\",\n" +
                "            \"onlineNums\":47,\n" +
                "            \"pickup_3h_onlineRate\":\"0.00%\",\n" +
                "            \"pickup_24h_onlineRate\":\"70.21%\",\n" +
                "            \"orderEfficiency\":64.77,\n" +
                "            \"delayDays\":1,\n" +
                "            \"pickup_12h_onlineRate\":\"0.00%\",\n" +
                "            \"pickupDate\":\"2019-07-10\",\n" +
                "            \"pickupCode\":\"D\",\n" +
                "            \"order_72h_onlineRate\":\"80.85%\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"titles1\":{\n" +
                "        \"queryDate\":\"查询日期\",\n" +
                "        \"pickupDate\":\"发货日期\",\n" +
                "        \"delayDays\":\"延后/天\",\n" +
                "        \"channelCode\":\"渠道\",\n" +
                "        \"pickupCode\":\"代码\",\n" +
                "        \"pickupNums\":\"发货数\",\n" +
                "        \"onlineNums\":\"上网数\",\n" +
                "        \"onlineRate\":\"上网率\",\n" +
                "        \"pickupEfficiency\":\"以发货时间\",\n" +
                "        \"pickup_3h_onlineRate\":\"以发货时间\",\n" +
                "        \"pickup_3h_onlineNums\":\"以发货时间\",\n" +
                "        \"pickup_6h_onlineRate\":\"以发货时间\",\n" +
                "        \"pickup_6h_onlineNums\":\"以发货时间\",\n" +
                "        \"pickup_12h_onlineRate\":\"以发货时间\",\n" +
                "        \"pickup_12h_onlineNums\":\"以发货时间\",\n" +
                "        \"pickup_24h_onlineNums\":\"以发货时间\",\n" +
                "        \"pickup_24h_onlineRate\":\"以发货时间\",\n" +
                "        \"pickup_over_24h_onlineNums\":\"以发货时间\",\n" +
                "        \"pickup_over_24h_onlineRate\":\"以发货时间\",\n" +
                "        \"orderEfficiency\":\"以导入时间\",\n" +
                "        \"order_24h_onlineRate\":\"以导入时间\",\n" +
                "        \"order_24h_onlineNums\":\"以导入时间\",\n" +
                "        \"order_48h_onlineNums\":\"以导入时间\",\n" +
                "        \"order_48h_onlineRate\":\"以导入时间\",\n" +
                "        \"order_72h_onlineNums\":\"以导入时间\",\n" +
                "        \"order_72h_onlineRate\":\"以导入时间\",\n" +
                "        \"order_over_72h_onlineNums\":\"以导入时间\",\n" +
                "        \"order_over_72h_onlineRate\":\"以导入时间\"\n" +
                "    },\n" +
                "    \"titles2\":{\n" +
                "        \"queryDate\":\"查询日期\",\n" +
                "        \"pickupDate\":\"发货日期\",\n" +
                "        \"delayDays\":\"延后/天\",\n" +
                "        \"channelCode\":\"渠道\",\n" +
                "        \"pickupCode\":\"代码\",\n" +
                "        \"pickupNums\":\"发货数\",\n" +
                "        \"onlineNums\":\"上网数\",\n" +
                "        \"onlineRate\":\"上网率\",\n" +
                "        \"pickupEfficiency\":\"平均时效/h\",\n" +
                "        \"pickup_3h_onlineNums\":\"3h上网数\",\n" +
                "        \"pickup_3h_onlineRate\":\"3h上网率\",\n" +
                "        \"pickup_6h_onlineNums\":\"6h上网数\",\n" +
                "        \"pickup_6h_onlineRate\":\"6h上网率\",\n" +
                "        \"pickup_12h_onlineNums\":\"12h上网数\",\n" +
                "        \"pickup_12h_onlineRate\":\"12h上网率\",\n" +
                "        \"pickup_24h_onlineNums\":\"24h上网数\",\n" +
                "        \"pickup_24h_onlineRate\":\"24h上网率\",\n" +
                "        \"pickup_over_24h_onlineNums\":\"超一天上网数\",\n" +
                "        \"pickup_over_24h_onlineRate\":\"超一天上网率\",\n" +
                "        \"orderEfficiency\":\"平均时效/h\",\n" +
                "        \"order_24h_onlineNums\":\"24h上网数\",\n" +
                "        \"order_24h_onlineRate\":\"24h上网率\",\n" +
                "        \"order_48h_onlineNums\":\"48h上网数\",\n" +
                "        \"order_48h_onlineRate\":\"48h上网率\",\n" +
                "        \"order_72h_onlineNums\":\"72h上网数\",\n" +
                "        \"order_72h_onlineRate\":\"72h上网率\",\n" +
                "        \"order_over_72h_onlineNums\":\"超3天上网数\",\n" +
                "        \"order_over_72h_onlineRate\":\"超3天上网率\"\n" +
                "    }\n" +
                "}";
        Map<String, Object> oriData = JSON.parseObject(dataStr, LinkedHashMap.class, Feature.OrderedField);
        exportOnlineStatistic(response
                , "Channel Neme"
                , (List<Map<String, Object>>)oriData.get("datas")
                , (Map<String, String>)oriData.get("titles1")
                , (Map<String, String>)oriData.get("titles2"));
    }

    public void exportOnlineStatistic (HttpServletResponse response, String fileName, List<Map<String, Object>> list,
                                        Map<String, String> titles1, Map<String, String> titles2) throws Exception{
        //组装Excel
        XSSFWorkbook wb = new XSSFWorkbook();
        //创建sheet
        XSSFSheet sheet =wb.createSheet("sheet1");

        /*************************标题**************************************/
        XSSFRow title0=sheet.createRow(0);
        XSSFRow title1=sheet.createRow(1);

        //标题样式
        XSSFCellStyle cellStyleTitle= setCellStyle(wb.createCellStyle(), HSSFColor.GOLD.index, XSSFCellStyle.ALIGN_CENTER, "1,1,1,1", true);
        XSSFFont titleFont = setFontStyle(wb.createFont(), HSSFFont.BOLDWEIGHT_BOLD, 10);
        titleFont.setFontName("宋体");
        cellStyleTitle.setFont(titleFont);//标题字体样式

        //插入标题1
        Set<String> keySet = titles1.keySet();
        List<String> keys = new ArrayList<>(keySet);
        int pickupIndexCount = 0;
        for(int k = 0; k<keys.size(); k++){
            if(keys.get(k).startsWith("pickup_")){
                pickupIndexCount++;
            }
            XSSFCell cell0 = title0.createCell(k);
            XSSFCell cell1 = title1.createCell(k);

            cell0.setCellStyle(cellStyleTitle);
            cell1.setCellStyle(cellStyleTitle);

            cell0.setCellValue(titles1.get(keys.get(k)));
            cell1.setCellValue(titles2.get(keys.get(k)));
            sheet.setColumnWidth(k, 8 * 256);
            if(keys.get(k).endsWith("Date")){
                sheet.setColumnWidth(k, 10 * 256);
            }
        }

        //合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 9 + pickupIndexCount, keys.size() - 1));
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 8, 8+pickupIndexCount));
        for (int i = 0; i < 8; i++) {
            sheet.addMergedRegion(new CellRangeAddress(0, 1, i, i));
        }

        /***************数据*****************************/
        //数据样式
        XSSFCellStyle cellStyleContent0= setCellStyle(wb.createCellStyle(), HSSFColor.LEMON_CHIFFON.index, XSSFCellStyle.ALIGN_CENTER, "1,1,1,1", false);
        XSSFCellStyle cellStyleContent1= setCellStyle(wb.createCellStyle(), HSSFColor.LIGHT_TURQUOISE.index, XSSFCellStyle.ALIGN_CENTER, "1,1,1,1", false);
        XSSFCellStyle cellStyleContent2= setCellStyle(wb.createCellStyle(), HSSFColor.LIGHT_GREEN.index, XSSFCellStyle.ALIGN_CENTER, "1,1,1,1", false);

        XSSFDataFormat df = wb.createDataFormat();
        for(int d = 0; d < list.size(); d++){
            //装载数据
            XSSFRow row = sheet.createRow(d+2);
            for(int r = 0; r<keys.size(); r++){
                XSSFCell cell = row.createCell(r);
                String key = keys.get(r);
                Object obj = list.get(d).get(keys.get(r));

                if(key.startsWith("pickup_") || key.equals("pickupEfficiency")){
                    setDetails(cell, cellStyleContent1, wb.createFont(), key, obj, df);
                }else if(key.startsWith("order_") || key.equals("orderEfficiency")){
                    setDetails(cell, cellStyleContent2, wb.createFont(), key, obj, df);
                }else{
                    setDetails(cell, cellStyleContent0, wb.createFont(), key, obj, df);
                }

                cell.setCellValue(obj + "");
            }
            row.setHeight((short)(256 * 1.2));
        }

        //Excel文件写到输出流
        OutputStream output=response.getOutputStream();

        response.setHeader("Content-disposition", "attachment; filename="+fileName+".xlsx");
        response.setContentType("application/msexcel");

        wb.write(output);

        //关闭流
        output.close();
    }

    private void setDetails(XSSFCell cell, XSSFCellStyle cellStyleContent, XSSFFont fort, String key, Object obj, XSSFDataFormat df) {
        XSSFCellStyle style = (XSSFCellStyle)cellStyleContent.clone();
        setFontStyle(fort, 0, 10);//标题字体样式
        fort.setFontName(HSSFFont.FONT_ARIAL);
        style.setFont(fort);
        cell.setCellStyle(style);
        if((key.endsWith("Nums") || key.equals("delayDays")) && obj != null){
            cell.setCellType(cell.CELL_TYPE_NUMERIC);
            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));
            cell.setCellValue(Double.valueOf(obj.toString()));
        }else if(key.endsWith("Efficiency") && obj != null){
            cell.setCellType(cell.CELL_TYPE_NUMERIC);
            style.setDataFormat(df.getFormat("0.00"));
            cell.setCellValue(Double.valueOf(obj.toString()));
        }else if(key.endsWith("Rate") && obj != null){
            Double value = Double.valueOf(obj.toString().replace("%", ""));
            if(value < 85){
                fort.setColor(HSSFColor.RED.index);
            }else if(value > 95){
                fort.setColor(HSSFColor.GREEN.index);
            }
            cell.setCellType(cell.CELL_TYPE_NUMERIC);
            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
            cell.setCellValue(value);
        }else {
            cell.setCellValue(obj == null? null: obj.toString());
        }
    }

    /**
     * 设置表格中的字体
     * @param font
     * @param boldweightBold 字体样式
     * @param size 字体大小
     * @return
     */
    private XSSFFont setFontStyle(XSSFFont font, int boldweightBold, int size) {
        font.setBoldweight((short)boldweightBold); //粗体
        font.setFontHeightInPoints((short) size); //字体大小
        return font;
    }

    /**
     * 设置表格样式
     * @param cellStyle 表格样式 XSSFCellStyle
     * @param color 表格颜色 HSSFColor
     * @param center 是否居中 HSSFCellStyle
     * @param border 边框 上下左右"1,1,1,1" HSSFCellStyle
     * @param isText 是否文本
     * @return
     */
    private XSSFCellStyle setCellStyle(XSSFCellStyle cellStyle, int color, int center, String border, boolean isText) {
        cellStyle.setFillPattern(XSSFCellStyle.FINE_DOTS);
        cellStyle.setFillBackgroundColor((short)color); //背景
        cellStyle.setFillForegroundColor((short)color); //前景
        cellStyle.setWrapText(isText); //自动换行
        cellStyle.setAlignment((short)center); // 水平居中
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER); // 垂直居中

        //边框
        if(StringUtils.isNotBlank(border)){
            String[] borders = border.split(",");
            cellStyle.setBorderTop(StringUtils.isBlank(borders[0])? null: Short.parseShort(borders[0]));//上边框    
            cellStyle.setBorderBottom(StringUtils.isBlank(borders[1])? null: Short.parseShort(borders[1]));//下边框    
            cellStyle.setBorderLeft(StringUtils.isBlank(borders[2])? null: Short.parseShort(borders[2]));//左边框    
            cellStyle.setBorderRight(StringUtils.isBlank(borders[3])? null: Short.parseShort(borders[3]));//右边框
        }
        return cellStyle;
    }
}
