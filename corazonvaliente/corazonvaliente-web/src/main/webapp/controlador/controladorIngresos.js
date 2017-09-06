app.controller("controladorIngresos", function($scope, $http, $window, $sessionStorage, $filter) {

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
	$scope.fechaIngreso = '';
	$scope.valor = '';
	$scope.codigo = '';
	$scope.descripcion = '';
	$scope.ano = 2017;
	$scope.mes = 06;
	$scope.total= 0;
	
	
	/**
	 * crea una ingreso
	 */
	
	$scope.crear = function() {
		if($scope.fechaIngreso !='' && $scope.valor!='' && $scope.telefono!='' && $scope.descripcion != ''){
							var dato = ({
								funcionario: null,
								sede: null,
								fechaIngreso: $scope.fechaIngreso,
								valor: $scope.valor,
								descripcion: $scope.descripcion
				});
							
							var obj=({
						 		ingreso:dato,
						 		sede: $sessionStorage.sede,
						 		funcionario : $sessionStorage.user

						 	});	
				$http({
					url : 'rest/ingreso/crear',
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
	 * editar una ingreso
	 */
	$scope.editar = function() {
		
		if($scope.codigo !=''){
		
		if($scope.fechaIngreso !='' && $scope.valor!='' && $scope.descripcion!=''){
							var dato = ({
								
							    codigo : $scope.codigo,
								funcionario: null,
								sede: null,
								fechaIngreso: $scope.fechaIngreso,
								valor: $scope.valor,
								descripcion: $scope.descripcion
				});
							
							var obj=({
						 		ingreso:dato,
						 		sede: $sessionStorage.sede,
						 		funcionario : $sessionStorage.user

						 	});	
				$http({
					url : 'rest/ingreso/editar',
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
					} else {
						alert(data.mensaje);
					}
				}).error(function(data, status, headers, config) {
					alert('error::' + data.mensaje);
				});
		}else{
			alert('favor llenar todo los campos correctamente');

		}
		}else{
			alert('Debe seleccionar un registro de la tabla');

		}
			
	}
	
	
	
	
	$scope.buscar = function(codigo) {
		
			var dato2 = $.param({
				codigo : codigo
			});
			$http({
				url : 'rest/ingreso/buscar',
				method : "POST",
				data : dato2,
				headers : {
					"Content-Type" : "application/x-www-form-urlencoded",
					"Authorization" : $sessionStorage.token
						
				}
			}).success(function(data, status, headers, config) {
				if (data.codigo == '00') {
					
						var date = new Date(data.obj.fechaIngreso);
						var year = date.getFullYear();
						var month = (date.getMonth());
						var day = (date.getDate()+1);
						$scope.fechaIngreso = new Date(year, month, day);
							
					$scope.codigo = data.obj.codigo;
					$scope.valor = data.obj.valor;
					$scope.descripcion = data.obj.descripcion;

				} else {
					alert(data.mensaje);
				}
			}).error(function(data, status, headers, config) {
				alert('error::' + data.mensaje);
			});
		}
	
	
	$scope.eliminar = function() {
		if($scope.codigo !=''){
		var dato2 = $.param({
			codigo : $scope.codigo
		});
		$http({
			url : 'rest/ingreso/eliminar',
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
	}else{
		alert('Debe seleccionar un registro de la tabla');

	}
	}
	
	
	
	/**
	 * lista las ingresos
	 */
	$scope.listarIngresos = function() {
		if($scope.ano != '' && $scope.mes != ''){
		if($scope.ano < 2061 && $scope.mes <13){

		
			var dato2 = $.param({
				ano : $scope.ano,
				mes :  $scope.mes,
				sede : $sessionStorage.sede.nombre

			});
			$http({
				url : 'rest/ingreso/listarIngresos',
				method : "POST",
				data : dato2,
				headers : {
					"Content-Type" : "application/x-www-form-urlencoded",
					"Authorization" : $sessionStorage.token
				}	
		}).success(function(data, status, headers, config) {
			if (data.codigo == '00') {
				$scope.ingresos = data.obj;
				$scope.calcularTotal();
				
			}else{
				alert("No hay registros en las fechas indicadas");
				$scope.ingresos = null;
				$scope.total= 0;

			}

		}).error(function(data, status, headers, config) {

			alert('error::' + data.mensaje);
		});
		}else{
			alert('mes o anho incorrecto, se pasa del rango requerido');


		}
		}else{
			alert('favor llenar todo los campos correctamente');

		}
			
		

	}
	
	
	
	$scope.calcularTotal=function(){
		$scope.total= 0;
		for(var i= 0;i<$scope.ingresos.length;i++){
			$scope.total=(parseInt($scope.total)+parseInt($scope.ingresos[i].valor));	
		}
	}
	
	
	$scope.limpiarCampos = function() {
		$scope.fechaIngreso = '';
		$scope.valor = '';
		$scope.descripcion = '';
		$scope.codigo = '';
	}
	

});


