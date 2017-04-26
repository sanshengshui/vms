package com.mektec.vms.service;

import com.mektec.vms.domain.Lot;

import java.util.List;

/**
 * Created by mektec on 16-3-30.
 */
public interface LotService {

    void createLot(Lot lot);
    Lot findLotByLotNum(String lotNum);
    void updateLot(Lot lot);
    void deleteLot(Lot lot);
    List<Lot> findAllLots();
}
