/**
 * 
 */
function load() {
       var p = document.getElementById("profissao").value;
       var est  = document.getElementById("Estado").value;
       
       //document.getElementById("div_canvas").style.display = "block";
      
       //console.log(p);
       //console.log(est);
       
       var min_e =0;
       var med_e=0;
       var max_e=0;
       var qtd_e =0;
       
       var min_b =0;
       var med_b=0;
       var max_b=0;
       var qtd_b =0;
       if(est=="Brasil"){
       	est="BR";
       }
       
       var url = "Api?md=estatistica&cargo="+p+"&estado=BR";
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
             var min_b = jsonObj.Min;
             var med_b = jsonObj.Med;
             var max_b = jsonObj.Max;
             var qtd_b = jsonObj.Qtd;
             var d = "";
            
             
             //consulta poe estado
             url = "Api?md=estatistica&cargo="+p+"&estado="+est;
             //var ajax;
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
                   var min_e = jsonObj.Min;
                   var med_e = jsonObj.Med;
                   var max_e = jsonObj.Max;
                   var qtd_e = jsonObj.Qtd;
                
                   d= d+"<table class='table  table-hover table-bordered tabela'>"
                   d= d+"  <thead>"
                   d= d+"    <tr>"
                   d= d+"      <th scope='col'></th>"
                   d= d+"      <th scope='col'>Estado</th>"
                   d= d+"      <th scope='col'>Brasil</th>"
                   d= d+"    </tr>"
                   d= d+"  </thead>"
                   d= d+"  <tbody>"
                   d= d+"    <tr>"
                   d= d+"      <th scope='row'>Menor Salário</th>"
                   d= d+"      <td>R$&nbsp"+min_e+",00</td>"
                   d= d+"      <td>R$&nbsp"+min_b+",00</td>"
                   d= d+"    </tr>"
                   d= d+"    <tr>"
                   d= d+"      <th scope='row'>Salário Médio</th>"
                   d= d+"      <td>R$&nbsp"+med_e+",00</td>"
                   d= d+"      <td>R$&nbsp"+med_b+",00</td>"
                   d= d+"    </tr>"
                   d= d+"    <tr>"
                   d= d+"      <th scope='row'>Maior Salário</th>"
                   d= d+"      <td>R$&nbsp"+max_e+",00</td>"
                   d= d+"      <td>R$&nbsp"+max_b+",00</td>"
                   d= d+"    </tr>"
                   d= d+"    <tr>"
                   d= d+"      <th scope='row'>Quantidade analisadas</th>"
                   d= d+"      <td>"+qtd_e+"</td>"
                   d= d+"      <td>"+qtd_b	+"</td>"
                   d= d+"    </tr>"
                   d= d+"  </tbody>"
                   d= d+"</table>"
                  

                   document.getElementById("dados").innerHTML = d;
                  
                  
                   
                   
                  
                   var ctx = document.getElementById('myChart').getContext('2d');
                   var chart = new Chart(ctx, {
	               	    // The type of chart we want to create
	               		type: 'bar',
	
	               	    // The data for our dataset
	               	    data: {
	               	        labels: ["Mínimo", "Médio", "Máximo"],
	               	        datasets: [{
	               	            label: "Estado",
	               	            backgroundColor: ['rgb(255, 99, 132)','rgb(255, 99, 132)','rgb(255, 99, 132)'],
	               	            borderColor: 'rgb(0, 0, 0)',
	               	            data: [ min_e, med_e, max_e],
	               	        },
	               	        	{
	               	        	label: "Brasil",
	               	        	backgroundColor: ['#007bff','#007bff','#007bff'],
	               	            borderColor: 'rgb(0, 0, 0)',
	               	            data: [  min_b, med_b, max_b],
	               	        	
	               	        }]
	               	    },
	
	               	    // Configuration options go here
	               	    options: {
		               	     title: {
		                         display: true,
		                         text: 'Gráfico Salarial',
		                         position: 'top',
		                         fontSize: 16
		                     },
		                     layout: {
		                         padding: {
		                             left: 20,
		                             right: 20,
		                             top: 20,
		                             bottom: 20
		                         }
		                     },
		                     legend: {
		                         display: true,
		                         //text: "R$",
		                         //position: 'left',
		                         
		                     },
		                     scales: {
		                    	 yAxes: [{
		                    		
		                    		 position:"left",
		                    		 scaleLabel:{
		                    			 labelString:"Valor em Reais",
		                    			 display:true,
		                    			 fontSize: 14
		                    			 },
		                    		 ticks:{suggestedMin:16}
		                    		 
		                         }]
		                     }
	               	    }
	               	});
	               
	               //fim grafico
              }
             }
             ajax.open("GET", url, true);
             ajax.send();
             
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
function graf(min,med,max, prof){
	var ctx = document.getElementById('myChart').getContext('2d');
	var chart = new Chart(ctx, {
	    // The type of chart we want to create
		type: 'bar',

	    // The data for our dataset
	    data: {
	        labels: ["Minimo", "Médio", "Máximo"],
	        datasets: [{
	            label: prof,
	            backgroundColor: ['rgb(255, 99, 132)', 'rgb(100, 150, 200)', 'rgb(200, 150, 200)'],
	            borderColor: 'rgb(0, 0, 0)',
	            data: [ min, med, max],
	        }]
	    },

	    // Configuration options go here
	    options: {}
	});
}