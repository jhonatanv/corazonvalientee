app.controller("controladoFuncionariosEjecutivos", function($scope, $http, $window, $sessionStorage, $filter) {

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
	
	
	$scope.funcionarios = [];
	$scope.cedula = '';
	$scope.funcionario = null;

	
	/**
	 * lista funcionarios Ejecutivos
	 */
	$scope.listarFuncionarios = function() {

		$http({
			url : 'rest/funcionario/listarFuncionariosEjecutivos',
			method : "GET",
			headers : {
				"Authorization" : $sessionStorage.token
			}
		}).success(function(data, status, headers, config) {
			if (data.codigo == '00') {
				$scope.funcionarios = data.obj;
				for (var int = 0; int < $scope.funcionarios.length; int++) {
				if($scope.funcionarios[int].estado == true){
					$scope.funcionarios[int].estado = 'activo';
				}else{
					$scope.funcionarios[int].estado = 'inactivo';
				}
				}

			}else{
				alert(data.mensaje);
			}

		}).error(function(data, status, headers, config) {

			alert('error::' + data.mensaje);
		});

	}
	
$scope.desactivar = function() {
	if ($scope.cedula != '') {
		var dato2 = $.param({
			cedula : $scope.cedula
		});
		$http({
			url : 'rest/funcionario/eliminar',
			method : "POST",
			data : dato2,
			headers : {
				"Content-Type" : "application/x-www-form-urlencoded",
				"Authorization" : $sessionStorage.token
			}
		}).success(function(data, status, headers, config) {
			if (data.codigo == '00') {
				alert("Funcionario desactivado");
				$scope.listarFuncionarios();

			} else {
			

				alert("Este funcionario no esta activo o registrado");
			}
		}).error(function(data, status, headers, config) {
			alert('error::' + data.mensaje);
		});
		
	}else{
		alert('Ingrese un numero de cedula');
	}
	}




$scope.activar = function() {
	if ($scope.cedula != '') {
	$scope.buscar();
	window.setTimeout(function() {
	if($scope.funcionario != null){
		if($scope.funcionario.estado == false){
						var dato = ({
							cedula : $scope.funcionario.cedula,
							cargo : $scope.funcionario.cargo,
							nombre : $scope.funcionario.nombre,
							telefono : $scope.funcionario.telefono,
							fechaIngreso: $scope.funcionario.fechaIngreso,
							tipoContrato : $scope.funcionario.tipoContrato,
							eps : $scope.funcionario.eps,
							salario : $scope.funcionario.salario,
							usuario : $scope.funcionario.usuarioL,
							contrasenia : $scope.funcionario.contrasenia,
							estado : 'true'

			});
			$http({
				url : 'rest/funcionario/editar',
				method : "POST",
				data : dato,
				headers : {
					"Content-Type" : "application/json",
					"Authorization" : $sessionStorage.token
				}
			}).success(function(data, status, headers, config) {
				if (data.codigo == '00') {
					alert("Funcionario activado");
					$scope.listarFuncionarios();					
				} else {
					alert(data.mensaje);
				}
			}).error(function(data, status, headers, config) {
				alert('error::' + data.mensaje);
			});
		}else{
			alert('Este funcionario ya se encuentra activo');

		}
	}else{
		alert('Este funcionario no esta registrado');

	}
	}, 1800);
	}else{
		alert('Ingrese un numero de cedula');
	}
		
}



$scope.buscar = function() {
	$scope.funcionario = null;
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
		}
			
	}).error(function(data, status, headers, config) {
		alert('error::' + data.mensaje);
	});
}
	
	$scope.listarFuncionarios();
	
});


