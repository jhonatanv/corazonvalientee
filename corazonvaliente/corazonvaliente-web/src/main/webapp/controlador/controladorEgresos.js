app.controller("controladorEgresos", function($scope, $http, $window, $sessionStorage, $filter) {

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

	$scope.egresos = [];
	$scope.codigo = '';
	$scope.numeroFactura = '';
	$scope.fechaEgreso = '';
	$scope.cuenta = '';
	$scope.nit = '';
	$scope.concepto = '';
	$scope.valor = '';
	$scope.ano = 2017;
	$scope.mes = 06;
	$scope.total= 0;
	
	
	
	/**
	 * crea una egreso
	 */
	
	$scope.crear = function() {
		if($scope.numeroFactura != '' && $scope.fechaEgreso != '' && $scope.cuenta != '' && $scope.nit != '' && $scope.concepto != '' && $scope.valor != ''){
							var dato = ({
								funcionario: null,
								sede: null,							
							numeroFactura: $scope.numeroFactura,
							fechaEgreso : $scope.fechaEgreso,
							cuenta : $scope.cuenta,
							nit : $scope.nit,
							concepto : $scope.concepto,
							valor : $scope.valor
				});
							
							var obj=({
						 		egreso:dato,
						 		sede: $sessionStorage.sede,
						 		funcionario : $sessionStorage.user

						 	});	
				$http({
					url : 'rest/egreso/crear',
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
	 * editar una egreso
	 */
	$scope.editar = function() {
		
		if($scope.codigo !=''){
		
			if($scope.numeroFactura != '' && $scope.fechaEgreso != '' && $scope.cuenta != '' && $scope.nit != '' && $scope.concepto != '' && $scope.valor != ''){
							var dato = ({
								
							    codigo : $scope.codigo,
								funcionario: null,
								sede: null,
								numeroFactura: $scope.numeroFactura,
								fechaEgreso : $scope.fechaEgreso,
								cuenta : $scope.cuenta,
								nit : $scope.nit,
								concepto : $scope.concepto,
								valor : $scope.valor
				});
							var obj=({
						 		egreso:dato,
						 		sede: $sessionStorage.sede,
						 		funcionario : $sessionStorage.user

						 	});	
				$http({
					url : 'rest/egreso/editar',
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
				url : 'rest/egreso/buscar',
				method : "POST",
				data : dato2,
				headers : {
					"Content-Type" : "application/x-www-form-urlencoded",
					"Authorization" : $sessionStorage.token
				}
			}).success(function(data, status, headers, config) {
				if (data.codigo == '00') {
					
						var date = new Date(data.obj.fechaEgreso);
						var year = date.getFullYear();
						var month = (date.getMonth());
						var day = (date.getDate()+1);
						$scope.fechaEgreso = new Date(year, month, day);					
					$scope.codigo = data.obj.codigo;
					$scope.numeroFactura = data.obj.numeroFactura;
					$scope.cuenta = data.obj.cuenta;
					$scope.nit = data.obj.nit;
					$scope.concepto = data.obj.concepto;
					$scope.valor = data.obj.valor;

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
			url : 'rest/egreso/eliminar',
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
	 * lista las egresos
	 */
	$scope.listarEgresos = function() {
		if($scope.ano != '' && $scope.mes != ''){
		if($scope.ano < 2061 && $scope.mes <13){

		
			var dato2 = $.param({
				ano : $scope.ano,
				mes :  $scope.mes,
				sede : $sessionStorage.sede.nombre
			});
			$http({
				url : 'rest/egreso/listarEgresos',
				method : "POST",
				data : dato2,
				headers : {
					"Content-Type" : "application/x-www-form-urlencoded",
					"Authorization" : $sessionStorage.token
				}	
		}).success(function(data, status, headers, config) {
			if (data.codigo == '00') {
				$scope.egresos = data.obj;
				$scope.calcularTotal();
				
			}else{
				alert("No hay registros en las fechas indicadas");
				$scope.egresos = null;
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
		for(var i= 0;i<$scope.egresos.length;i++){
			$scope.total=(parseInt($scope.total)+parseInt($scope.egresos[i].valor));	
		}
	}
	
	
	
	$scope.limpiarCampos = function() {
		$scope.codigo = '';
		$scope.numeroFactura = '';
		$scope.fechaEgreso = '';
		$scope.cuenta = '';
		$scope.nit = '';
		$scope.concepto = '';
		$scope.valor = '';
	}
	

});


