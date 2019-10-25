function validateNotice(callback) {
	  $("form[name='registerNotice']").validate({
            rules: {
            	companyType: "required",
            	noticesCategories: "required",
            	modality: "required",
                number: "required",
                object: "required",
                tradingDate: "required",
                closingDate: "required",
                categories: "required",
                fileName: "required"
            },

            messages: {
            	companyType: "Selecione um tipo válido",
            	noticesCategories: "Selecione a(s) categoria(s)",
                modality: "Escolha uma modalidade válida",
                number: "Digite um número válido",
                object: "Digite uma descrição válida",
                tradingDate: "Selecione uma data válida",
                closingDate: "Selecione uma data válida",
                categories: "Selecione uma categoria válida",
                fileName: "Insira um arquivo válido",
            },
            errorClass: "registerError",
            validClass: "registerSuccess",

	    submitHandler: function(form) {
	    	callback(form); 
	    }
	  });
}

function validateCategory(callback){
	  $("form[name='registerCategory']").validate({
		  rules: {
			  description: "required",
		  },
		
		  messages: {
		      description: "Digite uma categoria Válida",
		  },
		  errorClass: "registerError",
		  validClass: "registerSuccess",
		
		  submitHandler: function(form) {
			  callback(form);					
		  }
	  });
}

function validateModality(callback){
	  $("form[name='registerModality']").validate({
        rules: {
        	acronyms: "required",
        	description: "required",
        },

        messages: {
          	acronyms: "Digite um Acronimo Válido",
            description: "Digite uma Descrição Válida",
        },
        errorClass: "registerError",
        validClass: "registerSuccess",

        submitHandler: function(form) {
        	callback(form);
        }
	  });
}

function validateCompanyType(callback){
	  $("form[name='registerCompanyType']").validate({
	      	rules: {
				acronyms: "required",
				description: "required",
	      	},
	
	      messages: {
	        	acronyms: "Digite um Acronimo Válido",
	          	description: "Digite uma Descrição Válida",
	      },
	      errorClass: "registerError",
	      validClass: "registerSuccess",
	
	      submitHandler: function(form) {
	    	  callback(form);
	      }
	  });
}

function validateLogin(callback){
    console.log("entrou no validationLogin");
	$("form[name='loginForm']").validate({
	    rules: {
	        userEmail: {
	            required: true,
	            email: true
	            },
	        userPassword: {
	            required: true,
	            minlength: 5
	        },
	    },
	    messages: {
	        userPassword: {
	            required: "Digite uma senha válida",
	            minlength: "Sua senha deve ter no mínimo 5 caracteres"
	        },
	        userEmail:  "Digite um email válido",
	    },
	    submitHandler: function(form) {
	    	callback(form);
	    },
	        errorClass: "registerError",
	        validClass: "registerSuccess"
	
	  });
}

function validateUserRegister(callback) {

	var optionalRules;
	
	var option = $("#type").val();
	if(option == "branch"){
		optionalRules = {
            rules: {
            	branch: "required"
            },
            messages: {
            	branch: "Digite um setor válido"
            }
		};
	}else{
		optionalRules = {
            rules: {
            	cnae: "required",
            	companyType: "required"
            },
            messages: {
            	cnae: "Digite um CNAE válido",
            	companyType: "Selecione uma categoria válida"
            }
		};
	}
	
	var generalRules = {
			rules: {
		    	socialName: "required",
		    	fantasyName: "required",
		        email: {
		            required: true,
		            email: true
		        },
		        password: {
		            required: true,
		            minlength: 5
		        },
		        cnpj: "required",
		        type: "required",
		        zipCode: "required",
		        address: "required",
		        number: "required",
		        city: "required",
		        state: "required",
		        phonePrimary: "required",
		        responsibleName: "required",
		        responsibleCpf: "required"
			},

            messages: {
            	socialName: "Digite uma razão social válida",
            	fantasyName: "Digite um nome fantasia válida",
                password: {
                    required: "Digite uma senha válida",
                    minlength: "Sua senha deve ter no mínimo 5 caracteres"
                },
                email:  "Digite um email válido",
                cnpj: "Digite um CPF válido",
                type: "Selecione um tipo válido",
                zipCode: "Digite um CEP válido",
                address: "Digite uma rua válida",
                number: "Digite um numero válido",
                city: "Digite uma cidade válida",
                state: "Digite um estado válido",
                phonePrimary: "Digite um telefone válido",
                responsibleName: "Digite um nome válido",
                responsibleCpf: "Digite um CPF válido"
            }
	};
	
	var rules = $.extend(generalRules['rules'], optionalRules['rules']);
	var messages = $.extend(generalRules['messages'], optionalRules['messages']);
	
  $("form[name='registerForm']").validate({
            rules,
            messages,
            errorClass: "registerError",
            validClass: "registerSuccess",

       submitHandler: function(form) {
    	   callback(form);
    }
  });
};