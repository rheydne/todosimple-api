package com.rheydne.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rheydne.todosimple.model.Task;
import com.rheydne.todosimple.model.User;
import com.rheydne.todosimple.repositories.TaskRepository;

@Service
public class TaskService {
    
    @Autowired
    private UserService userService; // Serviço para lidar com operações relacionadas a usuários

    @Autowired
    private TaskRepository taskRepository; // Repositório para acessar dados de tarefas

    public Task findById(Long id) {
        Optional<Task> task = this.taskRepository.findById(id); // Busca uma tarefa pelo ID no repositório
        return task.orElseThrow(() -> new RuntimeException(
            "Tarefa não encontrada! id: " + id + ", Tipo: " + Task.class.getName()
        )); // Retorna a tarefa encontrada ou lança uma exceção se não for encontrada
    }

    @Transactional
    public Task create(Task obj) {
        User user = this.userService.findById(obj.getUser().getId()); // Busca o usuário relacionado à tarefa pelo ID
        obj.setId(null); // Define o ID da tarefa como nulo para garantir a criação de um novo registro
        obj.setUser(user); // Define o usuário relacionado à tarefa
        obj = this.taskRepository.save(obj); // Salva a tarefa no banco de dados usando o repositório
        return obj; // Retorna a tarefa criada
    }

    @Transactional
    public Task update(Task obj) {
        Task newObj = findById(obj.getId()); // Busca a tarefa a ser atualizada pelo ID
        newObj.setDescription(obj.getDescription()); // Atualiza a descrição da tarefa com a nova descrição fornecida
        return this.taskRepository.save(newObj); // Salva as alterações na tarefa no banco de dados
    }

    public void delete(Long id) {
        findById(id); // Verifica se a tarefa existe antes de excluir
        try {
            this.taskRepository.deleteById(id); // Exclui a tarefa com o ID fornecido usando o repositório
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!"); // Lança uma exceção se houver entidades relacionadas à tarefa
        }
    }

}
