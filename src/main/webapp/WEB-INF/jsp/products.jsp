<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<c:url value='/resource/css/main.css'/>">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.1/angular.min.js">
	
</script>
<script src="/webstore/resource/js/controllers.js" >
</script>

<title>Products--</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Products</h1>
				<p>All the available products in our store</p>
			</div>
		</div>
	</section>
	<section class="container"  ng-app="cartApp">

		<div class="row">
			<c:forEach var="product" items="${products}">
				<div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
					<div class="thumbnail">
						<img src='<c:url value="/resource/images/P1234.jpg"></c:url>'
							alt="image" style="width: 100%" />
						<div class="caption">
							<h3>${product.name}</h3>
							<p>${product.description}</p>
							<p>${product.unitPrice}USD</p>
							<p>Available ${product.unitsInStock} units in stock</p>
							<p>
								<a
									href=" <spring:url value= "/products/product?id=${product.productId}" /> "
									class="btn btn-primary"> <spanclass ="glyphicon-info-sign glyphicon" /></span>
									Details
								</a>
							</p>
							<p ng-controller="cartCtrl">
								<a href="#" class="btn btn-warning btn-large"
									ng-click="addToCart('${product.productId}')"> <span
									class="glyphicon-shopping-cart glyphicon"></span> Order Now
								</a>
							</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>
</body>
</html>