package com.rheydne.todosimple.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "user")
public class User {

    // Age junto com os groups, dos atributos
    // Serve para nao deixar os atributos nulos, vazios e fora do size na comunicação com o banco de dados
    public interface CreateUser{} // Interface usada como marcação para validação ao criar um usuário

    public interface UpdateUser{} // Interface usada como marcação para validação ao atualizar um usuário

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Necessário para definir o ID como identity no banco de dados
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true) // Define algumas características das colunas
    @NotNull(groups = CreateUser.class) // O campo username não pode ser nulo ao criar um usuário
    @NotEmpty(groups = CreateUser.class) // O campo username não pode ser vazio ao criar um usuário
    @Size(groups = CreateUser.class, min = 2, max = 100) // O tamanho do campo username deve estar entre 2 e 100 caracteres ao criar um usuário
    private String username;

    @JsonProperty(access = Access.WRITE_ONLY) // Vai garantir que a senha possa ser acessada apenas para ser escrita (sem retorno de dados na API)
    @Column(name = "password", length = 60, nullable = false)
    @NotNull(groups = { CreateUser.class, UpdateUser.class }) // O campo password não pode ser nulo ao criar ou atualizar um usuário
    @NotEmpty(groups = { CreateUser.class, UpdateUser.class }) // O campo password não pode ser vazio ao criar ou atualizar um usuário
    @Size(groups = { CreateUser.class, UpdateUser.class }, min = 8, max = 60) // O tamanho do campo password deve estar entre 8 e 60 caracteres ao criar ou atualizar um usuário
    private String password;

    @OneToMany(mappedBy = "user") // Um usuário pode ter várias tarefas
    private List<Task> tasks = new ArrayList<Task>(); // Declara uma lista de taks para um usuario (Relacionamento entre as classes)

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public User() {
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (! (obj instanceof User)) 
            return false;
        User other = (User) obj;
        if (this.id == null)
            if (other.id != null)
                return false;
            else if (!this.id.equals(other.id))
                return false;
        return Objects.equals(this.id, other.id) && Objects.equals(this.username, other.username)
            && Objects.equals(this.password, other.password);
        
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (this.id == null) ? 0 : this.id.hashCode() );
        return result;
    }
}
