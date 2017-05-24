<%@page import="model.Anggota"%>
<%@page import="model.Buku"%>
<%@page import="service.ClientAPI"%>
<!DOCTYPE HTML>
<!--
	Directive by HTML5 UP
	html5up.net | @n33co
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<%
    String query = request.getParameter("query");
    Buku[] bk = ClientAPI.cariBuku(query);
    Anggota a =(Anggota) session.getAttribute("anggota");
    boolean login;
    if (a==null) {
            login = false;
        }
    else{
        login = true;
    }
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
            <div id="header">
		<h2><a href="index.jsp">Home</a> // 
                    <% if (login) { %>
                        <a href="Logout"> Logout</a></h2>    
                    <%
                        }
                    else {%>
                        <a href="index.jsp"> Login</a></h2>
                    <%
                        }
                    %>
                    
            </div>
		<!-- Main -->
			<div id="main">

				<div class="box container">
					<header>
						<h2>Hasil pencarian buku</h2>
					</header>
					<section>
                                                <%for (int i = 0; i < bk.length; i++) { %>
						<div class="table-wrapper">
                                                    <table class="default">
                                                        <tr>
                                                            <td colspan="2"><b>
                                                                    <% if (login) { %>
                                                                    <a href="Peminjaman?p=<%=bk[i].getPerpustakaan()%>&isbn=<%=bk[i].getISBN()%>">
                                                                        <%=bk[i].getJudul()%></a></b>
                                                                    <%
                                                                        }
                                                                    else {%>
                                                                        <%=bk[i].getJudul()%></b>
                                                                    <%
                                                                        }
                                                                    %>
                                                                    
                                                                    </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Penulis : <%=bk[i].getPenulis()%></td>
                                                            <td>Penerbit : <%=bk[i].getPenerbit()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td>ISBN : <%=bk[i].getISBN()%></td>
                                                            <td>Tahun : <%=bk[i].getTahun_Terbit()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Tersedia dengan jumlah : <b><%=bk[i].getKetersediaan()%></b></td>
                                                            <td>Tersedia di <%=bk[i].getPerpustakaan()%></td>
                                                        </tr>
                                                        
                                                    </table>        
						</div>
                                                <%
                                                    }
                                                %>
					</section>
					
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