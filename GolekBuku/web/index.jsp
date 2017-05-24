<%@page import="model.Anggota"%>
<!DOCTYPE HTML>
<!--
	Directive by HTML5 UP
	html5up.net | @n33co
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<%
    
    Anggota a = (Anggota) session.getAttribute("anggota");
    
%>
<html>
	<head>
		<title>Golek Buku</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="assets/css/main.css" />
		<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
	</head>
	<body>

		<!-- Header -->
			<div id="header">
				<span class="logo icon fa-book"></span>
				<h1>GOLEK BUKU</h1>
				<p>One stop perpustakaan on Internet.</p>
				
			</div>

		<!-- Main -->
			<div id="main">

				<header class="major container 75%">
					<h2>Cari buku apa?</h2>
					<form action="hasil.jsp">
						<input type="text" name="query" value="" placeholder="Judul Buku / ISBN / Penulis"/><br>
						<input type="submit" value="cari" />
       			 	</form>
				</header>
			</div>

		<!-- Footer -->
			<div id="footer">
				<div class="container 75%">
                                    <%
                                        if (a!=null){
                                    %>  
                                        <header class="major last">
                                            <h2>Masuk <a href="dashboard.jsp"> dashboard?</a></h2>
					</header>   
                                        <header class="major last">
                                            <h2>Halo <%= a.getNama() %>, mau <a href="Logout"> logout?</a></h2>
					</header>   
                                            
                                    <%        
                                        }
                                        else{
                                    %>
                                        <header class="major last">
						<h2>Sudah punya akun? Login</h2>
					</header>

					<form action="Login" method="POST">
						<div class="row">
							<div class="6u 12u(mobilep)">
								<input type="text" name="username" value="" placeholder="username" />
							</div>
							<div class="6u 12u(mobilep)">
								<input type="password" name="password" value="" placeholder="password" />
							</div>
						</div>
						<div class="row">
							<div class="12u">
								<ul class="actions">
									<li><input type="submit" value="login" /></li>
								</ul>
							</div>
						</div>
					</form>
                                    
                                        <header class="major last">
						<h2>Belum punya akun?</h2>
                                                <a href="daftar.html" class="button">daftar!</a>
					</header>
                                    
                                    
                                    <%
                                            
                                            
                                            
                                        }
                                    %>
					


					<ul class="copyright">
						<li>Golek Buku - 2017</li>
					</ul>

				</div>
			</div>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/skel.min.js"></script>
			<script src="assets/js/util.js"></script>
			<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
			<script src="assets/js/main.js"></script>

	</body>
</html>