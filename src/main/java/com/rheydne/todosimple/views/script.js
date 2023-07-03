const url = "http://localhost:8080/task/user/1"; // URL da API

function hideLoader() {
    document.getElementById("loading").style.display = "none"; // Oculta o spinner de carregamento
    document.getElementById("loading-value").style.display = "none"; // Oculta o texto "Carregando..."
}

function show(tasks) {

    let tab = `<thead>
        <th scope="col">#</th>
        <th scope="col">Description</th>
        <th scope="col">Username</th>
        <th scope="col">User Id</th>
    </thead>`; // Cabeçalho da tabela

    for (let task of tasks) {
        tab += `<tr>
            <td scope="row">${task.id}</td> <!-- Exibe o ID da tarefa -->
            <td scope="row">${task.description}</td> <!-- Exibe a descrição da tarefa -->
            <td scope="row">${task.user.username}</td> <!-- Exibe o nome de usuário associado à tarefa -->
            <td scope="row">${task.user.id}</td> <!-- Exibe o ID do usuário associado à tarefa -->
        </tr>`;
    }

    document.getElementById("tasks").innerHTML = tab; // Preenche a tabela com as linhas de tarefas
}

async function getAPI(url) {
    
    const response = await fetch(url, {method: "GET"}); // Faz uma requisição GET para a URL da API

    var data = await response.json(); // Converte a resposta para JSON
    if(response)
        hideLoader(); // Oculta o spinner de carregamento
    show(data); // Exibe as tarefas na tabela
}

getAPI(url); // Chama a função getAPI para obter os dados da API e exibi-los na tabela