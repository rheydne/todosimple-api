package com.rheydne.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rheydne.todosimple.models.User;
import com.rheydne.todosimple.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired // Injeção de dependencias
    private UserRepository userRepository; // Repositório para acessar dados de usuários

    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id); // Busca um usuário pelo ID no repositório
        return user.orElseThrow(() -> new RuntimeException(
            "Usuário não encontrado! id: " + id + ", Tipo: " + User.class.getName()
        )); // Retorna o usuário encontrado ou lança uma exceção se não for encontrado
    }

    @Transactional // Caso ocorra qualquer erro do inicio ao fim desse metodo, isso dara rollback nas alteracoes feitas no BD 
    public User create(User obj) {
        obj.setId(null); // Define o ID do usuário como nulo para garantir a criação de um novo registro
        obj = this.userRepository.save(obj); // Salva o usuário no banco de dados usando o repositório
        return obj; // Retorna o usuário criado
    }

    @Transactional // Caso ocorra qualquer erro do inicio ao fim desse metodo, isso dara rollback nas alteracoes feitas no BD
    public User update(User obj) {
        User newObj = findById(obj.getId()); // Busca o usuário a ser atualizado pelo ID
        newObj.setPassword(obj.getPassword()); // Atualiza a senha do usuário com a nova senha fornecida
        return this.userRepository.save(newObj); // Salva as alterações no usuário no banco de dados
    }

    public void delete(Long id) {
        findById(id); // Verifica se o usuário existe antes de excluir
        try {
            this.userRepository.deleteById(id); // Exclui o usuário com o ID fornecido usando o repositório
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!"); // Lança uma exceção se houver entidades relacionadas ao usuário
        }
    }
}
