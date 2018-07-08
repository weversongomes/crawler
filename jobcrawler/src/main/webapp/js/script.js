/**
 * 
 */
function load() {
       var p = document.getElementById("profissao").value;
       var est  = document.getElementById("Estado").value;
       console.log(p);
       console.log(est);
       
      
       
       //Api?cargo=Agente+Financeiro&estado=BR
       var url = "Api?cargo="+p+"&estado="+est;
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
             d= d+"Cargo: "+jsonObj.Cargo+"</br>";
             d= d+"Regiao: "+jsonObj.Estado+"</br>";
             d= d+"Menor Salario: "+jsonObj.Min+" R$</br>";
             d= d+"Maior Salario: "+jsonObj.Max+" R$</br>";
             d= d+"Salario Medio: "+jsonObj.Med+" R$</br>";
             d= d+"Quantidade de vagas analisadas: "+jsonObj.Qtd+"</br>";

             document.getElementById("dados").innerHTML = d;
            
             
        }
       }
       ajax.open("GET", url, true);
       ajax.send();
       
  } 