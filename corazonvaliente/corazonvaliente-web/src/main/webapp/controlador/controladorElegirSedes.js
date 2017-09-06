app.controller("controladorElegirSedes", function($scope, $http, $window,$sessionStorage) {
	


$scope.accesos = false;
$scope.cadena=window.location.pathname;
$scope.dato=$scope.cadena.split('/');
$scope.nombre=$scope.dato[3].split('.');
	
	$scope.filtro = function() {
		if($sessionStorage.rutas){
		if($sessionStorage.rutas.length>0){
		for (var i = 0; i < $sessionStorage.rutas.length; i++) {
			if ('#/'+$scope.nombre[0] == $sessionStorage.rutas[i].url) {
				$scope.accesos = true;
			}
		}
		if ($scope.accesos == false) {
				window.location.href = '/corazonvaliente-web/menu.html#/';
		}
		}else{
			window.location.href = '/corazonvaliente-web/index.html#/';
		}
	}else{
		window.location.href = '/corazonvaliente-web/index.html#/';
	}
}
	
$scope.filtro();
	
	
	$scope.sede = '';
	
	$scope.sedes = [];

	
	
	$scope.elegirSede = function() {
		
				$sessionStorage.sede=$scope.sede;
				window.setTimeout(function() {
					window.location.href = '/corazonvaliente-web/menu.html#/';
				}, 1800);
		}
	
	
	/**
	 * lista las sedes
	 */
	$scope.listarSedes = function() {
		
		$http({
			url : '../rest/sede/listarSedes',
			method : "GET",
			headers : {
				"Authorization" : $sessionStorage.token
			}
		}).success(function(data, status, headers, config) {
			if (data.codigo == '00') {
				$scope.sedes = data.obj;
				
			} else {
				alert(data.mensaje);
			}

		}).error(function(data, status, headers, config) {

			alert('error::' + data.mensaje);
		});
	}
		
		$scope.listarSedes();
		
});