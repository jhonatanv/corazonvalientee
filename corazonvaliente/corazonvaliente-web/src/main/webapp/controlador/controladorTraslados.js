app.controller("controladorTraslados", function($scope, $http, $window,
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

	
	$scope.sede = '';
	$scope.sedes = [];
	$scope.cedula = '';
	$scope.usuario = '';

	/**
	 * crea una entrevista para un usuario
	 */
	$scope.realizarTraslado = function() {
		if ($scope.cedula == '') {
			alert("Debe ingresar una cedula de usuario");
		} else if (document.getElementById("sede").selectedIndex == "0") {
			alert("Debe seleccionar una sede");
		} else {
			$scope.buscarUsuario();
			window.setTimeout(function() {
				if ($scope.usuario != '') {
					var dato3 = ({
						usuario : $scope.usuario,
						sede : $scope.sede
					});
					$http({
						url : 'rest/trasladoUsuario/trasladar',
						method : "POST",
						data : dato3,
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
				}
			}, 1800);
		}
	}

	/**
	 * busca un usuario
	 */
	$scope.buscarUsuario = function() {
		var dato2 =({
			cedula : $scope.cedula,
			sede: $sessionStorage.sede 
		});
		$http({
			url : 'rest/usuario/buscarUsuarios',
			method : "POST",
			data : dato2,
			headers : {
				"Content-Type" : "application/json",
				"Authorization" : $sessionStorage.token
			}
		}).success(function(data, status, headers, config) {
			if (data.codigo == '00') {
				$scope.usuario = data.obj;
			} else {
				alert(data.mensaje);
			}
		}).error(function(data, status, headers, config) {
			alert('error::' + data.mensaje);
		});
	}

	$scope.limpiarCampos = function() {
		$scope.cedula = '';
		$scope.usuario = '';
	}

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

	$scope.listarSedes();
});
