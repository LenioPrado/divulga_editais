function loadNotice(actionToExecute, loadedCallback){
	
	var hasToLoad = 3;
	
	$('#contentDiv').load("registerNotice.html", function(){
		
		$('#submitNotice').click(function(){
			validateNotice(function(form){
				actionToExecute();
			});
		});
		
		getEntity('modality', function(json)
		{		
			fillSelect(json, 'modality', 'modalityId',['acronyms','description']);
			hasToLoad--;
			if(hasToLoad === 0 && loadedCallback){
				loadedCallback();
			}
		});	
		
		getEntity('companyType', function(json)
		{		
			fillSelect(json, 'companyType', 'companyTypeId',['acronyms','description']);
			hasToLoad--;
			if(hasToLoad === 0 && loadedCallback){
				loadedCallback();
			}
		});	

		getEntity('category', function(json)
		{		
			fillSelect(json, 'noticesCategories', 'categoryId','description');
			hasToLoad--;
			if(hasToLoad === 0 && loadedCallback){
				loadedCallback();
			}
			$('.selectpicker').selectpicker({
				size: 4
			});
			$('.filter-option pull-left').text('Selecione');
		});	
	});	
}

function loadCategory(actionToExecute, loadedCallback){
	
	$('#contentDiv').load("registerCategory.html", function(){
		$('#submitCategory').click(function(){
			validateCategory(function(form){
				actionToExecute();
			});
		});
		
		if(loadedCallback){
			loadedCallback();
		}
	});
}

function loadModality(actionToExecute, loadedCallback){

	$('#contentDiv').load("registerModality.html", function(){
		$('#submitModality').click(function(){
			validateModality(function(form){
				actionToExecute();
			});
		});	
		
		if(loadedCallback){
			loadedCallback();
		}
	});
}

function loadCompanyType(actionToExecute, loadedCallback){
	
	$('#contentDiv').load("registerCompanyType.html", function(){
		$('#submitCompanyType').click(function(){
			validateCompanyType(function(form){
				actionToExecute();
			});
		});
		
		if(loadedCallback){
			loadedCallback();
		}
	});
}