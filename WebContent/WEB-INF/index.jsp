<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    <style type="text/css">
<%@ include file="/resources/css/style.css" %>
</style>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script type="text/javascript" src="resources/js/core.js"></script>

        <title>BuyBig</title>
    </head>
    <body onload="init()">
        <nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Buybig</a>
    </div>
    <div>
      <ul class="nav navbar-nav">
        <li class="active"><a href="Shop">Shop</a></li>
        <li ><a href="UserInfo">Mon compte</a></li> 
        <li><a href="#">Logout</a></li> 
      </ul>
    </div>
  </div>
</nav>        
        <div id="selection">
            <label>Choisir une categorie</label>
             
            <select id="liste" onchange="changerCategorie(this)">                                           
                <option>Choisir...</option>
            </select>
        </div>
        <div class="catalogue" id="catalogue">
        <div></div>
        </div>
        <div class="panier" id="panier">
            <h1>Panier</h1>
            <table id="tab_panier">
                <thead>
                    <th>Titre</th>
                    <th>Prix</th>
                    <th width="30px"></th>
                </thead>
            </table>
            <div id="facture">
                <span id="sousTotal">SousTotal: 0$</span>
                 
                <span id="tps">Tps: 0$</span>
                 
                <span id="tvq">Tvq: 0$</span>
                 
                <span id="total">Total: 0$</span>
            </div>
            <input type="button" class="payer" value="Payer" onclick="checkout()"/>
        </div>
    </body>
</html>