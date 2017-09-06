app.controller("controladorUsuario", function($scope, $http, $window,
		$sessionStorage, $filter)
		{
		
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

	$scope.imagen='';
	$scope.bytes=[];
	$scope.foto=[];
	$scope.fotosel=[];
	$scope.ciudades=[];
	$scope.funcionarioB;
	
	$scope.cedula=0;
	$scope.nombre='';
	$scope.alias='';
	$scope.genero='';
	$scope.ciudadNacimiento='';
	$scope.fechaNacimiento='';
	$scope.fechaIngreso='';
	$scope.adiccion='';
	$scope.tiempoAdiccion='';
	$scope.accesoAAdiccion='';
	$scope.antecedentes='';
	$scope.problemaJudicial='';
	$scope.profesion='';
	$scope.estadoCivil='';
	$scope.numeroHijos='';
	$scope.estudios='';
	$scope.instituciones='';
	$scope.tipoIngreso='';
	$scope.motivoRetiro='';
	$scope.descripcionIngreso='';
	$scope.descripcionGeneral='';
	$scope.modalidad='';
	$scope.valor='';
	$scope.etapa='';
	$scope.acudiente='';
	$scope.celularAcudiente='';
	
	
	$scope.campos=function () {
		alert($scope.cedula);
		alert($scope.funcionarioB);
		alert($scope.bytes);
		alert($scope.nombre);
		alert($scope.alias);
		alert($scope.genero);
		alert($scope.ciudadNacimiento);
		alert($scope.fechaNacimiento);
		alert($scope.fechaIngreso);
		alert($scope.adiccion);
		alert($scope.tiempoAdiccion);
		alert($scope.accesoAAdiccion);
		alert($scope.antecedentes);
		alert($scope.problemaJudicial);
		alert($scope.profesion);
		alert($scope.estadoCivil);
		alert($scope.numeroHijos);
		alert($scope.estudios);
		alert($scope.instituciones);
		alert($scope.tipoIngreso);
		alert($scope.motivoRetiro);
		alert($scope.descripcionIngreso);
		alert($scope.descripcionGeneral);
		alert($scope.modalidad);
		alert($scope.valor);
		alert($scope.etapa);
		alert($scope.acudiente);
		alert($scope.celularAcudiente);
}

	/**
	 * crea una entrevista para un usuario
	 */
	$scope.registrarEntrevista = function() {
		 	if($scope.cedula=='' || $scope.cedula==0 ){
				alert("Debe ingresar una cedula de usuario");
		 	}else if(document.getElementById ("ciudades").selectedIndex == "0"){
		 		alert("Debe seleccionar una ciudad");
		 	}else{
		 		$scope.convertirImg();
		 		window.setTimeout(function() {
					var dato3 = ({
						cedula : $scope.cedula,
						funcionario : null,
						imagen: $scope.bytes,
						nombre: $scope.nombre,
						alias: $scope.alias,
						genero: $scope.genero,
						ciudadNacimiento: $scope.ciudadNacimiento,
						fechaNacimiento: $scope.fechaNacimiento,
						fechaIngreso: $scope.fechaIngreso,
						adiccion: $scope.adiccion,
						tiempoAdiccion: $scope.tiempoAdiccion,
						accesoAAdiccion: $scope.accesoAAdiccion,
						antecedentes: $scope.antecedentes,
						problemaJudicial: $scope.problemaJudicial,
						profesion: $scope.profesion,
						estadoCivil: $scope.estadoCivil,
						numeroHijos: $scope.numeroHijos,
						estudios: $scope.estudios,
						instituciones: $scope.instituciones,
						tipoIngreso: $scope.tipoIngreso,
						motivoRetiro: $scope.motivoRetiro,
						descripcionIngreso: $scope.descripcionIngreso,
						descripcionGeneral: $scope.descripcionGeneral,
						modalidad: $scope.modalidad,
						valor: $scope.valor,
						etapa: $scope.etapa,
						acudiente: $scope.acudiente,
					    celularAcudiente: $scope.celularAcudiente,
					    sede: null
					});
					
					var obj=({
						usuario:dato3,
						user:$sessionStorage.user,
						sede: $sessionStorage.sede
					});
					
					$http({
						url : 'rest/usuario/crearUsuario',
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
			}, 1800);		
		}
	}
	
	/**
	 * busca un usuario
	 */
	$scope.buscarUsuario=function () {
		if($scope.cedula==''){
			alert("Debe ingresar una cedula de usuario");
		}else{
		var dato2 =({
			cedula : $scope.cedula,
			sede: $sessionStorage.sede
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
				
				for(var i=0; i<$scope.ciudades.length;i++){
					if(data.obj.ciudadNacimiento.nombre==$scope.ciudades[i].nombre){
						$scope.ciudadNacimiento=$scope.ciudades[i];									
					}
				}
				
				
				if(data.obj.fechaIngreso!=null){
					var date = new Date(data.obj.fechaIngreso);
					var year = date.getFullYear();
					var month = (date.getMonth());
					var day = (date.getDate()+1);
					$scope.value = new Date(year, month, day);
					$scope.fechaIngreso= $scope.value;
						
				}
				
				if(data.obj.fechaNacimiento!=null){
					var dates = new Date(data.obj.fechaNacimiento);
					var years = dates.getFullYear();
					var months = (dates.getMonth());
					var days = (dates.getDate()+1);
					$scope.values = new Date(years, months, days);
					$scope.fechaNacimiento= $scope.values;
				}
				
				$scope.nombre= data.obj.nombre;
				$scope.alias= data.obj.alias;
				$scope.adiccion= data.obj.adiccion;
				$scope.tiempoAdiccion= data.obj.tiempoAdiccion;
				$scope.accesoAAdiccion= data.obj.accesoAAdiccion;
				$scope.antecedentes= data.obj.antecedentes;
				$scope.problemaJudicial= data.obj.problemaJudicial;
				$scope.profesion= data.obj.profesion;
				
				
				var a =	document.getElementById("estadoCivil").value=data.obj.estadoCivil;
				$scope.estadoCivil = a;
				

				var b =	document.getElementById("genero").value=data.obj.genero;
				$scope.genero = b;
				
				var c =	document.getElementById("modalidad").value=data.obj.modalidad;
				$scope.modalidad = c;
				
				var d =	document.getElementById("etapa").value=data.obj.etapa;
				$scope.etapa = d;
				
				
				$scope.numeroHijos= data.obj.numeroHijos;
				$scope.estudios= data.obj.estudios;
				$scope.instituciones= data.obj.instituciones;
				$scope.tipoIngreso= data.obj.tipoIngreso;
				$scope.motivoRetiro= data.obj.motivoRetiro;
				$scope.descripcionIngreso= data.obj.descripcionIngreso;
				$scope.descripcionGeneral= data.obj.descripcionGeneral;
				$scope.valor= data.obj.valor;
				$scope.acudiente= data.obj.acudiente;
				$scope.celularAcudiente= data.obj.celularAcudiente;
				
				$scope.fotosel=[];
				$scope.foto= data.obj.imagen;
				
			} else {
				alert(data.mensaje);
			}
		}).error(function(data, status, headers, config) {
			alert('error::' + data.mensaje);
		});
	}
}
	
	/**
	 * edita una entrevista para un usuario
	 */
	$scope.editarEntrevista = function() {
		if($scope.cedula==''){
			alert("Debe ingresar una cedula de usuario");
	 	}else if(document.getElementById ("ciudades").selectedIndex == "0"){
	 		alert("Debe seleccionar una ciudad");
	 	}else{
	 		$scope.convertirImg();
	 		window.setTimeout(function() {
		 			var dato3 = ({
						cedula : $scope.cedula,
						funcionario : null,
						imagen: $scope.bytes,
						nombre: $scope.nombre,
						alias: $scope.alias,
						genero: $scope.genero,
						ciudadNacimiento: $scope.ciudadNacimiento,
						fechaNacimiento: $scope.fechaNacimiento,
						fechaIngreso: $scope.fechaIngreso,
						adiccion: $scope.adiccion,
						tiempoAdiccion: $scope.tiempoAdiccion,
						accesoAAdiccion: $scope.accesoAAdiccion,
						antecedentes: $scope.antecedentes,
						problemaJudicial: $scope.problemaJudicial,
						profesion: $scope.profesion,
						estadoCivil: $scope.estadoCivil,
						numeroHijos: $scope.numeroHijos,
						estudios: $scope.estudios,
						instituciones: $scope.instituciones,
						tipoIngreso: $scope.tipoIngreso,
						motivoRetiro: $scope.motivoRetiro,
						descripcionIngreso: $scope.descripcionIngreso,
						descripcionGeneral: $scope.descripcionGeneral,
						modalidad: $scope.modalidad,
						valor: $scope.valor,
						etapa: $scope.etapa,
						acudiente: $scope.acudiente,
					    celularAcudiente: $scope.celularAcudiente,
					    sede: $sessionStorage.sede
					});
		 			
		 			var obj=({
						usuario:dato3,
						user:$sessionStorage.user,
						sede: $sessionStorage.sede
					});
		 			
					$http({
						url : 'rest/usuario/editarUsuario',
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
		}, 1800);
	}
}
		
	/**
	 * elimina la imagen seleccionada
	 */
	$scope.cambiarImg=function () {
		$scope.foto=[];
	}
	
	/**
	 * convierte una imagen a bytes el tamaño de la imgen debe de ser de 150px maximo
	 */
	$scope.convertirImg=function () {
		$scope.imagen= document.getElementById("foto");
		if($scope.imagen != null){
			var canvas = document.createElement("canvas");
		    canvas.width = document.getElementById("foto").width;
		    canvas.height = document.getElementById("foto").height;
		    var ctx = canvas.getContext("2d");
		    ctx.drawImage($scope.imagen, 0, 0);
		    var dataURL = canvas.toDataURL("image/png");
		    $scope.bytes= dataURL.replace(/^data:image\/(png|jpg);base64,/, "");
		}else{
			$scope.imagen= document.getElementById("fotoB");
			if($scope.imagen != null){
				var canvas = document.createElement("canvas");
			    canvas.width = document.getElementById("fotoB").width;
			    canvas.height = document.getElementById("fotoB").height;
			    var ctx = canvas.getContext("2d");
			    ctx.drawImage($scope.imagen, 0, 0);
			    var dataURL = canvas.toDataURL("image/png");
			    $scope.bytes= dataURL.replace(/^data:image\/(png|jpg);base64,/, "");
				}		
			}
	    }

	/**
	 * busca un funcionario para la entrevista
	 */
	$scope.buscarFuncionario=function () {
		var dato2 = $.param({
			cedula : $scope.funcionarioSede
		});
		$http({
			url : 'rest/funcionariosede/buscarfuncionario',
			method : "POST",
			data : dato2,
			headers : {
				"Content-Type" : "application/x-www-form-urlencoded",
				"Authorization" : $sessionStorage.token
			}
		}).success(function(data, status, headers, config) {
			if (data.codigo == '00') {
				$scope.funcionarioB=data.obj;
			}
		}).error(function(data, status, headers, config) {
			alert('error::' + data.mensaje);
		});
	}
	
	/**
	 * lista las cuidades de la base de datos
	 */
	$scope.listarCiudades = function() {
		$http({
			url : 'rest/ciudad/listarCiudades',
			method : "GET",
			headers : {
				"Authorization" : $sessionStorage.token
			}
		}).success(function(data, status, headers, config) {
			if (data.codigo == '00') {
				$scope.ciudades = data.obj;
			} 
		}).error(function(data, status, headers, config) {
			alert('error::' + data.mensaje);
		});
	}
		
	/**
	 * limpia los campos de la ventana
	 */
	$scope.limpiarCampos = function() {
		
		$scope.imagen='';
		$scope.bytes=[];
		$scope.foto=[];
		$scope.fotosel=[];
		$scope.funcionarioB;
		$scope.cedula=0;
		$scope.nombre='';
		$scope.alias='';
		$scope.genero='';
		$scope.ciudadNacimiento='';
		$scope.fechaNacimiento='';
		$scope.fechaIngreso='';
		$scope.adiccion='';
		$scope.tiempoAdiccion='';
		$scope.accesoAAdiccion='';
		$scope.antecedentes='';
		$scope.problemaJudicial='';
		$scope.profesion='';
		$scope.estadoCivil='';
		$scope.numeroHijos='';
		$scope.estudios='';
		$scope.instituciones='';
		$scope.tipoIngreso='';
		$scope.motivoRetiro='';
		$scope.descripcionIngreso='';
		$scope.descripcionGeneral='';
		$scope.modalidad='';
		$scope.valor='';
		$scope.etapa='';
		$scope.acudiente='';
		$scope.celularAcudiente='';
		
	}
	
	$scope.listarCiudades();
	
	
}).directive('appFilereader', function(
	    $q
) {
  
  var slice = Array.prototype.slice;

  return {
    restrict: 'A',
    require: '?ngModel',
    link: function(scope, element, attrs, ngModel) {
      if (!ngModel) return;

      ngModel.$render = function() {}

      element.bind('change', function(e) {
        var element = e.target;
        if(!element.value) return;

        element.disabled = true;
        $q.all(slice.call(element.files, 0).map(readFile))
          .then(function(values) {
            if (element.multiple) ngModel.$setViewValue(values);
            else ngModel.$setViewValue(values.length ? values[0] : null);
            element.value = null;
            element.disabled = false;
          });

        function readFile(file) {
          var deferred = $q.defer();
          var reader = new FileReader()
          reader.onload = function(e) {
            deferred.resolve(e.target.result);
          }
          reader.onerror = function(e) {
            deferred.reject(e);
          }
          reader.readAsDataURL(file);
          return deferred.promise;
        }
      }); 
    }
  };
});
