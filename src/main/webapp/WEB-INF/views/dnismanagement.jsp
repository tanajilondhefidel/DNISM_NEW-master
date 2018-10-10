<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Comlink | DNIS Management</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="description" content="Avenger Admin Theme">
<meta name="author" content="KaijuThemes">

<link
	href='http://fonts.googleapis.com/css?family=RobotoDraft:300,400,400italic,500,700'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300,400,400italic,600,700'
	rel='stylesheet' type='text/css'>
<style>

.panel-ctrls {float:left !important;}
.dataTables_filter{display:none !important;}
.dataTables_paginate{float:left !important;}
</style>
<!--[if lt IE 10]>
        <script type="text/javascript" src="assets/js/media.match.min.js"></script>
        <script type="text/javascript" src="assets/js/placeholder.min.js"></script>
    <![endif]-->

<link type="text/css"
	href="assets/fonts/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">

<!-- Font Awesome -->
<link type="text/css" href="assets/css/styles.css" rel="stylesheet">
<!-- Core CSS with all styles -->

<link type="text/css"
	href="assets/plugins/jstree/dist/themes/avenger/style.min.css"
	rel="stylesheet">
<!-- jsTree -->
<link type="text/css" href="assets/plugins/codeprettifier/prettify.css"
	rel="stylesheet">
<!-- Code Prettifier -->
<link type="text/css"
	href="assets/plugins/iCheck/skins/minimal/blue.css" rel="stylesheet">
<!-- iCheck -->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries. Placeholdr.js enables the placeholder attribute -->
<!--[if lt IE 9]>
        <link type="text/css" href="assets/css/ie8.css" rel="stylesheet">
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/respond.js/1.1.0/respond.min.js"></script>
        <script type="text/javascript" src="assets/plugins/charts-flot/excanvas.min.js"></script>
        <script type="text/javascript" src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- The following CSS are included as plugins and can be removed if unused-->

<link type="text/css"
	href="assets/plugins/datatables/dataTables.bootstrap.css"
	rel="stylesheet">
<link type="text/css"
	href="assets/plugins/datatables/dataTables.fontAwesome.css"
	rel="stylesheet">

</head>
<body class="infobar-offcanvas">


	<header id="topnav"
		class="navbar navbar-midnightblue navbar-fixed-top clearfix"
		role="banner">
	<a style="margin-left: 10px;" class="navbar-brand"
		href="dashboard.html">Comlink</a>

	<ul class="nav navbar-nav toolbar pull-right">
		<li class="dropdown toolbar-icon-bg"><a href="#"
			class="dropdown-toggle" data-toggle='dropdown'><span
				class="icon-bg"><i class="fa fa-fw fa-user"></i></span></a>
			<ul class="dropdown-menu userinfo arrow">
				<li><a href="changepassword.html"><span class="pull-left">Change
							Password</span> </a></li>
				<li><a href="logout"><span class="pull-left">Sign
							Out</span> <i class="pull-right fa fa-sign-out"></i></a></li>
			</ul></li>
	</ul>
	</header>
	<div id="wrapper" style="display: block !important">
		<div id="layout-static">
			<div class="static-sidebar-wrapper sidebar-midnightblue">
				<div class="static-sidebar">
					<div class="sidebar">
						<div class="widget stay-on-collapse" id="widget-welcomebox">
							<div class="widget-body welcome-box tabular">
								<div class="tabular-row">
									<div class="tabular-cell welcome-avatar">
										<a href="#"><img src="assets/demo/avatar/avatar_02.png"
											class="avatar"></a>
									</div>
									<div class="tabular-cell welcome-options">
										<span class="welcome-text">Welcome,</span> <a href="#"
											class="name">${sessionuser.firstName}</a>
									</div>
								</div>
							</div>
						</div>
						<div class="widget stay-on-collapse" id="widget-sidebar">
							<nav role="navigation" class="widget-body">
							<ul class="acc-menu">
								<li class="nav-separator">Explore</li>
								<li><a href="dashboard"><i class="fa fa-home"></i><span>Dashboard</span></a></li>
								<c:if test="${sessionuser.type=='Admin'}">
									<li><a href="user"><i class="fa fa-users"></i><span>Users</span></a></li>
									<li><a href="log"><i class="fa fa-bar-chart"></i><span>Log</span></a></li>
								</c:if>
								<li><a href="dnismanagement"><i class="fa fa-cog"></i><span>DNIS
											Management</span></a></li>
								<li><a href="addaction"><i class="fa fa-cog"></i><span>Add
											DNIS</span></a></li>
							</ul>
							</nav>
						</div>
					</div>
				</div>
			</div>
			<div class="static-content-wrapper" style="overflow: hidden">
				<div class="static-content">
					<div class="page-content">
						<div class="page-heading">
							<h1>DNIS Management</h1>
						</div>
						<div class="container-fluid">
							<h6>${message}</h6>
							<div class="row">
								<div class="col-md-12">
									<form modelAttribute="fileBean" method="Post"
										enctype="multipart/form-data" id="formFile" class="clearfix">
										<div class="col-sm-8">
											<div class="col-sm-2 p">
												<label style="" class=" text-center control-label">Staus
													: </label> <input type="hidden" name="action" value="add">
												<select class="form-control" name="Status">
													<option value="" ${status == '' ? 'selected' : ''}>ALL</option>
													<option value="Active"
														${status == 'Active' ? 'selected' : ''}>ACTIVE</option>
													<option value="Deleted"
														${status == 'Deleted' ? 'selected' : ''}>DELETED</option>
												</select>
											</div>
											<div class="col-sm-2 p">
												<label style="" class=" text-center control-label">Prefix+
													Mapping Number : </label> <input type="hidden" name="action"
													value="hi Search me"> <input type="text"
													placeholder="Enter Prefix+ Mapping Number"
													class="form-control" name="prefixmapp"
													value="${prefixmapp}">
											</div>
											<!-- <div class="col-sm-4 p">
												<label style="" class=" text-center control-label"> Date	of Modification	  : </label> 
												<input type="text" placeholder="Enter Date of Modification" class="form-control" name="datem" value="${datem}">
											</div> -->
											<div class="col-sm-2 p">
												<label style="" class=" text-center control-label">Ticket
													Order Number : </label> <input type="text"
													placeholder="Enter Ticket Order Number"
													class="form-control" name="ticnumber" value="${ticnumber}">
											</div>
										</div>
										<div class="col-sm-8">
											<div class="col-sm-2 p">
											<input type="hidden" name="setpagination" id= "setpagination" value="">
												<label style="" class=" text-center control-label">
													File Name: </label> <input type="text"
													placeholder="Enter File Name" class="form-control"
													name="file" value="${file}">
											</div>
											<div class="col-sm-2 p">
												<label style="" class=" text-center control-label">
													Gateway Group Name: </label> <select class="form-control"
													name="gatewayname" id="gatewayname">
													<option value=""selected}>ALL</option>
													<c:forEach var="gateway" items="${gatways}">
														<c:if test="${gateway == gatewayname}">
															<option value="${gateway}" selected>${gateway}</option>
														</c:if>
														<c:if test="${gateway!= gatewayname}">
															<option value="${gateway}">${gateway}</option>
														</c:if>



													</c:forEach>
												</select>

											</div>
											<div class="col-sm-2 p">
												<label style="" class=" text-center control-label">
													DNIS: </label> <input type="text"
													placeholder="Enter DNIS" class="form-control"
													name="did" value="${did}">
											</div>

										</div>
										<div class="col-sm-8 p clearfix">
											<div class="col-sm-1">
												<input type="button" value="Search"
													 onclick="setPaginion()"
													class="btn btn-primary col-sm-12">
											</div>
											<div class="col-sm-1">
												<input type="submit" value="Download Report"
													formAction="downloadReport"
													class="btn btn-primary col-sm-12">
											</div>
											<div class="col-sm-1">
												<input type="submit" value="DNIS Template"
													formAction="dnisTemplate"
													class="btn btn-primary  col-sm-12">
											</div>
											<div class="col-sm-1">
												<input style="padding: 5px !important; text-align: left;"
													type="file" class="btn btn-primary col-sm-12" name="file" id="file"
													value="Choose File To Upload">
											</div>
											<div class="col-sm-1">
												<input type="button" value="File Upload"
													 onclick="populateMassage()" class="btn btn-primary  col-sm-12">
											</div>
											<div class="col-sm-1">
												<input type="submit" value="Create Config"
													formAction="downloadConfig"
													class="btn btn-primary  col-sm-12">
											</div>
											<div class="clearfix"></div>
										</div>
									</form>
									<br> <br>
									<div class="panel panel-default">
										<div class="panel-body">
											<div class="panel-heading" style="border: 0 none;">

												<div class="panel-ctrls"></div>
											</div>
											<div class="panel-body panel-no-padding" >
												<table id="example"
                                                    class="display"  cellspacing="0"
													width="100%">
													<thead>
														<tr>
														<th style="padding-right: 150px;">ACTION</th>
															<th style="padding-right: 60px;">RecordID</th>
															<th style="padding-right: 60px;">DNIS</th>
															<th style="padding-right: 60px;">Prefix</th>
															<th style="padding-right: 60px;">MappedNumber</th>
															<th style="padding-right: 60px;">CustomerName</th>
															<th style="padding-right: 60px;">DateCreated</th>
															<th style="padding-right: 60px;">DateModified</th>
															<th style="padding-right: 60px;">TicketOrderNum</th>
															<th style="padding-right: 60px;">DTFS</th>
															<th style="padding-right: 60px;">Status</th>
															<th style="padding-right: 60px;">File Name</th>
															<th style="padding-right: 60px;">User Id</th>
															<th style="padding-right: 60px;">GatewayGroupName</th>
															<th style="padding-right: 60px;">InitialActionBy</th>
															<th style="padding-right: 60px;">Current Action</th>
															<th style="padding-right: 60px;">Rounting IP1</th>
															<th style="padding-right: 60px;">Rounting IP2</th>
															<th style="padding-right: 60px;">Rounting IP3</th>
															<th style="padding-right: 60px;">Rounting IP4</th>
															<th style="padding-right: 60px;">Rounting IP5</th>
															<th style="padding-right: 60px;">Rounting IP6</th>
															
														</tr>
													</thead>
													<tbody>
														<c:forEach var="dnis" items="${dnisList}">
															<tr>
															<td><c:if test="${dnis.status=='Active'}">
																		<input type="hidden" value="" id="delete"
																			name="delete">
																		<a
																			href="currentaction.do?delete=${dnis.recordid}&Status=${status}&datem=${datem}&prefixmapp=${prefixmapp}&file=${file}&did=${did}&ticnumber=${ticnumber}&gatewayname=${gatewayname}">DELETE</a>
																	</c:if> <c:if test="${dnis.status=='Deleted'}">
																		<input type="hidden" value="" id="undelete"
																			name="undelete">
																		<a
																			href="currentaction.do?undelete=${dnis.recordid}&Status=${status}&datem=${datem}&prefixmapp=${prefixmapp}&file=${file}&did=${did}&ticnumber=${ticnumber}&gatewayname=${gatewayname}">UNDELETE</a>
																	</c:if> &nbsp; | &nbsp; <a href="#modify" data-toggle="modal"
																	onClick="edit('${dnis.status}','${dnis.mnumber}','${dnis.prefix}','${dnis.file}','${dnis.didNumber}','${dnis.ticketno}','${dnis.gatewayName}')">MODIFY</a>

																</td>
																<td>${dnis.recordid}</td>
																<td>${dnis.didNumber}</td>
																<td>${dnis.prefix}</td>
																<td>${dnis.mnumber}</td>
																<td>${dnis.cname}</td>
																<td>${dnis.datec}</td>
																<td>${dnis.datem}</td>
																<td>${dnis.ticketno}</td>
																<td>${dnis.dtfs}</td>
																<td>${dnis.status}</td>
																<td>${dnis.file}</td>
																<td>${dnis.userId}</td>
																<td>${dnis.gatewayName}</td>
																<td>${dnis.initialAction}</td>
																<td>${dnis.currentAction}</td>
																<td>${dnis.rip1}</td>
																<td>${dnis.rip2}</td>
																<td>${dnis.rip3}</td>
																<td>${dnis.rip4}</td>
																<td>${dnis.rip5}</td>
																<td>${dnis.rip6}</td>
																
															</tr>
														</c:forEach>

													</tbody>
												</table>
												<div class="panel-footer"></div>
											</div>

										</div>
									</div>
									
									
									<!--  -->
									
									
								</div>
							</div>
						</div>
						<div aria-hidden="true" aria-labelledby="myModalLabel"
							role="dialog" tabindex="-1" id="modify" class="modal fade">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button aria-hidden="true" data-dismiss="modal" class="close"
											type="button">x</button>
										<h2 class="modal-title">Modify</h2>

									</div>
									<div class="modal-body clearfix">
										<form class="form-horizontal" action="currentaction"
											method="post">

											<div class="form-group clearfix">
												<label class="col-sm-3 control-label" for="inputPassword">
													Staus :</label>
												<div class="col-sm-8">
													<select class="form-control" name="StatusEd" id="statusEd">
														<option value="" ${status == '' ? 'selected' : ''}>ALL</option>
														<option value="Active"
															${status == 'Active' ? 'selected' : ''}>ACTIVE</option>
														<option value="Deleted"
															${status == 'Deleted' ? 'selected' : ''}>DELETED</option>
													</select>
												</div>
											</div>
											<div class="form-group clearfix">
												<label class="col-sm-3 control-label" for="inputPassword">Prefix
													Number : </label>
												<div class="col-sm-8">
													<input type="text" name="prefixEd" id="prefixEd"
														placeholder="Enter Prefix Number" class="form-control"
														required />
												</div>
											</div>

											<div class="form-group clearfix">
												<label class="col-sm-3 control-label" for="inputPassword">
													Mapping Number : </label>
												<div class="col-sm-8">
													<input type="text" name="mappEd" id="mappEd"
														placeholder="Enter Mapped Number" class="form-control"
														required />
												</div>
											</div>

											<div class="form-group clearfix">
												<label class="col-sm-3 control-label" for="inputPassword">Ticket
													Order Number:</label>
												<div class="col-sm-8">
													<input type="text" name="ticnumberEd" id="ticnumberEd"
														placeholder="Enter Ticket Order Number"
														class="form-control" required />
												</div>

											</div>
											<div class="form-group clearfix">
												<label class="col-sm-3 control-label" for="inputPassword">File
													Name:</label>
												<div class="col-sm-8">
													<input type="text" name="fileEd" id="fileEd"
														placeholder="Enter File Name:" class="form-control"
														 />

												</div>
											</div>
											<div class="form-group clearfix">
												<label class="col-sm-3 control-label" for="inputPassword">DNIS</label>
												<div class="col-sm-8">
													<input type="text" name="didEd" id="didEd"
														placeholder="Enter DNIS:" class="form-control" readonly />

												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label">Gateway Group
													Name:</label>
												<div class="col-sm-8">

													<select class="form-control" name="GatewayGroupNameEd"
														id="GatewayGroupNameEd">

													</select>


												</div>

											</div>
									</div>
									<div class="modal-footer">
										<button class="btn btn-primary" id="submit" type="submit">Update</button>
										<button data-dismiss="modal" class="btn btn-default"
											type="button">Cancel</button>

									</div>
									</form>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- .container-fluid -->
					</div>
					<!-- #page-content -->
				</div>
			</div>
		</div>
	</div>
	
	<div class="infobar-wrapper scroll-pane">
		<div class="infobar scroll-content"></div>
	</div>
	<!-- Switcher -->

	<!-- /Switcher -->
	<!-- Load site level scripts -->

	<!-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"></script> -->

	<script type="text/javascript" src="assets/js/jquery-1.10.2.min.js"></script>
	<!-- Load jQuery -->
	<script type="text/javascript" src="assets/js/jqueryui-1.9.2.min.js"></script>
	<!-- Load jQueryUI -->

	<script type="text/javascript" src="assets/js/bootstrap.min.js"></script>
	<!-- Load Bootstrap -->


	<script type="text/javascript"
		src="assets/plugins/easypiechart/jquery.easypiechart.js"></script>
	<!-- EasyPieChart-->
	<script type="text/javascript"
		src="assets/plugins/sparklines/jquery.sparklines.min.js"></script>
	<!-- Sparkline -->
	<!-- <script type="text/javascript"
		src="assets/plugins/jstree/dist/jstree.min.js"></script> -->
	<!-- jsTree -->

	<script type="text/javascript"
		src="assets/plugins/codeprettifier/prettify.js"></script>
	<!-- Code Prettifier  -->
	<script type="text/javascript"
		src="assets/plugins/bootstrap-switch/bootstrap-switch.js"></script>
	<!-- Swith/Toggle Button -->

	<script type="text/javascript"
		src="assets/plugins/bootstrap-tabdrop/js/bootstrap-tabdrop.js"></script>
	<!-- Bootstrap Tabdrop -->

	<script type="text/javascript"
		src="assets/plugins/iCheck/icheck.min.js"></script>
	<!-- iCheck -->

	<script type="text/javascript" src="assets/js/enquire.min.js"></script>
	<!-- Enquire for Responsiveness -->

	<script type="text/javascript" src="assets/plugins/bootbox/bootbox.js"></script>
	<!-- Bootbox -->

	<script type="text/javascript"
		src="assets/plugins/simpleWeather/jquery.simpleWeather.min.js"></script>
	<!-- Weather plugin-->

	<script type="text/javascript"
		src="assets/plugins/nanoScroller/js/jquery.nanoscroller.min.js"></script>
	<!-- nano scroller -->

	<script type="text/javascript"
		src="assets/plugins/jquery-mousewheel/jquery.mousewheel.min.js"></script>
	<!-- Mousewheel support needed for jScrollPane -->

	<script type="text/javascript" src="assets/js/application.js"></script>
	<script type="text/javascript" src="assets/demo/demo.js"></script>
	<script type="text/javascript" src="assets/demo/demo-switcher.js"></script>
	<script src="//code.jquery.com/jquery-2.1.4.min.js"></script>


<script type="text/javascript"> 
var pagelength1 = "${Pagination}"


console.log("Page Length--"+pagelength1)
if(pagelength1 == null || "" == pagelength1){
	pagelength1 = 25
}
       $(document).ready( function() {
    $('#example').dataTable( {
        "iDisplayLength": pagelength1
    } );
   } )
</script> 
</head>
   
	<script>
	function populateMassage(){
			var form = document.getElementById("formFile");
			var filename  = document.getElementById('file').value;
			if(filename != "" && filename != null){
			var vext = filename.split(".");
			if(vext[1] == "xls" || vext[1] == "xlsx"){
			form.action = "uploadFile";
			alert("Please check DNIS uploaded status by opening downloaded file")
			form.submit();
			}else {
				alert("Please select excel sheet file to upload")
			}
			} else {
				alert("Please select file to upload")
			}
			
		}
	
	function setPaginion(){
		var form = document.getElementById("formFile");
		var pageoption = document.getElementsByName("example_length")[0];
		var sval = pageoption.options[pageoption.selectedIndex].value;		
		 $("#setpagination").val(sval);
		form.action = "dnismanagement";
	form.submit();
	}
		function downloadReport() {
			window.open("downloadReport");
		}
		function uploadFile() {
			window.open("uploadFile");
		}

		function search() {
			window.open("dnismanagement");
		}

		function downloadConfig() {
			window.open("downloadConfig");
		}

		function dnisTemplate() {
			window.open("dnisTemplate");
		}

		function currentAction() {
			window.open("currentaction");
		}

		function edit(status, mapp, prefix, file, did, ticnumber, gatewayname) {
			
			console.log("did----"+did);
			console.log("ticnumber----"+ticnumber);
			console.log("gatewayname----"+gatewayname)
			console.log("prefix----"+prefix)
			console.log("mapp----"+mapp)
			
			var gatways = "${gatways}";
			var gatewaysarray = gatways.split(",");
			var strVar = "";
			var gatwa = "";
			for (var incr = 0; incr < gatewaysarray.length; incr++) {
				gatwa = gatewaysarray[incr].replace(/[[\]]/g, '')
				gatwa = gatwa.trim()
				if (gatewayname == gatwa)
					strVar += "<option value=\""+gatwa+"\" selected>" + gatwa
							+ "<\/option>";
				else
					strVar += "<option value=\""+gatwa+"\">" + gatwa
							+ "<\/option>";
			}

			console.log(strVar)
			
			$("#statusEd").val(status);
			$("#mappEd").val(mapp);
			$("#prefixEd").val(prefix);
			$("#fileEd").val(file);
			$("#didEd").val(did);
			$("#ticnumberEd").val(ticnumber);
			$("#GatewayGroupNameEd").html(strVar);
			$('#edit').modal({
				backdrop : 'static',
				keyboard : false
			})
			$("#edit").modal('show');
		}
	</script>

	<!-- End loading site level scripts -->

	<!-- Load page level scripts-->
	<script type="text/javascript"
		src="assets/plugins/jquery-chained/jquery.chained.min.js"></script>
	<!-- Chained Select Boxes -->
	<script type="text/javascript"
		src="assets/plugins/datatables/jquery.dataTables.js"></script>
	<script type="text/javascript"
		src="assets/plugins/datatables/dataTables.bootstrap.js"></script>
	<script type="text/javascript" src="assets/demo/demo-datatables.js"></script>

	<!-- End loading page level scripts-->
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.0/css/jquery.dataTables.css">
<script type="text/javascript" src="//code.jquery.com/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="assets/js/jquery-1.10.2.min.custom.js"></script>
<script type="text/javascript" src="assets/js/jquery.datatable.custom.js"></script>

<script>
/* $(document).ready(function() {
    $('#example').DataTable( {
        "dom": '<"top"l>rt<"bottom"flp><"clear">'
    } );
} ); */

</script>

</body>
</html>