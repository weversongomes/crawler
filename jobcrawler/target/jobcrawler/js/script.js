/**
 * 
 */
function load() {
       var p = document.getElementById("profissao").value;
       var est  = document.getElementById("Estado").value;
       //console.log(p);
       //console.log(est);
       
       var url = "Api?md=estatistica&cargo="+p+"&estado="+est;
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
             //console.log(ajax.responseText);          
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
function carregar(prof, reg) {
    var p = prof;
    var est  = reg;
    console.log(p);
    console.log(est);
    
    var url = "Api?md=dado&cargo="+p+"&estado="+est;
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
          console.log(jsonObj.length); 
          var d = "";
          for (var i = 1; i < jsonObj.length; i++) {
        	  d= d+"cidade: "+jsonObj[i].city+"</br>";
              d= d+"estado: "+jsonObj[i].est+"</br>";
              d= d+"profisao: "+jsonObj[i].prof+" </br>";
              d= d+"salario: "+jsonObj[i].sl+" R$</br>";
              d= d+"descricao: "+jsonObj[i].desc+"</br></br>";
            
          }
         
          

          document.getElementById("dados").innerHTML = d;
         
          
     }
    }
    ajax.open("GET", url, true);
    ajax.send();
    
	
}