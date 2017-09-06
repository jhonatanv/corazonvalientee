app.controller("controladorCargos", function($scope, $http, $window, $sessionStorage, $filter) {

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

	$scope.nombre = '';
	$scope.descripcion = '';
	

	
	/**
	 * crea un cargo
	 */
	$scope.crear = function() {
		if($scope.nombre !='' && $scope.descripcion!=''){
							var dato = ({
							nombre: $scope.nombre,
							descripcion: $scope.descripcion
				});
				$http({
					url : 'rest/cargo/crear',
					method : "POST",
					data : dato,
					headers : {
						"Content-Type" : "application/json",
						"Authorization" : $sessionStorage.token
					}
				}).success(function(data, status, headers, config) {
					if (data.codigo == '00') {
						alert(data.mensaje);
						$scope.limpiarCampos();

					} else {
						alert(data.mensaje);
					}
				}).error(function(data, status, headers, config) {
					alert('error::' + data.mensaje);
				});
		}else{
			alert('favor llenar todo los campos correctamente');

		}
			
	}
	
	
	
	/**
	 * edita
	 */
	$scope.editar = function() {
		
		if($scope.nombre !='' && $scope.descripcion!=''){
							var dato = ({
							nombre: $scope.nombre,
							descripcion: $scope.descripcion,
				});
				$http({
					url : 'rest/cargo/editar',
					method : "POST",
					data : dato,
					headers : {
						"Content-Type" : "application/json",
						"Authorization" : $sessionStorage.token
					}
				}).success(function(data, status, headers, config) {
					if (data.codigo == '00') {
						alert(data.mensaje);
						$scope.limpiarCampos();
					} else {
						alert(data.mensaje);
					}
				}).error(function(data, status, headers, config) {
					alert('error::' + data.mensaje);
				});
		}else{
			alert('favor llenar todo los campos correctamente');

		}
			
	}
	
	
	
	
	$scope.buscar = function() {
		
			var dato2 = $.param({
				nombre : $scope.nombre
			});
			$http({
				url : 'rest/cargo/buscar',
				method : "POST",
				data : dato2,
				headers : {
					"Content-Type" : "application/x-www-form-urlencoded",
					"Authorization" : $sessionStorage.token
				}
			}).success(function(data, status, headers, config) {
				if (data.codigo == '00') {
					
					$scope.descripcion = data.obj.descripcion;

				} else {
					alert(data.mensaje);
				}
			}).error(function(data, status, headers, config) {
				alert('error::' + data.mensaje);
			});
		}
	
	
//	$scope.eliminar = function() {
//		
//		var dato2 = $.param({
//			nombre : $scope.nombre
//		});
//		$http({
//			url : 'rest/sede/eliminar',
//			method : "POST",
//			data : dato2,
//			headers : {
//				"Content-Type" : "application/x-www-form-urlencoded"
//			}
//		}).success(function(data, status, headers, config) {
//			if (data.codigo == '00') {
//				alert(data.mensaje);

//
//
//			} else {
//				alert(data.mensaje);
//			}
//		}).error(function(data, status, headers, config) {
//			alert('error::' + data.mensaje);
//		});
//	}
	
	$scope.limpiarCampos = function() {
	$scope.nombre = '';
	$scope.descripcion = '';
	}
});


