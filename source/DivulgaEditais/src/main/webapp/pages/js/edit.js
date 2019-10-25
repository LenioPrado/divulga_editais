function editnotice(input){	
	var entityData = {
			'formId':'registerNotice', 
			'loadFunction': loadNotice,
			'submitInputId':'submitNotice',
			'url':'notice/edit',
			'pageTitle':'Edição de Edital',
			'successMessage':'Edital alterado com sucesso!',
			'select-multiple-id':'noticesCategories', 
			'select-multiple-entity-id': ['category','categoryId']
		};
	changeEntityPageSettings(input, entityData, function(notice){
		
		notice['companyType'] = { 'companyTypeId' : notice['companyType'] };
		notice['modality'] = { 'modalityId' : notice['modality'] };
		notice['user'] = { 'userId' : notice['user']['userId'] };
		notice['publishingDate'] = convertFormDateToSqlDate(notice['publishingDate']);
		
		var noticesCategories = notice['noticesCategories'];
		notice['noticesCategories'] = [];
		$.each(noticesCategories, function(i, value){
			notice['noticesCategories'].push({'category': {'categoryId' : value}});		
		});
		
		delete notice['edital'];	
	});
}

function editcategory(input){
	var entityData = {
			'formId':'registerCategory',
			'loadFunction': loadCategory,
			'submitInputId':'submitCategory',
			'url':'category/edit',
			'pageTitle':'Edição de Categoria',
			'successMessage':'Categoria alterada com sucesso!'
		};
	changeEntityPageSettings(input, entityData);
}

function editmodality(input){
	var entityData = {
			'formId':'registerModality',
			'loadFunction': loadModality,
			'submitInputId':'submitModality',
			'url':'modality/edit',
			'pageTitle':'Edição de Modalidade',
			'successMessage':'Modalidade alterada com sucesso!'
		};
	changeEntityPageSettings(input, entityData);
}

function editcompanyType(input){
	var entityData = {
			'formId':'registerCompanyType',
			'loadFunction': loadCompanyType,
			'submitInputId':'submitCompanyType',
			'url':'companyType/edit',
			'pageTitle':'Edição de Tipo de Companhia',
			'successMessage':'Tipo de Companhia alterada com sucesso!'
		};
	changeEntityPageSettings(input, entityData);
}

function changeEntityPageSettings(input, entityRelatedData, afterGetEntityCallback){
	var entityId = $(input).attr('entity-data');
	var entity = $('#tr'+entityId).data('key');
	
	entityRelatedData['loadFunction'](
			function(){				
				editEntity(entity, entityRelatedData, afterGetEntityCallback)
			}, 
			function(){
				$('#pageTitle').text(entityRelatedData['pageTitle']);
				$('#'+entityRelatedData['submitInputId']).val('Atualizar');
				
				$.each($(".notEditable"), function(i, input){
					$(input).hide();
				});	
				
				setFormData(entity);
				if(entityRelatedData['select-multiple-id']){
					setSelectMultipleValues(entity, entityRelatedData);
				}
			}
	);
}

function editEntity(oldEntity, entityRelatedData, afterGetEntityCallback){
	var entity = getFormData(entityRelatedData['formId']);
	
	$.each(oldEntity, function(field, value){
		if(!(field in entity)){
			entity[field] = value;
		}
	});
	
	delete entity['edit'];
	delete entity['delete'];
	
	if(afterGetEntityCallback){
		afterGetEntityCallback(entity);
	}
	
	$.ajax({
	   type: "post",
	   dataType: "json",
	   url: getServerUrl() + entityRelatedData['url'],
	   data: JSON.stringify( entity ),
	   processData: false,
	   contentType: 'application/json',
	   success: function(data){
		   showMessage(entityRelatedData['successMessage'],'success');
		   window.scrollTo(0, 0);
	   },
	   error: function(jqXHR, exception) {
			var msg = jqXHR.responseText;
			showMessage(msg,'error');
			window.scrollTo(0, 0);
	   }
	});	
}