package com.rheydne.todosimple.models;

import java.util.ArrayList;
import java.util.List;

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

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@AllArgsConstructor // Linhas necessarias para se usar o Lombok
@NoArgsConstructor  // Serve basicamente para substituir os contrutores, como também os getters e setters da classe
@Getter
@Setter
@EqualsAndHashCode
public class User {

    // Age junto com os groups, dos atributos
    // Serve para nao deixar os atributos nulos, vazios e fora do size na comunicação com o banco de dados
    public interface CreateUser{}

    public interface UpdateUser{}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Necessario para definir o ID como identity no banco de dados
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true) // Define algumas caracteristicas das colunas
    @NotNull(groups = CreateUser.class)
    @NotEmpty(groups = CreateUser.class)
    @Size(groups = CreateUser.class, min = 2, max = 100)
    private String username;

    @JsonProperty(access = Access.WRITE_ONLY) // Vai garantir que a senha podera ser acessada apenas para ser escrita (sem retorno de dados na api)
    @Column(name = "password", length = 60, nullable = false)
    @NotNull(groups = { CreateUser.class, UpdateUser.class } )
    @NotEmpty(groups = { CreateUser.class, UpdateUser.class } )
    @Size(groups = { CreateUser.class, UpdateUser.class } , min = 8, max = 60)
    private String password;

    @OneToMany(mappedBy = "user") // Um usuário pode ter várias tarefas
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Task> tasks = new ArrayList<Task>(); // Declara uma lista de taks para um usuario (Relacionamento entre as classes)
}
