package com.mektec.vms.service.Impl;

import com.mektec.vms.config.PropertiesManager;
import com.mektec.vms.domain.BaseInfo;
import com.mektec.vms.mapper.BaseInfoMapper;
import com.mektec.vms.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Properties;


@Service
@Transactional
public class SettingServiceImpl implements SettingService{

    private BaseInfoMapper baseInfoMapper;

    @Override
    public String getValue(String key, String defaultValue) {
        BaseInfo setting = baseInfoMapper.selectInfoById(key);
        if(setting == null) {
            return defaultValue;
        } else {
            return setting.getValue();
        }

    }

    @Override
    public Boolean getValue(String key, Boolean defaultValue) {
        String value = getValue(key, defaultValue.toString());
        return Boolean.parseBoolean(value);
    }

    @Override
    public Integer getValue(String key, Integer defaultValue) {
        String value = getValue(key, defaultValue.toString());
        return Integer.parseInt(value);
    }

    @Override
    public Float getValue(String key, Float defaultValue) {
        String value = getValue(key, defaultValue.toString());

        return Float.parseFloat(value);
    }

    @Override
    public void setValue(String key, String value) {
        setValue(key,value,"");
    }

    @Override
    public void setValue(String key, Boolean value) {
        setValue(key, value.toString());
    }

    @Override
    public void setValue(String key, Integer value) {
        setValue(key, value.toString());
    }

    @Override
    public void setValue(String key, Float value) {
        setValue(key, value.toString());
    }

    @Override
    public void setValue(String key, String value, String purpose) {
        BaseInfo setting = new BaseInfo();
        setting.setBaseId(key);
        setting.setValue(value);
        setting.setPurpose(purpose);

        if(baseInfoMapper.selectInfoById(key) == null) {
            baseInfoMapper.insertInfo(setting);
        } else {
            baseInfoMapper.updateInfo(setting);
        }
    }

    @Override
    public String getLocalValue(String key, String defaultValue) {
        Properties properties = null;
        if(!PropertiesManager.exists(PropertiesManager.SETTING_FILE)){
            return defaultValue;
        }
        //从外部加载
        properties = PropertiesManager.loadFromExternal(
                PropertiesManager.SETTING_FILE);
        if(properties == null) {
            return defaultValue;
        }

        return properties.getProperty(key, defaultValue);
    }

    @Override
    public Boolean getLocalValue(String key, Boolean defaultValue) {

        String value = getLocalValue(key, defaultValue.toString());
        return Boolean.parseBoolean(value);
    }

    @Override
    public Integer getLocalValue(String key, Integer defaultValue) {
        String value = getLocalValue(key, defaultValue.toString());
        return Integer.parseInt(value);
    }

    @Override
    public Float getLocalValue(String key, Float defaultValue) {

        String value = getLocalValue(key, defaultValue.toString());
        return Float.parseFloat(value);
    }

    @Override
    public void setLocalValue(String key, String value) {
        Properties properties = null;
        //先加载
        properties = PropertiesManager.loadFromExternal(
                PropertiesManager.SETTING_FILE);
        if(properties == null) {
            properties = new Properties();
        }

        properties.setProperty(key, value);

        try {
            PropertiesManager.save(properties, PropertiesManager.SETTING_FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setLocalValue(String key, Boolean value) {
        setLocalValue(key, value.toString());
    }

    @Override
    public void setLocalValue(String key, Integer value) {
        setLocalValue(key, value.toString());
    }

    @Override
    public void setLocalValue(String key, Float value) {
        setLocalValue(key, value.toString());
    }

    @Autowired
    public void setBaseInfoMapper(BaseInfoMapper baseInfoMapper) {
        this.baseInfoMapper = baseInfoMapper;
    }
}
