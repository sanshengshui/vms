**Vms**

操作系统:Ubantu16.04
编程语言:java,项目管理工具:maven,版本控制工具:在公司主要使用的是svn,为了个人的方便,我使用git来管理项目。
开发IDE:intelliJ IDEA  
项目中主要使用的技术:spring4.2 mybatis3.3 vaddin 7.6.4 resteasy 3.0.10. Final mysql5.6 .springmvc 
使用json格式的数据进行数据传输，使用vaddin对数据进行页面上的呈现。
vms对无线通讯收集来的数据进行处理。数据的持久性处理和网页上的对无线网关的无线控制。
项目描述:基于zigbee无线通讯方面的工业控制系统:利用zigbee无线模块对公司的电能，温湿度，等参数的采集，
利用网关zigbee-tcp/ip将数据上传到服务器上，服务器对数据进行持久化处理，以及实时的反映到公司的页面上，
通知方便人员进行管理和提高工作效率，以防不必要的损失和重大灾情的出现   
配置简介  
推荐使用如下方法配置java运行环境和maven(虽然不是很快,但很便捷,免于写环境变量)  
[!SDKMAN](http://sdkman.io/)  

Installing SDKMAN! on UNIX-like platforms is as easy as ever.  
 SDKMAN! installs smoothly on Mac OSX, Linux, Cygwin, Solaris and FreeBSD. We also support Bash and ZSH shells.  
Simply open a new terminal and enter:  

$ curl -s "https://get.sdkman.io" | bash  

Follow the instructions on-screen to complete installation.  

Next, open a new terminal or enter:  
$ source "$HOME/.sdkman/bin/sdkman-init.sh"  

Lastly, run the following code snippet to ensure that installation succeeded:  

$ sdk version  

If all went well, the version should be displayed. Something like:  

  sdkman 5.0.0+51
  
有时你可能安装不上,可能你的原因存在于!sdkman没有操作/usr/local的权限  　
sudo chmod 777 -R /usr/local  
sdk install java  
sdk install maven  

使用方法:  
1.git clone https://github.com/sanshengshui/vms  
2.mvn jetty:run  
3.Browse the web type url https://localhost:8080/  

获取JSON数据　　
你可以使用DHC BY RESTLET  


---------------------------------------------------
**获取工站信息**  
GET: http://localhost:8080/api/v1/workstations?terminal-code=T001  

{"stationId":"WS001", "stationName":"FPC显检", "lotNum":"37002324", "modelNum":"NJ1234-01",  
"productLine":{"lineId":"L001", "lineCode":"J1-A-01", "defectVersion":"04061123",  
"terminalCode":{"terminalType":"DISPLAY"}}

---------------------------------------------------
**获取缺陷列表**  
GET: http://localhost:8080/api/v1/defects?line-id=L1  
{  
  "defects":[  
    {"defectId":"DF001", "defectName":"缺陷1", "shortcut":"123","WS001"}  
    {"defectId":"DF002", "defectName":"缺陷2", "shortcut":"124","WS002"}  
  ]  
}  

---------------------------------------------------
**提交缺陷记录**  
POST: http://localhost:8080/api/v1/defect-records/  
{"recordId":"REC001", "defectId":"DF001", "lotNum":"", "count":"1", "terminalCode":"T001", "recordTime":"1213124"}  

---------------------------------------------------

**更新终端用户**  
PUT: http://localhost:8080/api/v1/terminals/operator/  
{"terminalCode":"T001", "operator":"XXXX", "updateTime":"134234242"}  

---------------------------------------------------  

--------------------------分割线--------------------  

**获取工站下终端列表**  
GET: http://localhost:8080/api/v1/terminals?terminal-code=T001  
[{"terminalCode":"T001","terminalType":"DISPLAY"}]  

---------------------------end----------------------  

**更新终端与工站的关系**  
PUT: http://host/api/v1/  
{"terminalCode":"T001", "stationId":"WS001", "updateTime":"134234242"}  

---------------------------------------------------  

**获取员工信息(根据ID卡号)**  
GET: http://localhost:8080/api/v1/employees?card-code=0083  
{"employeeId":"110347", "employeeName":"Tom"}  

----------------------------------------------------
**获取员工信息(根据工号)**  
GET: http://localhost:8080/api/v1/employees/110347  
{"employeeId":"110347", "employeeName":"Tom"}  

---------------------------------------------------  

**获取缺陷统计信息(根据终端编号)**  
GET: http://localhost:8080/api/v1/defect-statistics?terminal-code=T001  
{  
        {"defectId":"DF001", "count":"1500", "percent":"0"},  
        {"defectId":"DF002", "count":"1000", "percent":"0"},  
        
}   



