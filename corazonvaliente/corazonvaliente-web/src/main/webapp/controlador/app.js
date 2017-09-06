var app = angular.module("miApp", [ "ngRoute", "ngStorage" ]);
app.config(function($routeProvider) {
	$routeProvider.when("/", {
		controller : "menuController",
		controllerAs : "m1",
	}).when("/listarUsuarios", {
		controller : "controladorListarUsuarios",
		templateUrl : "paginas/listarUsuarios.html"
	}).when("/listarFuncionarios", {
		controller : "controladorListarFuncionarios",
		templateUrl : "paginas/listarFuncionarios.html"
	}).when("/funcionariosEjecutivos", {
		controller : "controladoFuncionariosEjecutivos",
		templateUrl : "paginas/funcionariosEjecutivos.html"
	}).when("/usuarioEntrevista", {
		controller : "controladorUsuario",
		templateUrl : "paginas/usuarioEntrevista.html"
	}).when("/aportes", {
		controller : "controladorAportes",
		templateUrl : "paginas/aportes.html"
	}).when("/cargos", {
		controller : "controladorCargos",
		templateUrl : "paginas/cargos.html"
	}).when("/egresos", {
		controller : "controladorEgresos",
		templateUrl : "paginas/egresos.html"
	}).when("/fichas", {
		controller : "controladorFichas",
		templateUrl : "paginas/fichas.html"
	}).when("/funcionarios", {
		controller : "controladorFuncionarios",
		templateUrl : "paginas/funcionarios.html"
	}).when("/ingresos", {
		controller : "controladorIngresos",
		templateUrl : "paginas/ingresos.html"
	}).when("/sedes", {
		controller : "controladorSedes",
		templateUrl : "paginas/sedes.html"
	}).when("/seguimientos", {
		controller : "controladorSeguimientos",
		templateUrl : "paginas/seguimientos.html"
	}).when("/traslados", {
		controller : "controladorTraslados",
		templateUrl : "paginas/traslados.html"
	}).when("/trasladosFuncionarios", {
		controller : "controladorTrasladosFuncionarios",
		templateUrl : "paginas/trasladosFuncionarios.html"
	}).when("/buscarInactivos", {
		controller : "controladorBuscarInactivos",
		templateUrl : "paginas/buscarInactivos.html"
	}).when("/diferencia", {
		controller : "controladorDiferencia",
		templateUrl : "paginas/diferencia.html"
	}).when("/elegirSede", {
		controller : "controladorElegirSedes",
		templateUrl : "paginas/elegirSede.html"
	});

});
