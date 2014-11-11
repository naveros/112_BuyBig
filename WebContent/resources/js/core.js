var tab_categories = [];
var tab_librairie = {
};
var tab_panier = [];
var total = 0;

function init() {
    xhttp = new XMLHttpRequest();
    xhttp.open("GET", "Catalogue", false);
    xhttp.send();
    xmlDoc = xhttp.responseXML;

    tab_categories = xmlDoc.getElementsByTagName("categorie");
    for (i = 0;i < tab_categories.length;i++) {
        categorie = tab_categories[i].getAttribute('nom');

        tab_librairie[categorie] = [];
        document.getElementById("liste").add(new Option(categorie));

        livre = tab_categories[i].getElementsByTagName("livre");
        for (j = 0;j < livre.length;j++) {
            isbn = livre[j].getAttribute('isbn');
            titre = livre[j].getAttribute('titre');
            auteur = livre[j].getAttribute('auteur');
            prix = livre[j].getAttribute('prix');
            onSale = livre[j].getAttribute('onSale');
            tab_librairie[categorie][j] = {
                "isbn" : isbn, "titre" : titre, "auteur" : auteur, "prix" : prix, "onSale" : onSale
            };
        }
    }
    updatePanier();
}

function changerCategorie(chooser) {
    var catalogue = document.getElementById("catalogue");

    catalogue.removeChild(catalogue.firstChild);

    var tab_livre = tab_librairie[chooser.value];
    slide = document.createElement("tr");
    for (i = 0;i < tab_livre.length;i++) {
        elem = document.createElement("td");
        
        elem.appendChild(livreToDiv(tab_livre[i]));
        slide.appendChild(elem);
    }
    catalogue.appendChild(slide);
}

function livreToDiv(arg1) {
    var tmpLivre = arg1;
    var livre = document.createElement("div");
    var image = document.createElement("img");
    var titre = document.createElement("span");
    titre.className = "livre_title";
    var auteur = document.createElement("span");
    auteur.className = "livre_auteur";
    var prix = document.createElement("span");
    prix.className = "livre_prix";

    var ajouter = document.createElement("input");
    if (tmpLivre["onSale"].length<5) {
		livre.className = "livreSpecial";
    }
	else{
		livre.className = "livre";
	}
	ajouter.setAttribute("type", "button");
    ajouter.setAttribute("value", "Ajouter");
    ajouter.className = "ajouter";
    ajouter.onclick = function () {
        ajouterPanier(tmpLivre);
    };    	
    image.className = "iconlivre";
    image.setAttribute("src", "resources/images/livres/" + tmpLivre["isbn"] + ".jpg");
    titre.className = "titre";
    titre.appendChild(document.createTextNode(tmpLivre["titre"]));
    auteur.appendChild(document.createTextNode(tmpLivre["auteur"]));
    prix.appendChild(document.createTextNode(tmpLivre["prix"]));
    livre.appendChild(image);
    livre.appendChild(titre);
    livre.appendChild(auteur);
    livre.appendChild(prix);
    livre.appendChild(ajouter);

    return livre;
}

function ajouterPanier(localLivre) {
    if (tab_panier.length < 8) {
        xhttp = new XMLHttpRequest();
        xhttp.open("POST", "Panier", false);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        console.log(JSON.stringify(localLivre));
        xhttp.send(encodeURI("action=add&isbn=" + localLivre.isbn + "&titre=" + localLivre.titre + "&auteur=" + localLivre.auteur + "&prix=" + localLivre.prix + "&onSale=" + localLivre.onSale));
        updatePanier();
        updateFacture();
    }

}

function enleverPanier(arg1) {
    var localLivre = tab_panier[arg1];
    xhttp = new XMLHttpRequest();
    xhttp.open("POST", "Panier", false);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send(encodeURI("action=remove&isbn=" + localLivre.isbn + "&titre=" + localLivre.titre + "&auteur=" +localLivre.auteur + "&prix=" + localLivre.prix + "&onSale=" + localLivre.onSale));
    updatePanier();
    updateFacture();
}

function updatePanier() {
    xhttp = new XMLHttpRequest();
    xhttp.open("GET", "Panier", false);
    xhttp.send();
    xmlDoc = xhttp.responseXML;
    tab_panier = [];
    var tab_livre = xmlDoc.getElementsByTagName("livre");
    var c = tab_livre.length;
    for (i = 0;i < c;i++) {
        var tmp_isbn = tab_livre[i].getAttribute('isbn');
        var tmp_titre = tab_livre[i].getAttribute('titre');
        var tmp_auteur = tab_livre[i].getAttribute('auteur');
        var tmp_prix = tab_livre[i].getAttribute('prix');
        var tmp_onSale = tab_livre[i].getAttribute('onSale');
        var tmp_livre = {
            "isbn" : tmp_isbn, "titre" : tmp_titre, "auteur" : tmp_auteur, "prix" : tmp_prix, "onSale" : tmp_onSale
        };
        tab_panier.push(tmp_livre);
    }

    var tabPanier = document.createElement("table");
    tabPanier.id = "tab_panier";

    var entete = document.createElement("thead");
    var titre = document.createElement("th");
    titre.innerHTML = "Titre";
    var prix = document.createElement("th");
    prix.innerHTML = "Prix";
    var remove = document.createElement("th");

    entete.appendChild(titre);
    entete.appendChild(prix);
    entete.appendChild(remove);
    tabPanier.appendChild(entete);

    for (var i = 0;i < tab_panier.length;i++) {
        var livre = document.createElement("tr");
        livre.indice = i;
        var remove = document.createElement("td");
        remove.onclick = function () {
            enleverPanier(this.parentNode.indice);
        };
        var titre = document.createElement("td");
        titre.innerHTML = tab_panier[i]["titre"];
        var prix = document.createElement("td");
        prix.innerHTML = tab_panier[i]["prix"];
        var image = document.createElement("img");
        image.setAttribute("src", "resources/images/icons/remove-icon.png");
        image.className = "deleteIcon";

        remove.appendChild(image);
        livre.appendChild(titre);
        livre.appendChild(prix);
        livre.appendChild(remove);

        tabPanier.appendChild(livre);

    }

    var old = document.getElementById("tab_panier");
    document.getElementById("panier").replaceChild(tabPanier, old);
}

function updateFacture() {
    var soustotal = 0;
    var tps = 0;
    var tvq = 0;

    for (var i = 0;i < tab_panier.length;i++) {
        soustotal += parseFloat(tab_panier[i]["prix"]);

    }
    soustotal = Math.round((soustotal) * 100) / 100;
    tps = Math.round((soustotal * 0.05) * 100) / 100;
    tvq = Math.round((soustotal * 0.10) * 100) / 100;
    total = Math.round((soustotal + tps + tvq) * 100) / 100;

    document.getElementById("sousTotal").innerHTML = "SousTotal: " + soustotal + "$";
    document.getElementById("tps").innerHTML = "Tps: " + tps + "$";
    document.getElementById("tvq").innerHTML = "Tvq: " + tvq + "$";
    document.getElementById("total").innerHTML = "Total: " + total + "$";

}

function checkout() {
    alert("Montant total a payer: " + total + "$");
}