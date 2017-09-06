app.controller("controladorFuncionarios", function($scope, $http, $window, $sessionStorage, $filter) {

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
	
	$scope.filtro();
	$scope.cedula = '';
	$scope.estado = '';
	$scope.cargo = '';
	$scope.nombre = '';
	$scope.telefono = '';
	$scope.fechaIngreso = '';
	$scope.tipoContrato = '';
	$scope.eps = '';
	$scope.salario = '';
	$scope.sede = '';
	$scope.usuarioL = '';
	$scope.contrasenia = '';
	$scope.contrasenia.contraseniaVerificar= '';
	$scope.cargos = [];
	$scope.sedes = [];
	
	
	

	
	$scope.crear = function() {
		if($scope.contrasenia!=$scope.contraseniaVerificar){
			alert('Señor ususario, las contraseñas no coinciden');
		}else{
		if($scope.cedula !='' && $scope.cargo!=0 && $scope.nombre !='' && $scope.telefono!='' && $scope.fechaIngreso !='' &&
		$scope.tipoContrato !=0 && $scope.eps !='' && $scope.salario != '' && $scope.sede != 0){
							
			var dato = ({
								cedula : $scope.cedula,
								cargo : $scope.cargo,
								nombre : $scope.nombre,
								telefono : $scope.telefono,
								fechaIngreso: $scope.fechaIngreso,
								tipoContrato : $scope.tipoContrato,
								eps : $scope.eps,
								salario : $scope.salario,
								usuario : $scope.usuarioL,
								contrasenia : $scope.contrasenia,
								sede : $scope.sede
				});
				$http({
					url : 'rest/funcionario/crear',
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
	}
	
	
	
	/**
	 * edita
	 */
	$scope.editar = function() {
		if($scope.contrasenia!=$scope.contraseniaVerificar){
			alert('Señor ususario, las contraseñas no coinciden');
		}else{
		if($scope.cedula !='' && $scope.cargo!=0 && $scope.nombre !='' && $scope.telefono!='' && $scope.fechaIngreso !='' &&
				$scope.tipoContrato !=0 && $scope.eps !='' && $scope.salario != ''){
							var dato = ({
								cedula : $scope.cedula,
								cargo : $scope.cargo,
								nombre : $scope.nombre,
								telefono : $scope.telefono,
								fechaIngreso: $scope.fechaIngreso,
								tipoContrato : $scope.tipoContrato,
								eps : $scope.eps,
								salario : $scope.salario,
								usuario : $scope.usuarioL,
								contrasenia : $scope.contrasenia
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
	}
	
	
	
	
	$scope.buscar = function() {
		
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
					

					$scope.sede = null;
					$scope.cargo = null;
					
					$scope.nombre = data.obj.nombre;
					$scope.telefono = data.obj.telefono;
					$scope.eps = data.obj.eps;
					$scope.salario = data.obj.salario;
					$scope.usuarioL = data.obj.usuario;
					$scope.contrasenia = data.obj.contrasenia;
					
					
				var a =	document.getElementById("contrato").value= data.obj.tipoContrato;
				$scope.tipoContrato = a;
					
					if(data.obj.fechaIngreso!=null){
						var date = new Date(data.obj.fechaIngreso);
						var year = date.getFullYear();
						var month = (date.getMonth());
						var day = (date.getDate()+1);
						$scope.value = new Date(year, month, day);
						$scope.fechaIngreso= $scope.value;			
					}
					
					var numeroCargos=0;
					for (var int = 0; int < $scope.cargos.length; int++) {
						if($scope.cargos[int].nombre == data.obj.cargo.nombre){
							
							numeroCargos = int;
						}
					}
					$scope.cargo = $scope.cargos[numeroCargos];
					
					
					if(data.obj.sede != null){
						var numeroSedes=0;
						for (var int = 0; int < $scope.sedes.length; int++) {
							if($scope.sedes[int].nombre == data.obj.sede.nombre){
								numeroSedes = int;
							}
						}
						$scope.sede = $scope.sedes[numeroSedes];
					}
				
					if(data.mensaje == "0"){
						var a =	document.getElementById("estado").value= "activo";
						$scope.estado = a;			
					}else{
						if(data.obj.estado == true){
							var a =	document.getElementById("estado").value= "activo";
							$scope.estado = a;	
						}else{
							var a =	document.getElementById("estado").value= "inactivo";
							$scope.estado = a;
						}
					}
							
				} else {
					alert(data.mensaje);
					$scope.estado = "";
				}
			}).error(function(data, status, headers, config) {
				alert('error::' + data.mensaje);
			});
		}
	
	
	$scope.eliminar = function() {
		
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
				alert(data.mensaje);
				$scope.limpiarCampos();

			} else {
				alert(data.mensaje);
			}
		}).error(function(data, status, headers, config) {
			alert('error::' + data.mensaje);
		});
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
				
			} else {
				alert(data.mensaje);
			}

		}).error(function(data, status, headers, config) {

			alert('error::' + data.mensaje);
		});

	}
	
	
	
	
	$scope.listarCargos = function() {

		$http({
			url : 'rest/cargo/listarCargos',
			method : "GET",
			headers : {
				"Authorization" : $sessionStorage.token
			}
		}).success(function(data, status, headers, config) {
			if (data.codigo == '00') {
				$scope.cargos = data.obj;
			} else {
				alert(data.mensaje);
			}

		}).error(function(data, status, headers, config) {

			alert('error::' + data.mensaje);
		});

	}
	
	
	$scope.listarSedes();
	$scope.listarCargos();
	
	$scope.limpiarCampos = function() {
		$scope.cedula = '';
		$scope.cargo = '';
		$scope.nombre = '';
		$scope.telefono = '';
		$scope.fechaIngreso = '';
		$scope.tipoContrato = '';
		$scope.eps = '';
		$scope.salario = '';
		$scope.sede = '';
		$scope.usuarioL = '';
		$scope.contrasenia = '';
		$scope.contrasenia.contraseniaVerificar= '';
		$scope.estado = '';

	}
	
});


