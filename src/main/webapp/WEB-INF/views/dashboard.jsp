<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="utf-8">
<title>Dashboard | Comlink</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="description" content="Comlink Admin Theme">
<meta name="author" content="KaijuThemes">

<link
	href='http://fonts.googleapis.com/css?family=RobotoDraft:300,400,400italic,500,700'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300,400,400italic,600,700'
	rel='stylesheet' type='text/css'>

<!--[if lt IE 10]>
        <script type="text/javascript" src="assets/js/media.match.min.js"></script>
        <"src/main/webapp/WEB-INF/views/dashboard.jsp"script type="text/javascript" src="assets/js/placeholder.min.js"></script>
    <![endif]-->

<link type="text/css"
	href="assets/fonts/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link type="text/css" href="assets/css/styles.css" rel="stylesheet">
<!-- Core CSS with all styles -->
<link type="text/css" href="assets/css/custom.css" rel="stylesheet">
<!-- custom css -->

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
	href="assets/plugins/form-daterangepicker/daterangepicker-bs3.css"
	rel="stylesheet">
<!-- DateRangePicker -->
<link type="text/css"
	href="assets/plugins/fullcalendar/fullcalendar.css" rel="stylesheet">
<!-- FullCalendar -->
<link type="text/css"
	href="assets/plugins/charts-chartistjs/chartist.min.css"
	rel="stylesheet">
<!-- Chartist -->


</head>



<body class="infobar-offcanvas">


	<header id="topnav"
		class="navbar navbar-midnightblue navbar-fixed-top clearfix"
		role="banner"> <a style="margin-left:10px;" class="navbar-brand" href="dashboard">comlink</a>




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
			<div class="static-sidebar-wrapper static-sidebar sidebar-midnightblue">
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
							<h1 class="p">Dashboard</h1>
							<div class="row">
								<div class="col-md-12">
									<div class="panel">
										<div class="row panel-body">

											<div class="col-md-12">
												<c:if test="${sessionuser.type=='Admin'}"><div class="col-md-4">
													<a href="user" class="shortcut-tile tile-midnightblue">
														<div class="tile-body">
															<div class="pull-left">
																<i class=""><img src="assets/img/geo.png"
																	width="50px" style="visibility: hidden;" /></i>
															</div>
															<div class="pull-right"></div>
														</div>
														<div class="tile-footer">
															<b>Users</b>
														</div>
													</a>
												</div>
												<div class="col-md-4">
													<a href="log" class="shortcut-tile tile-midnightblue">
														<div class="tile-body">
															<div class="pull-left">
																<i class=""><img src="assets/img/geo.png"
																	width="50px" style="visibility: hidden;" /></i>
															</div>
															<div class="pull-right"></div>
														</div>
														<div class="tile-footer">
															<b>Log</b>
														</div>
													</a>
												</div></c:if>
												<div class="col-md-4">
													<a href="dnismanagement"
														class="shortcut-tile tile-midnightblue">
														<div class="tile-body">
															<div class="pull-left">
																<i class=""><img src="assets/img/geo.png"
																	width="50px" style="visibility: hidden;" /></i>
															</div>
															<div class="pull-right"></div>
														</div>
														<div class="tile-footer">
															<b>DNIS Management</b>
														</div>
													</a>
												</div>
											</div>


										</div>
									</div>

								</div>


							</div>
						</div>

					</div>
					<!-- #page-content -->
				</div>
				<footer role="contentinfo">
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

	<!-- End loading site level scripts -->

	<!-- Load page level scripts-->

	<script type="text/javascript"
		src="assets/plugins/fullcalendar/fullcalendar.min.js"></script>
	<!-- FullCalendar -->

	<script type="text/javascript" src="assets/plugins/wijets/wijets.js"></script>
	<!-- Wijet -->

	<script type="text/javascript"
		src="assets/plugins/charts-chartistjs/chartist.min.js"></script>
	<!-- Chartist -->
	<script type="text/javascript"
		src="assets/plugins/charts-chartistjs/chartist-plugin-tooltip.js"></script>
	<!-- Chartist -->

	<script type="text/javascript"
		src="assets/plugins/form-daterangepicker/moment.min.js"></script>
	<!-- Moment.js for Date Range Picker -->
	<script type="text/javascript"
		src="assets/plugins/form-daterangepicker/daterangepicker.js"></script>
	<!-- Date Range Picker -->

	<script type="text/javascript" src="assets/demo/demo-index.js"></script>
	<!-- Initialize scripts for this page-->

	<!-- End loading page level scripts-->

</body>
</html>