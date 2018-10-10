<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 
 <meta charset="utf-8">
    <title>Change Password</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-touch-fullscreen" content="yes">
   

    <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700' rel='stylesheet' type='text/css'>
    <link type="text/css" href="assets/plugins/iCheck/skins/minimal/blue.css" rel="stylesheet">
    <link type="text/css" href="assets/fonts/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link type="text/css" href="assets/css/styles.css" rel="stylesheet">
      <link type="text/css" href="assets/css/custom.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries. Placeholdr.js enables the placeholder attribute -->
    <!--[if lt IE 9]>
        <link type="text/css" href="assets/css/ie8.css" rel="stylesheet">
        <script type="text/javascript" src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- The following CSS are included as plugins and can be removed if unused-->
 
 
 
 
</head>
<body class="focused-form">
      <div class="" id=""> 
        </div><!-- bgmap/-->  
   
        
<div class="container" id="login-form">
	<a href="dashboard" class="login-logo"><img src="assets/img/login-logo.png"></a>
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<div class="panel panel-default">
				<div class="panel-heading"><h2>Change Your Password </h2></div>
				<div class="panel-body">
					
					<form class="form-horizontal"  action="changepassword" method="post" onsubmit="return validate();">
					 
						<div class="form-group">
	                        <div class="p">
	                        	<div class="input-group">							
									<span class="input-group-addon">
										<i class="fa fa-key"></i>
									</span>
									<input type="password" class="form-control" id="password" placeholder="Password" data-parsley-minlength="6" placeholder="At least 6 characters"
									name="password"  required>
								</div>
						</div>

						<div class="">
	                        <div class="col-xs-12 p">
	                        	<div class="input-group">
									<span class="input-group-addon">
										<i class="fa fa-key"></i>
									</span>
									<input type="password" class="form-control" id="password1" placeholder="Confirm password" name="password" required>
								</div>
	                        </div>
						</div>

						

						<div class="">
							<div class="button-login col-sm-3">	
								<button type="submit" class="btn btn-primary"> Change Password</button>
								
							
								
							</div>
						</div>
						
						</form>
						<form action="dashboard" class="pull-right col-sm-3">
						 <button type="dashboard" class="btn btn-primary">Close</button> 
						</form>
						
												
				</div>
			</div>

			
		</div>
	</div>
</div>

   
  
 <script type="text/javascript">

function validate(){
	console.log("Password:"+$("#password").val());
	console.log("Password1:"+$("#password1").val());
    // return false;
	if($("#password").val()!=$("#password1").val()){
		console.log("not matched");
		alert("Both passwords should be same");
		return false;
	} else {
		return true;
	}
}


</script>
    
    <!-- Load site level scripts -->

<!-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"></script> -->

<script type="text/javascript" src="assets/js/jquery-1.10.2.min.js"></script> 							<!-- Load jQuery -->
<script type="text/javascript" src="assets/js/jqueryui-1.9.2.min.js"></script> 							<!-- Load jQueryUI -->

<script type="text/javascript" src="assets/js/bootstrap.min.js"></script> 								<!-- Load Bootstrap -->


<script type="text/javascript" src="assets/plugins/easypiechart/jquery.easypiechart.js"></script> 		<!-- EasyPieChart-->
<script type="text/javascript" src="assets/plugins/sparklines/jquery.sparklines.min.js"></script>  		<!-- Sparkline -->
<script type="text/javascript" src="assets/plugins/jstree/dist/jstree.min.js"></script>  				<!-- jsTree -->

<script type="text/javascript" src="assets/plugins/codeprettifier/prettify.js"></script> 				<!-- Code Prettifier  -->
<script type="text/javascript" src="assets/plugins/bootstrap-switch/bootstrap-switch.js"></script> 		<!-- Swith/Toggle Button -->

<script type="text/javascript" src="assets/plugins/bootstrap-tabdrop/js/bootstrap-tabdrop.js"></script>  <!-- Bootstrap Tabdrop -->

<script type="text/javascript" src="assets/plugins/iCheck/icheck.min.js"></script>     					<!-- iCheck -->

<script type="text/javascript" src="assets/js/enquire.min.js"></script> 									<!-- Enquire for Responsiveness -->

<script type="text/javascript" src="assets/plugins/bootbox/bootbox.js"></script>							<!-- Bootbox -->

<script type="text/javascript" src="assets/plugins/simpleWeather/jquery.simpleWeather.min.js"></script> <!-- Weather plugin-->

<script type="text/javascript" src="assets/plugins/nanoScroller/js/jquery.nanoscroller.min.js"></script> <!-- nano scroller -->

<script type="text/javascript" src="assets/plugins/jquery-mousewheel/jquery.mousewheel.min.js"></script> 	<!-- Mousewheel support needed for jScrollPane -->

<script type="text/javascript" src="assets/js/application.js"></script>
<script type="text/javascript" src="assets/demo/demo.js"></script>
<script type="text/javascript" src="assets/demo/demo-switcher.js"></script>

<!-- End loading site level scripts -->
    <!-- Load page level scripts-->
    
 
    <!-- End loading page level scripts-->
    </body>
</html>