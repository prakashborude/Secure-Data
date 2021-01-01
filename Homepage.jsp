<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Secure Sharing</title>
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<div class="slider">
	<div class="container">
		<div class="row">
			<div class="responsive-slider" data-spy="responsive-slider" data-autoplay="true">
				<div class="slides" data-group="slides">
					<ul>
						<li>
							<div class="slide-body" data-group="slide">
								<img src="../img/2a.jpg" alt="">
								
							</div>
						</li>
						<li>
							<div class="slide-body" data-group="slide">
								<img src="../img/1.jpg" alt="">
								
							</div>
						</li>
						
					</ul>
				</div>
		   
				<a class="slider-control left" href="#" data-jump="prev"><i class="fa fa-angle-left fa-2x"></i></a>
				<a class="slider-control right" href="#" data-jump="next"><i class="fa fa-angle-right fa-2x"></i></a>		
			</div>
		</div>
	</div>
	</div>
	<jsp:include page="Footer.jsp"></jsp:include>
	 <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="../js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.min.js"></script>
	<script src="../js/responsive-slider.js"></script>
	<script src="../js/wow.min.js"></script>
	<script>
	wow = new WOW(
	 {
	
		}	) 
		.init();
	</script>
</body>
</html>