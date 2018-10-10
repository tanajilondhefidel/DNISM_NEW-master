<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Comlink | Logs</title>
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
	href="assets/plugins/datatables/dataTables.bootstrap.css"
	rel="stylesheet">
<link type="text/css"
	href="assets/plugins/datatables/dataTables.fontAwesome.css"
	rel="stylesheet">
<link type="text/css"
	href="assets/fonts/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
	<link rel="stylesheet" type="text/css" media="screen"
	href="assets/css/bootstrap-datetimepicker1.min.css">
	<link href="assets/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet">
	
<!-- Font Awesome -->
<link type="text/css" href="assets/css/styles.css" rel="stylesheet">
<!-- Core CSS with all styles -->
<link type="text/css" href="assets/css/custom.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.0/css/jquery.dataTables.css">
<link
	href="assets/css/bootstrap-combined.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" media="screen"
	href="assets/css/bootstrap-datetimepicker1.min.css">
<link id="bootstrap-style" href="assets/css/bootstrap.css"
	rel="stylesheet">
<link type="text/css"
	href="assets/plugins/jstree/dist/themes/avenger/style.min.css"
	rel="stylesheet">
<!-- jsTree -->
<link type="text/css" href="assets/plugins/codeprettifier/prettify.css"
	rel="stylesheet">
<!-- Code Prettifier -->
<link type="text/css"
	href="assets/plugins/iCheck/skins/minimal/blue.css" rel="stylesheet">
	<style>
	
	.dataTables_paginate{float:left !important}
	</style>
<!-- 	<link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
<link id="base-style" href="assets/css/style.css" rel="stylesheet">
<link id="base-style-responsive"
	href="assets/css/style-responsive.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
       href="assets/css/droid_sans.css"> 
  
<link rel="stylesheet" type="text/css"
	href="assets/css/droid_serif.css">
<link rel="stylesheet" type="text/css"
	href="assets/css/boogaloo.css">
<link rel="stylesheet" type="text/css"
	href="assets/css/economica.css">
iCheck -->
<style>

.panel-ctrls {float:left !important;}
.dataTables_filter{display:none !important;}
</style>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries. Placeholdr.js enables the placeholder attribute -->
<!--[if lt IE 9]>
        <link type="text/css" href="assets/css/ie8.css" rel="stylesheet">
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/respond.js/1.1.0/respond.min.js"></script>
        <script type="text/javascript" src="assets/plugins/charts-flot/excanvas.min.js"></script>
        <script type="text/javascript" src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- The following CSS are included as plugins and can be removed if unused-->


</head>
<body class="infobar-offcanvas">

	
	<header id="topnav"
		class="navbar navbar-midnightblue navbar-fixed-top clearfix"
		role="banner"><a style="margin-left:10px;" class="navbar-brand" href="dashboard.html">Comlink</a>
	 


	

	<ul class="nav navbar-nav toolbar pull-right">
		<li class="dropdown toolbar-icon-bg"><a href="#"
			class="dropdown-toggle" data-toggle='dropdown'><span
				class="icon-bg" style="background:none;"><i class="fa fa-fw fa-user"></i></span></a>
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
								<c:if test="${sessionuser.type=='Admin'}">
									<li><a href="user"><i class="fa fa-users"></i><span>Users</span></a></li>
								<li><a href="log"><i class="fa fa-bar-chart"></i><span>Log</span></a></li>
								</c:if><li><a href="dnismanagement"><i class="fa fa-cog"></i><span>DNIS
											Management</span></a></li>
							</ul>
							</nav>
						</div>
					</div>
				</div>
			</div>
				<form class="" action="log" method="post" id="myForm" name="form1">
			<div class="static-content-wrapper" style="overflow: hidden;display:block !important">
				<div class="static-content">
					<div class="page-content">
						<div class="page-heading">
							
						</div>
						<div class="container-fluid">
							

						<div class="col-sm-12 p">
						<div class="col-sm-4">

										<div class="control-group">
											<label class="control-label" for="date01">Start Date</label>
											<div id="datetimepicker" class="input-append date">
												<input  data-format="yyyy-MM-dd hh:mm:ss" class="form-control"
													type="text" name="startdate" id="startdate1" value="${sdate}"></input> <span
													class="add-on"> <i data-time-icon="icon-time"
													data-date-icon="icon-calendar"></i>
												</span>
											</div>
											
										</div>
									</div>

									<div class="col-sm-4">
										<div class="control-group">
											<label class="control-label" for="date01">End Date</label>
											<div id="datetimepicker1" class="input-append date">
												<input  data-format="yyyy-MM-dd hh:mm:ss" class="form-control"
													type="text" name="enddate" id="enddate1" value="${edate}"></input> <span
													class="add-on"> <i data-time-icon="icon-time"
													data-date-icon="icon-calendar"></i>
												</span>
											</div>
										</div>
									</div>
									   
									
									<div class="col-sm-4">
										<div class="control-group">
											<label class="control-label" for="date01" style="visibility:hidden;">End Date</label>
											<div class="input-append date">
												<button id="" type="submit" class="btn btn-primary"
								           href="">Submit</button>
											</div>
										</div>
									</div>
									
								           </div>
						<div class="container-fluid">

							<div class="row">
								<div class="col-sm-12">


									<div class="panel panel-default">
										<div class="panel-heading">
											
											<div class="panel-ctrls"></div>
										</div>
										<div class="panel-body panel-no-padding">
											<table id="example"
												class="table table-striped table-bordered" cellspacing="0"
												width="100%">
												<thead>
													<tr>
															<th style="width:120px !important;padding-right: 100px">First Name</th>
															<th style="padding-right: 130px;width:100px !important;">Last name</th>
															<th style="padding-right: 130px; margin-right:30px;width:130px !important;">Date & Time</th>
															<th style="padding-right: 100px;width:100px !important;">Activity</th>
														</tr>
												</thead>
												<tbody>
													<c:forEach var="log" items="${logList}">
															<tr>
																<td>${log.fname}</td>
																<td>${log.lname}</td>
																<td>${log.time}</td>
																<td>${log.activity}</td>
															</tr>
														</c:forEach>
												</tbody>
											</table>
											<div class="panel-footer"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- .container-fluid -->
					
							
						</div>
						</div>
						</div>
						</div>
						
					<!-- #page-content -->
				</div>
			</div>
				</form>
				
				<footer role="contentinfo" style="position:relative">
				<div class="clearfix">
					<ul class="list-unstyled list-inline pull-left">
						<li><h6 style="margin: 0;">&copy; 2016 Comlink</h6></li>
					</ul>
					<button class="pull-right btn btn-link btn-xs hidden-print"
						id="back-to-top">
						<i class="fa fa-arrow-up"></i>
					</button>
				</div>
				</footer>
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

	<!--  <script type="text/javascript" src="assets/js/jquery-1.10.2.min.js"></script>  -->
	<!-- Load jQuery -->
<!-- 	<script type="text/javascript" src="assets/js/jqueryui-1.9.2.min.js"></script> -->
	<!-- Load jQueryUI -->

	<script type="text/javascript" src="assets/js/bootstrap.min.js"></script>
	<!-- Load Bootstrap -->


	<script type="text/javascript"
		src="assets/plugins/easypiechart/jquery.easypiechart.js"></script>
	<!-- EasyPieChart-->
	<script type="text/javascript"
		src="assets/plugins/sparklines/jquery.sparklines.min.js"></script>
	<!-- Sparkline -->
	<script type="text/javascript"
		src="assets/plugins/jstree/dist/jstree.min.js"></script>
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

	<script src='assets/js/fullcalendar.min.js'></script>

	<script src='assets/js/jquery.dataTables.min.js'></script>

	<script src="assets/js/excanvas.js"></script>
	<script src="assets/js/jquery.flot.min.js"></script>
	<script src="assets/js/jquery.flot.pie.min.js"></script>
	<script src="assets/js/jquery.flot.stack.js"></script>
	<script src="assets/js/jquery.flot.resize.min.js"></script>

	<!-- <script src="assets/js/jquery.chosen.min.js"></script>

	<script src="assets/js/jquery.uniform.min.js"></script>

	<script src="assets/js/jquery.cleditor.min.js"></script>

	<script src="assets/js/jquery.noty.js"></script>

	<script src="assets/js/jquery.elfinder.min.js"></script>

	<script src="assets/js/jquery.raty.min.js"></script>

	<script src="assets/js/jquery.iphone.toggle.js"></script>

	<script src="assets/js/jquery.uploadify-3.1.min.js"></script>

	<script src="assets/js/jquery.gritter.min.js"></script>

	<script src="assets/js/jquery.imagesloaded.js"></script>

	<script src="assets/js/jquery.masonry.min.js"></script>

	<script src="assets/js/jquery.knob.js"></script>

	<script src="assets/js/jquery.sparkline.min.js"></script>

	<script src="assets/js/custom.js"></script> -->

	

	<script
		src="assets/js/jquery.min.js"></script>
	<!-- <script
		src="assets/js/bootstrap.min.js"></script> -->


	<!-- <script src="assets/js/jquery1.min.js"></script> -->
<script src="assets/js/bootstrap1.min.js"></script>

	<script type="text/javascript"
		src="assets/js/bootstrap-datetimepicker1.min.js">
    </script>
	<script type="text/javascript"
		src="assets/js/bootstrap-datetimepicker.pt-BR.js">
    </script>
	<!-- End loading site level scripts -->

	<!-- Load page level scripts-->


	<!-- End loading page level scripts-->

	<script type="text/javascript">
	 $('#datetimepicker').datetimepicker({
    	        format: 'yyyy-MM-dd hh:mm:ss',
    	    });
    	     $('#datetimepicker1').datetimepicker({
    	           format: 'yyyy-MM-dd hh:mm:ss',
    	     });
    	     $( "#startdate1" ).datepicker().datepicker( 'setDate', 'today');
    	     $( "#enddate1" ).datepicker().datepicker( 'setDate', 'today'); 
    	    
       </script>
       
       
      <script type="text/javascript"> 
      var pagelength1 = "${Pagination}"

    	  if(pagelength1 == null || "" == pagelength1){
    	  	pagelength1 = 25
    	  }
       $(document).ready( function() {
    $('#example').dataTable( {
        "iDisplayLength": pagelength1
    } );
} )
       </script> 
       
        <script type="text/javascript">
        
       
        
        </script> 
   <script type="text/javascript"
		src="assets/plugins/datatables/jquery.dataTables.js"></script>
	<script type="text/javascript"
		src="assets/plugins/datatables/dataTables.bootstrap.js"></script>
	<script type="text/javascript" src="assets/demo/demo-datatables.js"></script>

	<!-- End loading page level scripts-->
<!-- 
<script type="text/javascript" src="//code.jquery.com/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="//cdn.datatables.net/1.10.0/js/jquery.dataTables.js"></script> -->
</body>
</html>