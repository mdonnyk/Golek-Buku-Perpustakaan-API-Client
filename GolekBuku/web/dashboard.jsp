<%@page import="model.Pinjam"%>
<%@page import="service.ClientAPI"%>
<%@page import="model.Anggota"%>
<%

    Anggota a = (Anggota) session.getAttribute("anggota");
    Pinjam pj[] = null;
    if (a==null) {
        request.setAttribute("error", "Anda Harus Login");
        RequestDispatcher rq = request.getRequestDispatcher("error.jsp");
        rq.forward(request, response);
    }
    try{
        pj = ClientAPI.cekHistory(a);
    }
    catch (Exception e){
        request.setAttribute("error", e.getMessage());
        RequestDispatcher rq = request.getRequestDispatcher("error.jsp");
        rq.forward(request, response);
    }
    
    
%><!DOCTYPE HTML>
<!--
	Directive by HTML5 UP
	html5up.net | @n33co
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
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
                <h2><a href="index.jsp">Home</a> // <a href="Logout"> Logout</a></h2>
            </div>
		<!-- Main -->
			<div id="main">
                            
                            <div class="box container">
				<header class="major container 75%">
                                    <h2>Selamat Datang! <%= a.getNama()%></h2>
                                    
				</header>
                                    <section>
                                        <header>
						<h3>Buku yang pernah dipinjam</h3>
					</header>
						<div class="table-wrapper">
                                                    <% for (int i = 0; i < pj.length; i++) { %>
                                                    
                                                    <table class="default">
                                                        <tr>
                                                            <td colspan="2"><b><%=pj[i].getJudul()%></b></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Tanggal Pinjam : <%=pj[i].getTanggal_pinjam() %></td>
                                                            <td>Tanggal Kembali : <%=pj[i].getTanggal_kembali()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td>ISBN : <%=pj[i].getID_Buku()%></td>
                                                            <td>Status :
                                                                <% if (pj[i].getStatus().equalsIgnoreCase("Y")){ %>
                                                                Sudah dikembalikan
                                                                 <%    
                                                                } else {
                                                                    %>
                                                                    <a href="Kembali?p=<%=pj[i].getPerpustakaan()%>&isbn=<%=pj[i].getID_Buku()%>">Belum dikembalikan</a>
                                                                
                                                                <% } %>
                                                            
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Keterangan : </td>
                                                            <td>Dipinjam di <%=pj[i].getPerpustakaan()%></td>
                                                        </tr>
                                                        
                                                    </table> 
                                                      
                                                    <%
                                                        }
                                                    %>
						</div>
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