package com.mektec.vms.service;

import com.mektec.vms.domain.ProductLine;

import java.util.List;

/**
 * Created by mektec on 16-3-30.
 */
public interface ProductLineService {
    //基本的CRUD
    void createLine(ProductLine productLine);
    ProductLine findLineById(String lineId);
    void updateLine(ProductLine productLine);
    void deleteLine(ProductLine productLine);

    //
    List<ProductLine> findLineListByLocation(String locationId);
    ProductLine findLineByLineCode(String lineCode);

    ProductLine findPassCountByLineCode(String lineCode);
    //通过lineCode找到产线上通过的PassCount;

    List<ProductLine> findAllLine();
}
