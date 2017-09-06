app.controller("loginController", function($scope, $http, $window,$sessionStorage) {
	
	$sessionStorage.rutas = [];
	$scope.ruta="rutaas";	
	
	
	/**
	 * user del usuario
	 */
	$scope.user = '';
	 
	/**
	 * contrasenia del usuario
	 */
	$scope.pass = '';
	
	
	$scope.accesos = function() {
		var xsrf = $.param({
			usuario : $sessionStorage.user
		});
		$http({
			url : 'rest/login/accesos',
			method : "POST",
			data : xsrf,
			headers : {
				"Content-Type" : "application/x-www-form-urlencoded",
				"Authorization" : $sessionStorage.token
			}
		}).success(function(data, status, headers, config) {
			if (data.codigo == '00') {
				$sessionStorage.rutas = data.obj;
				$scope.rutas = $sessionStorage.rutas;
			} else {
				alert("error al cargar accesos");
			}
		}).error(function(data, status, headers, config) {
			alert('error::' + data.mensaje);
		});
	};

	/**
	 * funcion para consumir servicio de verificar usuario
	 */
	$scope.login = function() {
		if($scope.user != "" || $scope.pass != ""){
			
		
		var xsrf = $.param({
			usuario : $scope.user,
			contrasenia : $scope.pass
		});
		$http({
			url : 'rest/login/verificar',
			method : "POST",
			data : xsrf,
			headers : {
				"Content-Type" : "application/x-www-form-urlencoded"
			}
		}).success(function(data, status, headers, config) {
			if (data.codigo == '00') {
				if(data.mensaje=='0'){
					alert("Usted no se encuentra activo");
				}else if(data.mensaje=='1'){
					$sessionStorage.token=data.obj.token;
					$sessionStorage.user=data.obj.user;
					$scope.accesos();
					
					window.setTimeout(function() {
						window.location.href = '/corazonvaliente-web/paginas/elegirSede.html#/';
					}, 1800);
				}else if(data.mensaje=='2'){
					$sessionStorage.token=data.obj.token;
					$sessionStorage.user=data.obj.user;
					$sessionStorage.sede=data.obj.sede;
					$scope.accesos();
					
					window.setTimeout(function() {
						window.location.href = '/corazonvaliente-web/menu.html#/';
					}, 1800);	
				}else{
					alert("Usuario o contraseña incorrectos");
				}				
			} else {
				alert(data.mensaje);
			}
		}).error(function(data, status, headers, config) {
			alert('error::' + data.mensaje);
		});
		}else{
			alert("Debe diligenciar todos los campos");
		}
	};
	
	
	
	
	
	
	
	
	
	
	
	
	

		
});

