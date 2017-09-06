app.controller("controladorTrasladosFuncionarios", function($scope, $http, $window,
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
	$scope.funcionario = '';

	
	$scope.realizarTraslado = function() {
		if ($scope.cedula == '') {
			alert("Debe ingresar una cedula del funcionario");
		} else if (document.getElementById("sede").selectedIndex == "0") {
			alert("Debe seleccionar una sede");
		} else {
			$scope.buscarFuncionario();
			
			window.setTimeout(function() {
				if ($scope.funcionario != '') {
					//if ($scope.funcionario.sede.nombre != $scope.sede.nombre) {

					var dato3 = ({
						
						
						cedula : $scope.cedula,
						cargo : $scope.cargo,
						nombre : $scope.nombre,
						telefono : $scope.telefono,
						fechaIngreso: $scope.fechaIngreso,
						tipoContrato : $scope.tipoContrato,
						eps : $scope.eps,
						salario : $scope.salario,
						usuario : $scope.usuario,
						contrasenia : $scope.contrasenia,
						sede : $scope.sede
						
					});
					$http({
						url : 'rest/funcionariosede/trasladarFuncionario',
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
//				}else{
//					alert("error, Este funcionario ya pertenece a esta sede");
//				}
				}
			}, 1800);
		
		}
	}

	/**
	 * busca un funcionario
	 */
	$scope.buscarFuncionario = function() {
		var dato2 = $.param({
			nombre : $scope.cedula
		});
		$http({
			url : 'rest/funcionario/buscarF',
			method : "POST",
			data : dato2,
			headers : {
				"Content-Type" : "application/x-www-form-urlencoded",
				"Authorization" : $sessionStorage.token
			}
		}).success(function(data, status, headers, config) {
			if (data.codigo == '00') {
				$scope.funcionario = data.obj;
			} else {
				alert(data.mensaje);
			}
		}).error(function(data, status, headers, config) {
			alert('error::' + data.mensaje);
		});
	}

	$scope.limpiarCampos = function() {
		$scope.cedula = '';
		$scope.funcionario = '';
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
