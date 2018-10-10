<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Comlink | Users</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-touch-fullscreen" content="yes">


<link
	href='http://fonts.googleapis.com/css?family=RobotoDraft:300,400,400italic,500,700'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300,400,400italic,600,700'
	rel='stylesheet' type='text/css'>

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
        <script type="text/javascript" src="assets/js/respond.js"></script>
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
		role="banner"> <a style="margin-left:10px;" class="navbar-brand" href="dashboard.html">Comlink</a>
	<ul class="nav navbar-nav toolbar pull-right">
		<li class="dropdown toolbar-icon-bg"><a href="#"
			class="dropdown-toggle" data-toggle='dropdown'><span
				class="icon-bg"><i class="fa fa-fw fa-user"></i></span></a>
			<ul class="dropdown-menu userinfo arrow">
				<li><a href="changepassword.html"><span class="pull-left">Change Password</span> </a></li>
				
				
				<li><a href="logout"><span class="pull-left">Sign
							Out</span> <i class="pull-right fa fa-sign-out"></i></a></li>
			</ul></li>
	</ul>
	</header>
	<div id="wrapper">
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
									<div class="tabular-cell welcome-options impuser">
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
								<c:if test="${sessionuser.type=='Admin'}"><li><a href="user"><i class="fa fa-users"></i><span>Users</span></a></li>
								<li><a href="log"><i class="fa fa-bar-chart"></i><span>Log</span></a></li>
								</c:if><li><a href="dnismanagement"><i class="fa fa-cog"></i><span>DNIS
											Management</span></a></li>
											<li><a href="addaction"><i class="fa fa-home"></i><span>Add DNIS</span></a></li>
							</ul>
							</nav>
						</div>
					</div>
				</div>
			</div>
				<div class="static-content-wrapper">
				<div class="static-content">
					<div class="page-content">
						<div class="page-heading">
							<h1>ADD DNIS</h1>
						</div>
						
				 <div class="col-sm-12">
									 
									 <div class="col-sm-12">
									 <form action="addaction" method="post">
									            <div class="col-sm-4 p">
												<label  style="" class=" text-center control-label" for="inputPassword">DTFS:</label>
												<input type="text" name="DTFS"
												placeholder="Enter DTFS" class="form-control" required/>		 
									            </div>
											
											<div class="col-sm-4 p">
												<label  style="" class=" text-center control-label" for="inputPassword">DNIS:</label>
												<input type="text" name="DIDNUMBER"
												placeholder="Enter DNIS" class="form-control" required/>		 
											</div>
											
											<div class="col-sm-4 p">
												<label  style="" class=" text-center control-label" for="inputPassword">Prefix:</label>
												<input type="text" name="Prefix"
												placeholder="Enter Prefix" class="form-control" required/>		 
											</div>
											</div>
											
													<div class="col-sm-12">
										    <div class="col-sm-4 p">
												<label  style="" class=" text-center control-label" for="inputPassword">Mapping Number:</label>
												<input type="text" name="MappingNumber"
												placeholder="Enter Mapping Number" class="form-control" required/>		 
										   </div>
											
											<div class="col-sm-4 p">
												<label  style="" class=" text-center control-label" for="inputPassword">Customer Name:</label>
												<input type="text" name="CustomerName"
												placeholder="Enter Customer Name" class="form-control" required/>		 
											</div>
																						
											<div class="col-sm-4 p">
												<label style="" class=" text-center control-label">  Gateway Group Name: </label>
													<select class="form-control"  name="GatewayGroupName">
													<c:forEach var="gateway" items="${gatways}">
													<c:if test="${gateway=='default'}">
													<option value="${gateway}" selected>${gateway}</option>
													</c:if>	
													<c:if test="${gateway!='default'}">											 
													<option value="${gateway}">${gateway}</option> 
													</c:if>	
														</c:forEach>
												</select>
											</div>
											
											
											</div>
											
											<div class="col-sm-12">
											<div class="col-sm-4 p">
												<label  style="" class=" text-center control-label" for="inputPassword">Ticket Order Number:</label>
												<input type="text" name="TicketOrderNumber"
												placeholder="Enter Ticket Order Numbere" class="form-control" required/>		 
											</div>
											
											
											<div class="col-sm-4 p">
												<button class="btn btn-primary" id="submit" type="submit" style=" margin-top: 24px;">submit</button> 
													<a href="addaction" data-dismiss="modal" class="btn btn-default" 
												  style="margin-top: 24px;color: white;border color: #03a9f4;;color: #ffffff;background-color: #2196F3;border-color: #2196F3;">Cancel</a>
											 </div>
											</div></form>
				 </div>
										
			</div>
	     		</div>
		        	</div>
			             </div>
			                   </div>
														 
												
									<div class="panel" style="width: 100%; overflow: auto;">
						  
						           </div>
						<!-- .container-fluid -->
					</div>
					<!-- #page-content -->
				</div>

				
				<script> 
			 
					var msg = "${message}"
					    if(msg != "")
				    	 alert(msg)
			 
				</script>