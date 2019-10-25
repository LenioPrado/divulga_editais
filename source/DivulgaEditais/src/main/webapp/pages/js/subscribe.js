function beforeProcessNotice(notice){
	notice['closingDate'] = convertFormDateToSqlDate(notice['closingDate']);
	notice['publishingDate'] = convertFormDateToSqlDate(notice['publishingDate']);
	notice['tradingDate'] = convertFormDateToSqlDate(notice['tradingDate']);
	delete notice['edital'];
}

function beforeProcessCategory(){
	// Alterar a entidade, se necessário.
}

function listNoticesToSubscribe(){
	var urlToLoad = "subscribeNotices.html";
	var urlToGetData = "notice/listNotSubscribed";
	var urlToSubscribe = "userNotice/subscribe/";

	var modal = function (){
		loadConfirmModal(urlToSubscribe, processEntity, beforeProcessNotice, 'Inscrição', 'Confirma inscrição no edital?');
	}
	
	listNoticesToSubscribeAndRegistered(urlToLoad, urlToGetData, modal, urlToSubscribe, subscribeButton);
}

function listNoticesRegisteredByUser(){
	var urlToLoad = "unsubscribeNotices.html";
	var urlToGetData = "notice/listSubscribed";
	var urlToUnsubscribe = "userNotice/unsubscribe/";
	
	var modal = function (){
		loadConfirmModal(urlToUnsubscribe, processEntity, beforeProcessNotice, 'Remover Inscrição', 'Confirma a remoção da inscrição no edital?');
	}
	
	listNoticesToSubscribeAndRegistered(urlToLoad, urlToGetData, modal, urlToUnsubscribe, unsubscribeButton);
}

function listCategoriesToSubscribe(){
	var urlToLoad = "subscribeCategories.html";
	var urlToGetData = "category/listNotSubscribed";
	var urlToSubscribe = "userCategory/subscribe/";	
	
	var modal = function (){
		loadConfirmModal(urlToSubscribe, processEntity, beforeProcessCategory, 'Inscrição', 'Confirma inscrição na categoria?');
	}
	
	listCategoriesToSubscribeAndRegistered(urlToLoad, urlToGetData, modal, urlToSubscribe, subscribeButton);
}

function listCategoriesRegisteredByUser(){
	var urlToLoad = "unsubscribeCategories.html";
	var urlToGetData = "category/listSubscribed";
	var urlToUnsubscribe = "userCategory/unsubscribe/";	
	
	var modal = function (){
		loadConfirmModal(urlToUnsubscribe, processEntity, beforeProcessCategory, 'Remover Inscrição', 'Confirma a remoção da inscrição na categoria?');
	}
	
	listCategoriesToSubscribeAndRegistered(urlToLoad, urlToGetData, modal, urlToUnsubscribe, unsubscribeButton);
}

function listNoticesToSubscribeAndRegistered(urlToLoad, urlToGetData, modalToLoad, urlToExecuteAction, actionButton){
	
	var fieldsArray = ['user.fantasyName', ['modality.acronyms','modality.description'], 
		'companyType.description','number', 'object', 
		'publishingDate', 'tradingDate', 'closingDate','edital', 'delete'];
	
	loadPage(urlToLoad, function(){
		modalToLoad();
		genericList(urlToGetData, "notice", "noticeId", fieldsArray, actionButton, null, eachNoticeRowListing);
	});	
}

function listCategoriesToSubscribeAndRegistered(urlToLoad, urlToGetData, modalToLoad, urlToExecuteAction, actionButton){
	loadPage(urlToLoad, function(){
		modalToLoad();
		genericList(urlToGetData, "category", "categoryId", ['description', 'delete'], actionButton);
	});		
}

function subscribeButton(data, dataTableId, entityId){
	data['delete'] = '<input type="image" entity-data="'+data[entityId]+'" data-toggle="modal" data-target="#confirmAction" src="assets/css/images/subscribe.png"/>';
}

function unsubscribeButton(data, dataTableId, entityId){
	data['delete'] = '<input type="image" entity-data="'+data[entityId]+'" data-toggle="modal" data-target="#confirmAction" src="assets/css/images/bt-deletar.png"/>';
}

function processEntity(urlToGetData, entity, entityId, beforeProcessCallback){	
	
	delete entity['edit'];
	delete entity['delete'];
	
	if(beforeProcessCallback){
		beforeProcessCallback(entity);
	}
	
	$.ajax({
	   type: "post",
	   dataType: "json",
	   url: getServerUrl() + urlToGetData,
	   data: JSON.stringify( entity ),
	   processData: false,
	   contentType: 'application/json',
	   success: function(data){
		   showMessage(data['message'],'success');
		   $('#tr'+entityId).hide();
		   window.scrollTo(0, 0);
	   },
	   error: function(jqXHR, exception) {
			var msg = jqXHR.responseText;
			showMessage(msg,'error');
			window.scrollTo(0, 0);
	   }
	});	
}