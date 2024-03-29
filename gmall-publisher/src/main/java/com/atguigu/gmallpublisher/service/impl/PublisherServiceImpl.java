package com.atguigu.gmallpublisher.service.impl;

import com.atguigu.gmallpublisher.mapper.DauMapper;
import com.atguigu.gmallpublisher.mapper.OrderMapper;
import com.atguigu.gmallpublisher.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private DauMapper dauMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Integer getSelectDauTotal(String date) {
        return dauMapper.selectDauTotal(date);
    }

    @Override
    public Map getSelectDauHour(String date) {
        //1.获取Mapper查出来的数据
        List<Map> list = dauMapper.selectDauTotalHourMap(date);

        //2.遍历List集合拿出每一个map将其重组成新的map
        HashMap<String, Long> resultMap = new HashMap<>();
        for (Map map : list) {
            resultMap.put((String) map.get("LH"), (Long) map.get("CT"));
        }
        return resultMap;
    }

    @Override
    public Double getSelectGmvTotal(String date) {
        return orderMapper.selectOrderAmountTotal(date);
    }

    @Override
    public Map getSelectGmvHour(String date) {
        //1.获取通过sql查询出来的数据
        List<Map> list = orderMapper.selectOrderAmountHourMap(date);

        //2.创建新的map集合用来改变数据结构
        HashMap<String, Double> result = new HashMap<>();
        for (Map map : list) {
            result.put((String) map.get("CREATE_HOUR"), (Double) map.get("SUM_AMOUNT"));
        }
        return result;
    }
}
