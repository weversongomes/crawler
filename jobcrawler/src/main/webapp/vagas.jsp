<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="pt">
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<link rel="stylesheet" href="css/estilo.css">
	<script type="text/javascript" src="js/script.js" charset="utf-8"></script>
	<title>Vagas</title>
</head>

<body>
	<div class="top container-fluid">
		<div class="logo">WM Empregos</div>
		
	</div>
    <div class="main container">
    	
    	<div class="row"">
    		

    		<div id="dados-prof">
    		</div>
    	</div>
    	<div class=row>
    		<div class="pesquisa container-fluid">
    			<div class="container-fluid ">
    				<div class="row justify-content-center t">
    					<h2>Ver vagas disponíveis</h2>
    				</div>
    				
					<h4 class="font-weight-normal">Verifique as vagas de empregos disponiveis em sua região.</h4>
    			</div>
    			
	    		<div>
				  <div class="form-row">
				    <div class="form-group col-md-6">
				      <label for="profissao">Profissão:</label>
				      <input type="text" class="form-control" id="profissao" name="cargo" placeholder="Digite um cargo (Ex: Engenheiro de Computação)" required autofocus>
				    </div>
				    <div class="form-group col-md-4">
				      <label for="Estado">Região:</label>
				      <select id="Estado" class="form-control" name="estado">
				        <option selected>Brasil</option>
				        <option>AC</option>
				        <option>AL</option>
				        <option>AP</option>
				        <option>AM</option>
				        <option>BA</option>
				        <option>CE</option>
				        <option>DF</option>
				        <option>ES</option>
				        <option>GO</option>
				        <option>MA</option>
				        <option>MT</option>
				        <option>MS</option>
				        <option>MG</option>
				        <option>PA</option>
				        <option>PB</option>
				        <option>PR</option>
				        <option>PE</option>
				        <option>PI</option>
				        <option>RJ</option>
				        <option>RN</option>
				        <option>RS</option>
				        <option>RO</option>
				        <option>RR</option>
				        <option>SC</option>
				        <option>SP</option>
				        <option>SE</option>
				        <option>TO</option> 
				      </select>
				    </div>
				    <div class="form-group col align-self-end">
				    	<button onclick="carregar()" class="btn btn-primary bt">Ver</button>
				    </div>
				  </div>
				</div>
    		</div>
    		
    		<div class="resultado container">
    			<div class="row justify-content-center" id="dados">
    				
    			</div>
    			
    		</div>
    	</div>
    
	   
	</div>

	<div class="rodape container-fluid">
		<div class="copy">
			<p>&copy; WM Empregos -2018</p>
		</div>
	
	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>

</body>
</html>