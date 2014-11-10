<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    <style type="text/css">
<%@ include file="/resources/css/style.css" %>
</style>
<script type="text/javascript" src="resources/js/core.js"></script>

        <title>BuyBig</title>
    </head>
    <body onload="init()">
        <h1 align="center">BuyBig</h1>
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