function listNotices(){
	
	var urlToLoad = "listNotices.html";
	var urlToGetData = "notice/";

	var fieldsArray = ['user.fantasyName', ['modality.acronyms','modality.description'], 
		'companyType.description','number', 'object', 
		'publishingDate', 'tradingDate', 'closingDate','edital', 'edit', 'delete'];	
	
	loadPage(urlToLoad, function(){
		loadDeleteConfirmModal(urlToGetData+'delete', deleteEntity, beforeDeleteNotice);
		genericList(urlToGetData, "notice", "noticeId", fieldsArray, editDeleteButton, null, eachNoticeRowListing);
	});	
}

function eachNoticeRowListing(data){
	data['tradingDate'] = convertSqlDateToFormDate(data.tradingDate);
	data['publishingDate'] = convertSqlDateToFormDate(data.publishingDate);
	data['closingDate'] = convertSqlDateToFormDate(data.closingDate);
	data['edital'] = '<a id="openFile" entity-data="'+ data.noticeId + '" href="#" onclick=downloadNotice(this);> Abrir Edital </a>';
}

function downloadNotice(input){
	var noticeId = $(input).attr('entity-data');
	var notice = $('#tr'+noticeId).data('key');
	var fileName = notice.fileName;
	var urlToGetData = "file/download/" + noticeId + "/" + fileName;

	downloadFile(urlToGetData);
}

function listCategories(){	
	var urlToLoad = "listCategories.html";
	var urlToGetData = "category/";
	
	loadPage(urlToLoad, function(){
		loadDeleteConfirmModal(urlToGetData+'delete', deleteEntity, beforeDeleteCategory);
		genericList(urlToGetData, "category", "categoryId", ['description', 'edit', 'delete'], editDeleteButton);
	});
}

function listModalities(){
	var urlToLoad = "listModalities.html";
	var urlToGetData = "modality/";	
	loadPage(urlToLoad, function(){
		loadDeleteConfirmModal(urlToGetData+'delete', deleteEntity, beforeDeleteModality);
		genericList(urlToGetData, "modality", "modalityId", ['acronyms', 'description', 'edit', 'delete'], editDeleteButton);
	});	
}

function listCompanyTypes(){
	var urlToLoad = "listCompanyTypes.html";
	var urlToGetData = "companyType/";
	loadPage(urlToLoad, function(){
		loadDeleteConfirmModal(urlToGetData+'delete', deleteEntity, beforeDeleteCompanyType);
		genericList(urlToGetData, "companyType", "companyTypeId", ['acronyms', 'description', 'edit', 'delete'], editDeleteButton);
	});	
}

function editDeleteButton(data, dataTableId, entityId){
	data['edit'] = '<input type="image" entity-data="'+data[entityId]+'" onclick="edit'+dataTableId+'(this);" src="assets/css/images/bt-editar.png"/>';
	data['delete'] = '<input type="image" entity-data="'+data[entityId]+'" data-toggle="modal" data-target="#confirmAction" src="assets/css/images/bt-deletar.png"/>';
}

function loadPage(htmlPageToLoad, loadedCallback){
	$('#contentDiv').load(htmlPageToLoad, loadedCallback);
}

function genericList(urlToGetData, dataTableId, entityId, dataTableFieldsArray, includeButtonsFunction, parameter, eachRowCallback){

	getEntity(urlToGetData, function(json)
			{		
		        $.each(json, function(i, data) {        	
		        	
		        	if(eachRowCallback){
		        		eachRowCallback(data);
		        	}
		        	
		        	if(includeButtonsFunction){
		        		includeButtonsFunction(data, dataTableId, entityId);
		        	}

					var row = '<tr id="tr'+data[entityId]+'">' +
						getTableData(data, dataTableFieldsArray) +
			  		  	'</tr>';
			        	
		        	$('#'+dataTableId).find('tbody:last').append(row);
		        	$('#tr'+data[entityId]).data('key',data);
		        });
			},{parameter});			
}