package com.comlink.dao;

import java.util.List;
import java.util.Map;

import com.comlink.model.DNIS;
import com.comlink.model.DNISTarget;
import com.comlink.model.GATEWAYS;
import com.comlink.model.GATEWAYSHost;
 

public interface DnisDao 
{ 
	public List<DNIS> getDnisByparameter(String status,
			String prefix_mapp, String file, String did, String ticnumber,String gatewayname,String datem);
	public List<DNIS> getDownloadReportList(String status,
			String prefix_dnis, String file, String did, String ticnumber,String gatewayname,String datem);
	public int uploadFile(String s);
	public void uploadFileData( List<DNIS> dnislst,String action);
	public int uploadFile(DNIS dnis);
	public int uploadData(String s);
	public List<DNISTarget> getAllDnis();
	public Map<DNIS,Object> getDnis(DNIS dni);
	public List<String> getGateGroupName();
	
	public int updatemodifyAction(String recid, String status);
	public int updateaddAction(String recid, String status);
	public int updatedeleteAction(String recid, String status);
	public int updateundeleteAction(String recid, String status);
	public boolean isgroupNameExist(String groupName);
	public boolean isDIDExist(String didNumber);
	public int uploadFileDelete(DNIS dnis);
	public int uploadFileModify(DNIS dnis);
	public int addAction(DNIS dnis);
	List<DNISTarget> getAllgetwaysGroup();
	List<GATEWAYS> getwaysfromName();
	List<GATEWAYSHost> getgetways();
	//public List<String> enumval();
	public int modify(DNIS dnis);
	
	public List<String> getGatewaysfromDb();
}


 