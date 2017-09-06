app.controller("controladorSeguimientos", function($scope, $http, $window, $sessionStorage, $filter) {

$scope.accesos = false;
	
$scope.filtro = function() {
	if ($sessionStorage.rutas) {
		if ($sessionStorage.rutas.length > 0) {
			for (var i = 0; i < $sessionStorage.rutas.length; i++) {
				if (window.location.hash == $sessionStorage.rutas[i].url) {
					$scope.accesos = true;
				}
			}
			if ($scope.accesos == false) {
				window.location.href = '/corazonvaliente-web/menu.html#/';
			}
		} else {
			window.location.href = '/corazonvaliente-web/index.html#/';
		}
	} else {
		window.location.href = '/corazonvaliente-web/index.html#/';
	}
}
	
$scope.filtro();

	
	$scope.usuarioB=null;

	
	$scope.usuario="";
	$scope.fecha="";
	$scope.observacion="";
	$scope.seguimientos=[];
	
	
	
	$scope.crear = function() {
		if( $scope.observacion !='' && $scope.fecha !=''){
		 	if($scope.usuario==''){
				alert("Debe ingresar una cedula de usuario");
		 	}else{
			 	$scope.buscarUsuario();		 		
		 		window.setTimeout(function() {
				 if($scope.usuarioB!=null){
					 var dato3 = ({
						funcionario : null,
						usuario: $scope.usuarioB,
						fecha: $scope.fecha,
						observacion: $scope.observacion
						});
					 
				 	var obj=({
				 		seguimiento:dato3,
				 		user: $sessionStorage.user
				 	});	
				 	
					$http({
						url : 'rest/seguimiento/crear',
						method : "POST",
						data: obj,
						headers : {
							"Content-Type" : "application/json",
							"Authorization" : $sessionStorage.token
						}
					}).success(function(data, status, headers, config) {
						if (data.codigo == '00') {
							$scope.buscarSeguimientos();
							alert(data.mensaje);
							$scope.limpiarCampos();
						} else {
							alert(data.mensaje);
						}
					}).error(function(data, status, headers, config) {
						alert('error::' + data.mensaje);
					});
			 	}
			}, 1800);		
		}
		}else{
	 		alert("LLenar todos los campos");

		}
	}
	
	
	/**
	 * busca los seguimientos de un usuario
	 */
	$scope.buscarSeguimientos=function () {
		$scope.buscarUsuario();
 		window.setTimeout(function() {
		if($scope.usuarioB!=null){
	 		var dato2 = $.param({
			cedula : $scope.usuario
		});
		$http({
			url : 'rest/seguimiento/buscarSeguimientos',
			method : "POST",
			data : dato2,
			headers : {
				"Content-Type" : "application/x-www-form-urlencoded",
				"Authorization" : $sessionStorage.token
			}
		}).success(function(data, status, headers, config) {
			if (data.codigo == '00') {	
				$scope.seguimientos= data.obj;				
			} else{
				alert(data.mensaje);
			}
		}).error(function(data, status, headers, config) {
			alert('error::' + data.mensaje);
		});
	 }}, 1800);	
}

	
	/**
	 * busca un usuario
	 */
	$scope.buscarUsuario=function () {
		var dato2 = ({
			cedula : $scope.usuario,
			sede : $sessionStorage.sede
		});
		$http({
			url : 'rest/usuario/buscarUsuario',
			method : "POST",
			data : dato2,
			headers : {
				"Content-Type" : "application/json",
				"Authorization" : $sessionStorage.token
					}
		}).success(function(data, status, headers, config) {
			if (data.codigo == '00') {	
				$scope.usuarioB= data.obj;				
			} else{
		 		alert("No se ha encontrado un usuario con la cedula ingresada");
			}
		}).error(function(data, status, headers, config) {
			alert('error::' + data.mensaje);
		});
	}

	
	
$scope.limpiarCampos = function() {
		
		$scope.fecha='';
		$scope.observacion='';
		$scope.usuarioB=null;

		
		
	}

});


