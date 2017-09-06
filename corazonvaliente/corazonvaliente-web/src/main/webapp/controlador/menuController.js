app.controller("menuController", function($scope, $http, $window,
		$sessionStorage) {

	$scope.rutas2 = $sessionStorage.rutas;

	$scope.accesos = false;
	$scope.selecSede = false;

	$scope.filtro = function() {
		if ($sessionStorage.rutas) {
			if ($sessionStorage.rutas.length > 0) {
				$scope.accesos = true;
			} else {
				window.location.href = '/corazonvaliente-web/index.html#/';
			}
		} else {
			window.location.href = '/corazonvaliente-web/index.html#/';
		}
	}

	$scope.filtro();

	/**
	 * activa el boton seleccionar sede
	 */
	$scope.activarSelecSede = function() {
		if ($sessionStorage.rutas) {
			if ($sessionStorage.rutas.length > 0) {
				for (var i = 0; i < $sessionStorage.rutas.length; i++) {
					if ('#/elegirSede' == $sessionStorage.rutas[i].url) {
						$scope.selecSede = true;
					}
				}
			}
		}
	}

	$scope.activarSelecSede();

	/**
	 * limpia el storage
	 */
	$scope.limpiarStorage = function() {
		$sessionStorage.rutas = [];
		$sessionStorage.user = "";
		$sessionStorage.sede = "";
		$sessionStorage.token = "";
	}

	/**
	 * cierra la sesion
	 */
	$scope.cerrarSesion = function() {
		var dato2 = $.param({
			token : $sessionStorage.token
		});
		$http({
			url : 'rest/login/cerrarSesion',
			method : "POST",
			data : dato2,
			headers : {
				"Content-Type" : "application/x-www-form-urlencoded"
			}
		}).success(function(data, status, headers, config) {
			if (data.codigo == '00') {
				$scope.limpiarStorage();
				window.setTimeout(function() {
					window.location.href = data.obj;
				}, 1800);
			}
		}).error(function(data, status, headers, config) {
			alert('error::' + data.mensaje);
		});
	}

	/**
	 * redirige a la pagina de seleccionar sede
	 */
	$scope.seleccionarSede = function() {
		window.location.href = '/corazonvaliente-web/paginas/elegirSede.html#/';
	}

});