package com.comlink.dao.impl;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.comlink.dao.DnisDao;
import com.comlink.model.DNIS;
import com.comlink.model.DNISTarget;
import com.comlink.model.GATEWAYS;
import com.comlink.model.GATEWAYSHost;
import com.comlink.rowmapper.DnisDIDMapper;
import com.comlink.rowmapper.DnisListMapper;
import com.comlink.rowmapper.DnisTargetMapper;
import com.comlink.rowmapper.GatewaysHostMapper;
import com.comlink.rowmapper.GatewaysMapper;
import com.comlink.rowmapper.GroupNameMapper;
import com.mysql.jdbc.Connection;

public class DnisDaoImpl implements DnisDao {
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public int uploadFile(DNIS dnis) {
	    return jdbcTemplate.update("call uploadFile(?)",dnis.getFilename());
    }
    
	public int uploadFile(String s) {
		 return jdbcTemplate.update("call upload_file(?)",s);
	}
    
    public int uploadData(String s) {
		 return jdbcTemplate.update("call uploadData(?)",s);
	}

    public List<DNISTarget> getAllDnis() {
		 List<DNISTarget> list = jdbcTemplate.query("select DIDnumber as dnis, concat(Prefix, MappedNumber) as Target from dnism where Status ='Active'", new DnisTargetMapper());
	  return list;
	 
    }
    public List<DNISTarget> getAllgetwaysGroup() {
		 List<DNISTarget> list = jdbcTemplate.query("select GatewayGroupName as dnis, concat(Prefix, MappedNumber) as Target from dnism where Status ='Active'", new DnisTargetMapper());
	  return list;
	 
    }
    public List<GATEWAYSHost> getgetways() {
		 List<GATEWAYSHost> list = jdbcTemplate.query("select GatewayName as id , GateWayIP as host ,TrunkGroupId as trunkGroupId from  gateways", new GatewaysHostMapper());
	  return list;
	 
    }
    public List<GATEWAYS> getwaysfromName() {
    	
    	String sql = "select dnism.GatewayGroupName, "
    			+"gatewaygroup.GatewayID1Value, "
    			+"gatewaygroup.GatewayID2Value, "
    			+"gatewaygroup.GatewayID3Value, "
    			+"gatewaygroup.GatewayID4Value, "
    			+"gatewaygroup.GatewayID5Value, "
    			+"gatewaygroup.GatewayID6Value, "
				+"g1.GateWayName as RoutingIP1, "
				+"g2.GateWayName as RoutingIP2, "
				+"g3.GateWayName as RoutingIP3, "
				+"g4.GateWayName as RoutingIP4, "
				+"g5.GateWayName as RoutingIP5, "
				+"g6.GateWayName as RoutingIP6 "
				+"from dnism,gatewaygroup,gateways g1,gateways g2,gateways g3,gateways g4,gateways g5,gateways g6 "
				+"where dnism.GatewayGroupName=gatewaygroup.GatewayGroupName "
				+"and gatewaygroup.GatewayID1=g1.RecordId "
				+"and gatewaygroup.GatewayID2=g2.RecordId "
				+"and gatewaygroup.GatewayID3=g3.RecordId "
				+"and gatewaygroup.GatewayID4=g4.RecordId "
				+"and gatewaygroup.GatewayID5=g5.RecordId "
				+"and gatewaygroup.GatewayID6=g6.RecordId group by dnism.GatewayGroupName";
		 List<GATEWAYS> list = jdbcTemplate.query(sql, new GatewaysMapper());
	  return list;
	 
    }
    
    public Map<DNIS, Object> getDnis(DNIS dni) {
		 Map<DNIS, Object> map = (Map<DNIS, Object>) jdbcTemplate.query("call get_dnis()",
			new DnisListMapper());
	  return map;
	}

	public List<DNIS> getDnisByparameter(String status, String prefix_mapp,
			String file, String did, String ticnumber, String gatewayname,String datem) {
		String condition = "";
		if ((status != null) && (!status.isEmpty())) {
			condition = condition + "dnism.Status like '%" + status + "%'AND ";
		}
		if ((prefix_mapp != null) && (!prefix_mapp.isEmpty())) {
			condition = condition + "concat(dnism.Prefix,dnism.MappedNumber) like '%"
					+ prefix_mapp + "%' AND ";
		}
		if ((datem != null) && (!datem.isEmpty())) {
			condition = condition + "dnism.DateModified like '" + datem + "%' AND ";
		}
		if ((file != null) && (!file.isEmpty())) {
			condition = condition + "dnism.Filename like '%" + file + "%' AND ";
		}
		if ((did != null) && (!did.isEmpty())) {
			condition = condition + "dnism.DIDnumber like '%" + did + "%' AND ";
		}
		if ((ticnumber != null) && (!ticnumber.isEmpty())) {
			condition = condition + "dnism.TicketOrderNum like '%" + ticnumber + "%' AND ";
		}
		if ((gatewayname != null) && (!gatewayname.isEmpty())) {
			condition = condition + "dnism.GatewayGroupName like '%" + gatewayname + "%' AND ";
		}
		String sql = null;
		if (condition.isEmpty()) {
			sql = "select dnism.*, "
					+"g1.GateWayIP as RoutingIP1, "
					+"g2.GateWayIP as RoutingIP2, "
					+"g3.GateWayIP as RoutingIP3, "
					+"g4.GateWayIP as RoutingIP4, "
					+"g5.GateWayIP as RoutingIP5, "
					+"g6.GateWayIP as RoutingIP6 "
					+"from dnism,gatewaygroup,gateways g1,gateways g2,gateways g3,gateways g4,gateways g5,gateways g6 "
					+"where dnism.GatewayGroupName=gatewaygroup.GatewayGroupName "
					+"and gatewaygroup.GatewayID1=g1.RecordId "
					+"and gatewaygroup.GatewayID2=g2.RecordId "
					+"and gatewaygroup.GatewayID3=g3.RecordId "
					+"and gatewaygroup.GatewayID4=g4.RecordId "
					+"and gatewaygroup.GatewayID5=g5.RecordId "
					+"and gatewaygroup.GatewayID6=g6.RecordId ";
		} else {
			condition = condition.substring(0, condition.length()-4);
			sql = "select dnism.*, "
					+"g1.GateWayIP as RoutingIP1, "
					+"g2.GateWayIP as RoutingIP2, "
					+"g3.GateWayIP as RoutingIP3, "
					+"g4.GateWayIP as RoutingIP4, "
					+"g5.GateWayIP as RoutingIP5, "
					+"g6.GateWayIP as RoutingIP6 "
					+"from dnism,gatewaygroup,gateways g1,gateways g2,gateways g3,gateways g4,gateways g5,gateways g6 "
					+"where dnism.GatewayGroupName=gatewaygroup.GatewayGroupName "
					+"and gatewaygroup.GatewayID1=g1.RecordId "
					+"and gatewaygroup.GatewayID2=g2.RecordId "
					+"and gatewaygroup.GatewayID3=g3.RecordId "
					+"and gatewaygroup.GatewayID4=g4.RecordId "
					+"and gatewaygroup.GatewayID5=g5.RecordId "
					+"and gatewaygroup.GatewayID6=g6.RecordId and "+ condition;
		}

		List<DNIS> list = jdbcTemplate.query(sql, new DnisListMapper());
		return list;
	}




	public List<DNIS> getDownloadReportList(String status, String prefix_mapp,
			String file, String did, String ticnumber, String gatewayname,
			String datem) {
		String condition = "";
		if ((status != null) && (!status.isEmpty())) {
			condition = condition + "dnism.Status like '%" + status + "%' AND ";
		}
		if ((prefix_mapp != null) && (!prefix_mapp.isEmpty())) {
			condition = condition + "concat(dnism.Prefix,dnism.MappedNumber) like '%"
					+ prefix_mapp + "%' AND ";
		}
		if ((datem != null) && (!datem.isEmpty())) {
			condition = condition + "dnism.DateModified like '" + datem + "%' AND ";
		}
		if ((file != null) && (!file.isEmpty())) {
			condition = condition + "dnism.Filename like '%" + file + "%' AND ";
		}
		if ((did != null) && (!did.isEmpty())) {
			condition = condition + "dnism.DIDnumber like '%" + did + "%' AND ";
		}
		if ((ticnumber != null) && (!ticnumber.isEmpty())) {
			condition = condition + "dnism.TicketOrderNum like '%" + ticnumber + "%' AND ";
		}
		if ((gatewayname != null) && (!gatewayname.isEmpty())) {
			condition = condition + "dnism.GatewayGroupName like '%" + gatewayname + "%' AND ";
		}
		String sql = null;
		if (condition.isEmpty()) {
			sql = "select dnism.*, "
					+"g1.GateWayIP as RoutingIP1, "
					+"g2.GateWayIP as RoutingIP2, "
					+"g3.GateWayIP as RoutingIP3, "
					+"g4.GateWayIP as RoutingIP4, "
					+"g5.GateWayIP as RoutingIP5, "
					+"g6.GateWayIP as RoutingIP6 "
					+"from dnism,gatewaygroup,gateways g1,gateways g2,gateways g3,gateways g4,gateways g5,gateways g6 "
					+"where dnism.GatewayGroupName=gatewaygroup.GatewayGroupName "
					+"and gatewaygroup.GatewayID1=g1.RecordId "
					+"and gatewaygroup.GatewayID2=g2.RecordId "
					+"and gatewaygroup.GatewayID3=g3.RecordId "
					+"and gatewaygroup.GatewayID4=g4.RecordId "
					+"and gatewaygroup.GatewayID5=g5.RecordId "
					+"and gatewaygroup.GatewayID6=g6.RecordId ";
		} else {
			condition = condition.substring(0, condition.length()-4);
			sql = "select dnism.*, "
					+"g1.GateWayIP as RoutingIP1, "
					+"g2.GateWayIP as RoutingIP2, "
					+"g3.GateWayIP as RoutingIP3, "
					+"g4.GateWayIP as RoutingIP4, "
					+"g5.GateWayIP as RoutingIP5, "
					+"g6.GateWayIP as RoutingIP6 "
					+"from dnism,gatewaygroup,gateways g1,gateways g2,gateways g3,gateways g4,gateways g5,gateways g6 "
					+"where dnism.GatewayGroupName=gatewaygroup.GatewayGroupName "
					+"and gatewaygroup.GatewayID1=g1.RecordId "
					+"and gatewaygroup.GatewayID2=g2.RecordId "
					+"and gatewaygroup.GatewayID3=g3.RecordId "
					+"and gatewaygroup.GatewayID4=g4.RecordId "
					+"and gatewaygroup.GatewayID5=g5.RecordId "
					+"and gatewaygroup.GatewayID6=g6.RecordId and "+ condition;
		}

		List<DNIS> list = jdbcTemplate.query(sql, new DnisListMapper());
		return list;
	}



	 

	public List<String> getGateGroupName(){
		String sql="select gatewayGroupName from gatewaygroup";
		return jdbcTemplate.query(sql, new GroupNameMapper());
	}

	public void uploadFileData(List<DNIS> dnislst,String action) {
			String addsql = "insert into dnism (Prefix,MappedNumber,CustomerName,TicketOrderNum,DTFS,FileName,UserID,DIDnumber,GatewayGroupName,DateModified)values(?,?,?,?,?,?,?,?,?,?)";

			Calendar calendar = Calendar.getInstance();
		   java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
			jdbcTemplate.batchUpdate(addsql, new BatchPreparedStatementSetter() {				
				public void setValues(PreparedStatement ps, int i)	throws SQLException {
					DNIS dnis = dnislst.get(i);
				    ps.setString(1, dnis.getPrefix());
				    ps.setString(2, dnis.getMnumber());
				    ps.setString(3, dnis.getCname());
				    ps.setString(4, dnis.getTicketno());
				    ps.setString(5, dnis.getDtfs());
				    ps.setString(6, dnis.getFilename());
				    ps.setString(7, dnis.getUserId());
				    ps.setString(8, dnis.getDidNumber());
				    ps.setString(9, dnis.getGatewayName());
				    ps.setString(9, dnis.getGatewayName());
				    ps.setTimestamp(10, ourJavaTimestampObject);
				}

				public int getBatchSize() {
				    return dnislst.size();
				}
			});
	}
	
	public int uploadFileModify(DNIS dnis) { 
		String caction = "MODIFY"; 	
		Calendar calendar = Calendar.getInstance();
		java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
		String updatesql="update  dnism set CurrentAction='"+caction+"', Prefix = '"+dnis.getPrefix()+"', MappedNumber = '"+dnis.getMnumber()+"', CustomerName = '"+dnis.getCname()+"', TicketOrderNum = '"+dnis.getTicketno()+"', DTFS = '"+dnis.getDtfs()+"', FileName = '"+dnis.getFilename()+"', UserID = '"+dnis.getUserId()+"', GatewayGroupName = '"+dnis.getGatewayName()+"', DateModified = '"+ourJavaTimestampObject+"' WHERE DIDnumber = '"+dnis.getDidNumber()+"'";
		return jdbcTemplate.update(updatesql);
	}
	
	
	public int uploadFileDelete(DNIS dnis) { 
		String caction = "DELETE"; 		
	if ("Active".equals(dnis.getStatus())){
		caction = "UNDELETE";
		}		
		Calendar calendar = Calendar.getInstance();
		java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
		String updatesql="update  dnism set CurrentAction='"+caction+"', Status = '"+dnis.getStatus()+"', DateModified = '"+ourJavaTimestampObject+"' WHERE DIDnumber = '"+dnis.getDidNumber()+"'";
		return jdbcTemplate.update(updatesql);
	}
	
	
	

	
	public int addAction(DNIS dnis) {
		Calendar calendar = Calendar.getInstance();
		java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
		String sql="insert into dnism (Prefix,MappedNumber,CustomerName,TicketOrderNum,DTFS,DIDnumber,GatewayGroupName,FileName,DateModified,UserID,InitialActionBy)values('"+dnis.getPrefix()+"','"+dnis.getMnumber()+"','"+dnis.getCname()+"','"+dnis.getTicketno()+"','"+dnis.getDtfs()+"','"+dnis.getDidNumber()+"','"+dnis.getGatewayName()+"', '"+dnis.getFilename()+"', '"+ourJavaTimestampObject+"', '"+dnis.getUserId()+"', '"+dnis.getInitialAction()+"')";
		return   jdbcTemplate.update(sql);  
		
	}

	
	public int updatemodifyAction(String recid, String status) {
		String caction="MODIFY";
		Calendar calendar = Calendar.getInstance();
		java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
		String sql="update  dnism set CurrentAction='"+caction+"', Status='"+status+"' Where  RecordID ='"+recid+"'";
		return jdbcTemplate.update(sql);
	}

	
	public int updateaddAction(String recid, String status) {
		String caction="ADD";
		String sql="update  dnism set CurrentAction='"+caction+"', Status='"+status+"' Where  RecordID ='"+recid+"'";
		return jdbcTemplate.update(sql);
	}

	
	public int updatedeleteAction(String recid, String status) {
		String caction="DELETE";
		Calendar calendar = Calendar.getInstance();
		String actionBy= "Manual";
		java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
		String sql="update  dnism set CurrentAction='"+caction+"', Status='"+status+"' , DateModified ='"+ourJavaTimestampObject+"' ,InitialActionBy ='"+actionBy+"' Where  RecordID ='"+recid+"'";
		return jdbcTemplate.update(sql);
	}

	
	public int updateundeleteAction(String recid, String status) {
		String caction="UNDELETE";
		Calendar calendar = Calendar.getInstance();
		String actionBy= "Manual";
		java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
		String sql="update  dnism set CurrentAction='"+caction+"', Status='"+status+"' , DateModified ='"+ourJavaTimestampObject+"' ,InitialActionBy ='"+actionBy+"'  Where  RecordID ='"+recid+"'";
		return jdbcTemplate.update(sql);
	}

	
	public boolean isgroupNameExist(String groupName) {
		String sql="select gatewayGroupName from gatewaygroup where gatewayGroupName = "+ "'" +groupName + "'";
		List<String> grpListName =  jdbcTemplate.query(sql, new GroupNameMapper()); 
		if(grpListName.size()>0)
		return true;
		else
		return false;
	}

	
	public boolean isDIDExist(String didNumber) {
		String sql="select didNumber from dnism where DIDnumber = "+ "'" +didNumber + "'";
		List<String> grpListName =  jdbcTemplate.query(sql, new DnisDIDMapper());
		if(grpListName.size()>0)
		return true;
		else
		return false;
	}

	
	public int modify(DNIS dnis) {

		String caction="MODIFY";
		String sql="update  dnism set Status='"+dnis.getStatus()+"', CurrentAction='"+caction+"' ,Prefix='"+dnis.getPrefix()+"',MappedNumber='"+dnis.getMnumber()+"',TicketOrderNum='"+dnis.getTicketno()+"',Filename='"+dnis.getFile()+"',GatewayGroupName='"+dnis.getGatewayName()+"' Where  DIDnumber ='"+dnis.getDidNumber()+"'";;
		return jdbcTemplate.update(sql);
	}
	
	
	public List<String> getGatewaysfromDb() 
	{
	
		String sql="select GatewayGroupName as gatewayGroupName from gatewaygroup";
		List<String> gatewaygroupname=  jdbcTemplate.query(sql, new GroupNameMapper());
		return gatewaygroupname;
	}
	
	

	

	
		 
}
