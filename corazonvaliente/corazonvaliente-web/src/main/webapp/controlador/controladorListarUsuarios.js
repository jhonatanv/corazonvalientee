app.controller("controladorListarUsuarios", function($scope, $http, $window,
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

	$scope.usuarios = [];

	$scope.listarUsuarios = function() {
		var dato2 = ({
			nombre : $sessionStorage.sede.nombre,
			ciudad : $sessionStorage.sede.ciudad,
			telefono : $sessionStorage.sede.telefono,
			direccion : $sessionStorage.sede.direccion
		});
		$http({
			url : 'rest/ficha/listarUsuariosSede',
			method : "POST",
			data : dato2,
			headers : {
				"Content-Type" : "application/json",
				"Authorization" : $sessionStorage.token
			}
		}).success(function(data, status, headers, config) {
			if (data.codigo == '00') {
				$scope.usuarios = data.obj;
			} else {
				alert(data.mensaje);
			}
		}).error(function(data, status, headers, config) {
			alert('error::' + data.mensaje);
		});
	}

	$scope.listarUsuarios();

});
