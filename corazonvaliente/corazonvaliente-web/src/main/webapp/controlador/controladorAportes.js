app.controller("controladorAportes", function($scope, $http, $window,
		$sessionStorage, $filter) {

$scope.accesos = false;
	
	$scope.filtro = function() {
		if($sessionStorage.rutas){
			if($sessionStorage.rutas.length>0){
		for (var i = 0; i < $sessionStorage.rutas.length; i++) {
			if (window.location.hash == $sessionStorage.rutas[i].url) {
				$scope.accesos = true;
			}
		}
		if ($scope.accesos == false) {
				window.location.href = '/corazonvaliente-web/menu.html#/';
		}
		}else{
			window.location.href = '/corazonvaliente-web/index.html#/';
		}
	}else{
		window.location.href = '/corazonvaliente-web/index.html#/';
	}
}
	
$scope.filtro();

	
	$scope.aportes = [];
	$scope.cedula = '';
	$scope.descripcion = '';
	$scope.valor = '';
	$scope.usuario = '';
	$scope.total = 0;
	$scope.deuda = 0;

	/**
	 * crea un aporte
	 */
	$scope.crearAporte = function() {
		if ($scope.cedula == '' || $scope.descripcion == ''
				|| $scope.valor == '') {
			alert("Todos los campos son obligatorios");
		} else {
			$scope.buscarUsuario();
			window.setTimeout(function() {
				if ($scope.usuario != '') { 
					var dato3 = ({
						codigo : 0,
						sede : null,
						usuario : $scope.usuario,
						fechaAporte : null,
						descripcion : $scope.descripcion,
						valorCancelado : $scope.valor
					});
					var obj=({
						aporte:dato3,
						sede:$sessionStorage.sede
					});
					
					$http({
						url : 'rest/aporte/crear',
						method : "POST",
						data : obj,
						headers : {
							"Content-Type" : "application/json",
							"Authorization" : $sessionStorage.token
						}
					}).success(function(data, status, headers, config) {
						if (data.codigo == '00') {
							$scope.listarAportes();
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
		var dato2 = ({
			cedula : $scope.cedula,
			sede:$sessionStorage.sede
		});
		$http({
			url : 'rest/usuario/buscarUsuario',
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

	/**
	 * lista los aportes de un usuario
	 */
	$scope.listarAportes = function() {
		

			var dato2 =({
				cedula : $scope.cedula,
				sede:$sessionStorage.sede
			});
			$http({
				url : 'rest/aporte/listarAportes',
				method : "POST",
				data : dato2,
				headers : {
					"Content-Type" : "application/json",
					"Authorization" : $sessionStorage.token
				}
			}).success(function(data, status, headers, config) {
				if (data.codigo == '00') {
					$scope.aportes = data.obj;
					$scope.calcularTotalDeuda();
				}
			}).error(function(data, status, headers, config) {
				alert('error::' + data.mensaje);
			});
		
	}

	/**
	 * lista los aportes de un usuario en una sede
	 */
	$scope.listarAportesSede = function() {
		$scope.total = 0;
		if ($scope.cedula == '') {
			alert("Debe ingresar una cedula");
		}else{
				var dato2 =({
					cedula : $scope.cedula,
					sede:$sessionStorage.sede
				});
				$http({
					url : 'rest/aporte/listarAportes',
					method : "POST",
					data : dato2,
					headers : {
						"Content-Type" : "application/json",
						"Authorization" : $sessionStorage.token
					}
				}).success(function(data, status, headers, config) {
					if (data.codigo == '00') {
						$scope.aportes = data.obj;
						$scope.calcularTotalDeuda();
					} else {
						alert(data.mensaje);
						$scope.aportes = [];
						$scope.total = 0;


					}
				}).error(function(data, status, headers, config) {
					alert('error::' + data.mensaje);
				});
			}
		}
	/**
	 * calcula el valor del total y de la deuda de un usuario
	 */
	$scope.calcularTotalDeuda=function(){
		for(var i= 0;i<$scope.aportes.length;i++){
			$scope.total=(parseInt($scope.total)+parseInt($scope.aportes[i].valorCancelado));	
		}
		$scope.deuda=parseInt($scope.aportes[0].usuario.valor)-parseInt($scope.total);
		if($scope.deuda<0){
			$scope.deuda=0;
		}
	}
	
	
	/**
	 * limpia los campos de la ventana
	 */
	$scope.limpiarCampos = function() {
		$scope.descripcion = '';
		$scope.valor = '';
	}

});
