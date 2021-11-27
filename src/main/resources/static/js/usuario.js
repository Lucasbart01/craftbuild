function logar(){
	
    var objeto = {
        "email" : document.getElementById("txtemail").value,
        "senha" : document.getElementById("txtsenha").value
    }

    var cabecalho = {
        method: "POST",
        body: JSON.stringify(objeto),
        headers:{
            "Content-type":"application/json"
        }
    }

    fetch("http://localhost:8080/login/entry", cabecalho)
        .then(res => res.json())
        .then(res => {
            localStorage.setItem("logado",JSON.stringify(res));
            window.location="user";

        })
        .catch(err => {
			console.log(err);
            window.alert("Erro ao logar");
        });

   
}

function cadastrar(){
	    var objeto = {
		
        "nome" : document.getElementById("nome").value,
        "email" : document.getElementById("email").value,
        "senha" : document.getElementById("senha").value,
        "motivo" : document.getElementById("motivo").value
    }

    var cabecalho = {
        method: "POST",
        body: JSON.stringify(objeto),
        headers:{
            "Content-type":"application/json"
        }
    }

    fetch("http://localhost:8080/add", cabecalho)
        .then(res => res.json())
        .then(res => {
            localStorage.setItem("cadastrar",JSON.stringify(res));
            window.location="user";

        })
        .catch(err => {
			console.log(err);
            window.alert("Erro ao cadastrar");
        });
	
	
}

function atualizar(){
	
	    var objeto = {
		
        "nome" : document.getElementById("nome").value,
        "email" : document.getElementById("email").value,
        "senha" : document.getElementById("senha").value,
        "motivo" : document.getElementById("motivo").value
    }

    var cabecalho = {
        method: "POST",
        body: JSON.stringify(objeto),
        headers:{
            "Content-type":"application/json"
        }
    }

    fetch("http://localhost:8080/update/{id}", cabecalho)
        .then(res => res.json())
        .then(res => {
            localStorage.setItem("cadastrar",JSON.stringify(res));
            //window.location="user";

        })
        .catch(err => {
			console.log(err);
            window.alert("Erro ao cadastrar");
        });
	
	
}