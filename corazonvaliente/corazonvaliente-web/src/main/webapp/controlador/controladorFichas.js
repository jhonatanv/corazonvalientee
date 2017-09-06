app.controller("controladorFichas", function($scope, $http, $window,
		$sessionStorage, $filter) {

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
	
	$scope.fotoB = '';
	$scope.nombre = '';
	$scope.fichas = [];
	$scope.cedula = '';
	$scope.codigo = '';
	$scope.observacion = '';
	$scope.fechaSalida = '';
	
	/**
	 * busca un usuario
	 */
	$scope.buscarUsuario = function() {
		if ($scope.cedula == '') {
			alert("Debe ingresar una cedula de usuario");
		} else {
			var dato2 = $.param({
				cedula : $scope.cedula
			});
			$http({
				url : 'rest/usuario/buscarUsuarioFicha',
				method : "POST",
				data : dato2,
				headers : {
					"Content-Type" : "application/x-www-form-urlencoded",
					"Authorization" : $sessionStorage.token
				}
			}).success(function(data, status, headers, config) {
				if (data.codigo == '00') {
					$scope.nombre = data.obj.nombre;
					$scope.fotoB = data.obj.imagen;
					$scope.listarFichas();
				} else {
					alert(data.mensaje);
				}
			}).error(function(data, status, headers, config) {
				alert('error::' + data.mensaje);
			});
		}
	}

	/**
	 * lista las fichas de un usuario
	 */
	$scope.listarFichas = function() {
		var dato2 = $.param({
			cedula : $scope.cedula
		});
		$http({
			url : 'rest/ficha/listarFichas',
			method : "POST",
			data : dato2,
			headers : {
				"Content-Type" : "application/x-www-form-urlencoded",
				"Authorization" : $sessionStorage.token
			}
		}).success(function(data, status, headers, config) {
			if (data.codigo == '00') {
				$scope.fichas = data.obj;
			}
		}).error(function(data, status, headers, config) {
			alert('error::' + data.mensaje);
		});
	}
	
	
	/**
	 * registrar una ficha de salida
	 */
	$scope.registarFicha = function() {	
			if($scope.codigo == '' || $scope.observacion == '' || $scope.fechaSalida==''){
		 		alert("Todos los campos son obligatorios");
		 	}else {
		 			var dato3 = ({
		 				codigo : $scope.codigo,
		 				observacion : $scope.observacion,
		 				fechaSalida: $scope.fechaSalida,
		 				usuario: null,
		 				sede: null,
		 				fechaEntrada: null,
		 				estadoUsuario: false
					});
		 			var obj=({
		 				ficha: dato3,
		 				sede: $sessionStorage.sede
		 			});
		 			
					$http({
						url : 'rest/ficha/editarFicha',
						method : "POST",
						data : obj,
						headers : {
							"Content-Type" : "application/json",
							"Authorization" : $sessionStorage.token
						}
					}).success(function(data, status, headers, config) {
						if (data.codigo == '00') {
							alert(data.mensaje);
							$scope.limpiarCampos();
							$scope.listarFichas();
						} else {
							alert(data.mensaje);
						}
					}).error(function(data, status, headers, config) {
						alert('error::' + data.mensaje);
					});
		 	}
	}
	
	/**
	 * limpia los campos de registrar ficha
	 */
	$scope.limpiarCampos= function(){
		$scope.codigo = '';
		$scope.observacion = '';
		$scope.fechaSalida = '';
	}
	
	
	/**
	 * limpia la interfaz
	 */
	$scope.cancelar= function(){
		$scope.fotoB = '';
		$scope.nombre = '';
		$scope.fichas = [];
		$scope.cedula = '';
		$scope.codigo = '';
		$scope.observacion = '';
		$scope.fechaSalida = '';
	}
	
});
