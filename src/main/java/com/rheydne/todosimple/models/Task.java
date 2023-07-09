package com.rheydne.todosimple.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "task")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne // Define um relacionamento muitos-para-um com a entidade User (várias tarefas podem ser de um usuário)
    @JoinColumn(name = "user_id", nullable = false, updatable = false) // Define a coluna "user_id" como chave estrangeira para o relacionamento
    private User user; // Declara um usuario para uma task (Relacionamento entre as classes)

    @Column(name = "description", length = 255, nullable = false)
    @NotNull
    @NotBlank
    @Size(min = 1, max = 255)
    private String description;
}
