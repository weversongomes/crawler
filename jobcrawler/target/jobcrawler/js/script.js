/**
 * 
 */
function load() {
       var p = document.getElementById("profissao").value;
       var est  = document.getElementById("Estado").value;
       console.log(p);
       console.log(est);
       
       var d = "";
       d= d+p+"</br>"
       d= d+est+"</br>"
       document.getElementById("dados").innerHTML = d;
       /*
       //Api?cargo=Agente+Financeiro&estado=BR
       var url = "/jobcrawler/Api?cargo="+p+"estado="+est;
       var ajax;
       if (window.XMLHttpRequest){
            ajax = new XMLHttpRequest();//para Chrome, mozilla etc
       } else if(window.ActiveXObject){
            ajax = new ActiveXObject("Microsoft.XMLHTTP");//para IE apenas
       }
       ajax.onreadystatechange = function(){
        if (ajax.readyState == 4 ){
             console.log("recebido");
             var jsonObj = JSON.parse(ajax.responseText);//JSON.parse()
             
             //console.log(jsonObj.features.properties["txt"]);
             console.log(ajax.responseText);
             //document.getElementById("dados").innerHTML = ajax.responseText;
             
             //document.getElementById("dados").innerHTML = jsonObj.features[0].properties.txt;
             //console.log(jsonObj["features"]);
             
             var d = "";
             d= d+jsonObj.Cargo+"</br>";
             d= d+jsonObj.Estado+"</br>";
             d= d+jsonObj.Min+"</br>";
             d= d+jsonObj.Max+"</br>";
             d= d+jsonObj.Med+"</br>";
             d= d+jsonObj.Qtd+"</br>";

             document.getElementById("dados").innerHTML = d;
            
             
        }
       }
       ajax.open("GET", url, true);
       ajax.send();
       */
  } 