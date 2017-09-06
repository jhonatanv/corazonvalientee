app.controller("controladorSedes", function($scope, $http, $window, $sessionStorage, $filter) {

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
	
	
	$scope.accesos = false;
	
	$scope.filtro = function() {
		for (var i = 0; i < $sessionStorage.rutas.length; i++) {
			if (window.location.hash == $sessionStorage.rutas[i].url) {
				$scope.accesos = true;
			}
		}
		if ($scope.accesos == false) {
				window.location.href = '/corazonvaliente-web/menu.html#/';
		}
	}
	
	$scope.filtro();
	
	
	$scope.ciudad = '';
	$scope.ciudades = [];
	$scope.sedes = [];
	$scope.nombre = '';
	$scope.telefono = '';
	$scope.direccion = '';
	
	
	
	
	
	
	/**
	 * crea una sede
	 */
	$scope.crear = function() {
		if($scope.nombre !='' && $scope.fecha!='' && $scope.telefono!='' && $scope.ciudad != 0){
							var dato = ({
							ciudad: $scope.ciudad,
							nombre: $scope.nombre,
							telefono: $scope.telefono,
							direccion: $scope.direccion
				});
				$http({
					url : 'rest/sede/crear',
					method : "POST",
					data : dato,
					headers : {
						"Content-Type" : "application/json",
						"Authorization" : $sessionStorage.token
					}
				}).success(function(data, status, headers, config) {
					if (data.codigo == '00') {
						alert(data.mensaje);
						$scope.listarSedes();
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
	 * editar una sede
	 */
	$scope.editar = function() {
		
		if($scope.nombre !='' && $scope.fecha!='' && $scope.telefono!='' && $scope.ciudad != 0){
							var dato = ({
							ciudad: $scope.ciudad,
							nombre: $scope.nombre,
							telefono: $scope.telefono,
							direccion: $scope.direccion
				});
				$http({
					url : 'rest/sede/editar',
					method : "POST",
					data : dato,
					headers : {
						"Content-Type" : "application/json",
						"Authorization" : $sessionStorage.token
					}
				}).success(function(data, status, headers, config) {
					if (data.codigo == '00') {
						alert(data.mensaje);
						$scope.listarSedes();
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
				url : 'rest/sede/buscar',
				method : "POST",
				data : dato2,
				headers : {
					"Content-Type" : "application/x-www-form-urlencoded",
					"Authorization" : $sessionStorage.token
				}
			}).success(function(data, status, headers, config) {
				if (data.codigo == '00') {
					$scope.telefono = parseInt(data.obj.telefono);
					$scope.direccion = data.obj.direccion;
					
					var numeroCiudad=0;
					for (var int = 0; int < $scope.ciudades.length; int++) {
						if($scope.ciudades[int].nombre == data.obj.ciudad.nombre){
							
							numeroCiudad = int;
						}
					}
					$scope.ciudad = $scope.ciudades[numeroCiudad];

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
	//$scope.listarSedes();

//
//
//			} else {
//				alert(data.mensaje);
//			}
//		}).error(function(data, status, headers, config) {
//			alert('error::' + data.mensaje);
//		});
//	}
	
	
	/**
	 * lista las sedes
	 */
	$scope.listarSedes = function() {
		
		
		$http({
			url : 'rest/sede/listarSedes',
			method : "GET",
			headers : {
				"Authorization" : $sessionStorage.token
			}
		}).success(function(data, status, headers, config) {
			if (data.codigo == '00') {
				$scope.sedes = data.obj;
				
			}

		}).error(function(data, status, headers, config) {

			alert('error::' + data.mensaje);
		});

	}
	
	
	
	
	$scope.listarCiudades = function() {

		$http({
			url : 'rest/sede/listarCiudades',
			method : "GET",
			headers : {
				"Authorization" : $sessionStorage.token
			}
		}).success(function(data, status, headers, config) {
			if (data.codigo == '00') {
				$scope.ciudades = data.obj;
			} else {
				alert(data.mensaje);
			}

		}).error(function(data, status, headers, config) {

			alert('error::' + data.mensaje);
		});

	}
	
	$scope.limpiarCampos = function() {
	$scope.ciudad = '';
	$scope.nombre = '';
	$scope.telefono = '';
	$scope.direccion = '';
	}
	
	
	$scope.listarCiudades();
	$scope.listarSedes();
	
});


