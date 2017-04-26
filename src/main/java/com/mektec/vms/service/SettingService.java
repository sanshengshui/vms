package com.mektec.vms.service;
public interface SettingService {
	//
	public String getValue(String key, String defaultValue);

	public Boolean getValue(String key, Boolean defaultValue);

	public Integer getValue(String key, Integer defaultValue);

	public Float getValue(String key, Float defaultValue);
	
	public void setValue(String key, String value);
    public void setValue(String key, Boolean value);
    public void setValue(String key, Integer value);
    public void setValue(String key, Float value);

    public void setValue(String key, String value, String purpose);

	public String getLocalValue(String key, String defaultValue);
    public Boolean getLocalValue(String key, Boolean defaultValue);

    public Integer getLocalValue(String key, Integer defaultValue);

    public Float getLocalValue(String key, Float defaultValue);

	public void setLocalValue(String key, String value);
    public void setLocalValue(String key, Boolean value);
    public void setLocalValue(String key, Integer value);
    public void setLocalValue(String key, Float value);
	
}
