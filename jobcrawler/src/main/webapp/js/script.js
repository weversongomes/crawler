/**
 * 
 */
function load() {
       var p = document.getElementById("profissao").value;
       var est  = document.getElementById("Estado").value;
       //console.log(p);
       //console.log(est);
       if(est=="Brasil"){
       	est="BR";
       }
       
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
	var p = document.getElementById("profissao").value;
    var est  = document.getElementById("Estado").value;
    //console.log(p);
    //console.log(est);
    if(est=="Brasil"){
    	est="BR";
    }
    if(p==""){
    	p="ALL";
    }
    
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
          for (var i = 0; i < jsonObj.length; i++) {
        	  d= d+"<div class='col-lg-3 col-md-4 col-sm-6 vagas'>";
        	  d =d+"<div class='row conteudo'>";
        	  d= d+"<div class='row container-fluid info'><h5>"+jsonObj[i].prof+"</h5></div>";
        	  d= d+"<div class='row info'><span>Cidade:&nbsp</span> "+jsonObj[i].city+"</div>";
              d= d+"<div class='row info'><span>Estado:&nbsp</span> "+jsonObj[i].est +"</div>";
              d= d+"<div class='row info'><span>Salário:&nbsp</span> ";
              if(jsonObj[i].sl==0){
            	  d= d+"Não informado"+"</div>";
              }else{
            	  d= d+jsonObj[i].sl +",00 R$</div>";
              }
              d= d+"<div class='row info'><span>Descrição: </span></br><p class='text-justify'>"+jsonObj[i].desc+"</p></div>";
              d= d+"</div>";
              d= d+"</div>";
          }
         
          

          document.getElementById("dados").innerHTML = d;
         
          
     }
    }
    ajax.open("GET", url, true);
    ajax.send();
    
	
}