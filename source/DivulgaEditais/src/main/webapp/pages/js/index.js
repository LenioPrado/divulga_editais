window.onload = function() {
	assignEvents();
};

function assignEvents(){	
	$('#loginSubmit').click(function(){
		validateLogin(function(form){
			checkUser();
		});
	});
	
	$('#registerUser').click(function(){
		validateUserRegister(function(form){
			registerUser();
		});
	});	
}

function show(selected){
    var branch = document.getElementById("prefeitura");
    var cnae = document.getElementById("empresa");
    var option = document.getElementById("type").value;
    var show = document.getElementById(option);

    if(show == branch){
        show.style.display = "block";
        cnae.style.display = "none";
    } else{
        show.style.display = "block";
        branch.style.display = "none";
    }        
}

function registerUser()
{
	var user = getFormData('registerForm');
	
	console.log(user);

	$.ajax({
	   type: "post",
	   dataType: "json",
	   url: getServerUrl() + "user/create",
	   data: JSON.stringify( user ),
	   processData: true,
	   contentType: 'application/json',
	   success: function(data){
		   showIndexMessage('messagesRegister', 'Usuário cadastrado com sucesso!','success');
	   },
	   error: function(jqXHR, status, error) {
		   var msg = jqXHR.responseText;	
		   showIndexMessage('messagesRegister', msg,'error');
	   }
	});    	
};
  
function checkUser(){
	var data = getFormData('loginForm');
	var url = "user/login" + "/" + data['userEmail'] + "/" + data['userPassword'] 
	$.ajax({
		   type: "get",
		   dataType: "json",
		   url: getServerUrl() + url,
		   processData: true,
		   contentType: 'application/json',
		   success: function(data){
			   if(data['message']){
				   showIndexMessage('messagesLogin', data['message'],'error');
			   } else{
				   // Fazer algo com o nome do usuário!
				   hideMessage();
				   window.location.replace("mainUser.html");
			   }			   
		   },
		   error: function(jqXHR, exception) {
				if(jqXHR.responseText['message']){
					showIndexMessage('messagesLogin', data['message'],'error');
				} else {
					var msg = jqXHR.responseText;
					showIndexMessage('messagesLogin', msg,'error');
				}				
		   }
	});
}