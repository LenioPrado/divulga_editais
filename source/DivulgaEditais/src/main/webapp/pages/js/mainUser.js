window.onload = function() {
	$('#sidebarDiv').load("sidebar.html", function(){
		// Sidebar Load Event
		assignEvents();
		isLogged();
	});	
	
	$('#header').load("header.html", function(){
		// Header Load Event
		loadConfirmModal(null, logout, null, 'Saída', 'Confirma saída do sistema de editais?');
	});	
	
	$('#footer').load("footer.html", function(){
		// Footer Load Event
	});	
};

function assignEvents(){

	$('#userRoleLink').click(function(){listNotices();});
	
	$('#listNoticesLink').click(function(){listNotices();});
	$('#listCategoriesLink').click(function(){listCategories();});
	$('#listModalitiesLink').click(function(){listModalities();});
	$('#listCompanyTypesLink').click(function(){listCompanyTypes();});

	$('#registerNoticeLink').click(function(){loadNotice(registerNotice);});
	$('#registerCategoriesLink').click(function(){loadCategory(registerCategory);});	
	$('#registerModalityLink').click(function(){loadModality(registerModality);});
	$('#registerCompanyTypeLink').click(function(){loadCompanyType(registerCompanyType);});	

	$('#subscribeToNoticesLink').click(function(){listNoticesToSubscribe();});
	$('#subscribeToCategoriesLink').click(function(){listCategoriesToSubscribe();});		
	$('#subscribedNoticesLink').click(function(){listNoticesRegisteredByUser();});
	$('#subscribedCategoriesLink').click(function(){listCategoriesRegisteredByUser();});
}