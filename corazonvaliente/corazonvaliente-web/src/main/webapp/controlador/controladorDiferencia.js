app.controller("controladorDiferencia", function($scope, $http, $window, $sessionStorage, $filter) {

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
	
	$scope.ingresos = [];
	$scope.egresos = [];
	
	$scope.ingreso =0;
	$scope.egreso =0;

	$scope.total= 0;
	

		

	/**
	 * lista las ingresos
	 */
	$scope.listarIngresos = function() {
		var dato2 = $.param({
			sede : $sessionStorage.sede.nombre
		});
		
			$http({
				url : 'rest/ingreso/listarTodosIngresos',
				method : "POST",
				data : dato2,
				headers : {
					"Content-Type" : "application/x-www-form-urlencoded",
					"Authorization" : $sessionStorage.token
				}		
		}).success(function(data, status, headers, config) {
			if (data.codigo == '00') {
				$scope.ingresos = data.obj;
				for(var i= 0;i<$scope.ingresos.length;i++){
					$scope.ingreso=($scope.ingreso+ $scope.ingresos[i].valor);	
				}				
			}else{
				alert("No hay registros de ingresos");
			}

		}).error(function(data, status, headers, config) {

			alert('error::' + data.mensaje);
		});

		}
	

	/**
	 * lista las egresos
	 */
	$scope.listarEgresos = function() {
		
		var dato2 = $.param({
			sede : $sessionStorage.sede.nombre
		});
			$http({
				url : 'rest/egreso/listarTodosEgresos',
				method : "POST",
				data : dato2,
				headers : {
					"Content-Type" : "application/x-www-form-urlencoded",
					"Authorization" : $sessionStorage.token
				}	
		}).success(function(data, status, headers, config) {
			if (data.codigo == '00') {
				$scope.egresos = data.obj;
				for(var i= 0;i<$scope.egresos.length;i++){
					$scope.egreso=(parseInt($scope.egreso)+parseInt($scope.egresos[i].valor));	
				}
				
				$scope.total = ($scope.ingreso-$scope.egreso);
				
			}else{
				alert("No hay registros de egresos");
			}

		}).error(function(data, status, headers, config) {

			alert('error::' + data.mensaje);
		});

		}
			

	
	$scope.constructor=function(){
		$scope.listarEgresos();
		$scope.listarIngresos();
		
	}

	$scope.constructor();

	

});


