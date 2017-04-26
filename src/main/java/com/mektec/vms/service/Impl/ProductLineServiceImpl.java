package com.mektec.vms.service.Impl;

import com.mektec.vms.domain.ProductLine;
import com.mektec.vms.mapper.ProductLineMapper;
import com.mektec.vms.service.ProductLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mektec on 16-3-30.
 */
@Service
@Transactional
public class ProductLineServiceImpl implements ProductLineService{

    private ProductLineMapper productLineMapper;

    @Autowired
    public void setProductLineMapper(ProductLineMapper productLineMapper) {
        this.productLineMapper = productLineMapper;
    }

    @Override
    public void createLine(ProductLine productLine) {

        productLineMapper.createProductLine(productLine);
    }

    @Override
    public ProductLine findLineById(String lineId) {

        return productLineMapper.findLineById(lineId);
    }

    @Override
    public ProductLine findPassCountByLineCode(String lineCode) {
        return productLineMapper.findPassCountByLineCode(lineCode);
    }

    @Override
    public void updateLine(ProductLine productLine) {
        productLineMapper.updateLine(productLine);
    }

    @Override
    public void deleteLine(ProductLine productLine) {
        productLineMapper.deleteLine(productLine);
    }

    @Override
    public List<ProductLine> findLineListByLocation(String locationId) {

        return productLineMapper.findLineListByLocation(locationId);
    }

    @Override
    public ProductLine findLineByLineCode(String lineCode) {
        return productLineMapper.findLineByLineCode(lineCode);
    }

    @Override
    public List<ProductLine> findAllLine() {
        return productLineMapper.findAllLine();
    }
}
