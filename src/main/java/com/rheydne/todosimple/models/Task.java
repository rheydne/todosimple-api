package com.rheydne.todosimple.models;

import java.util.Objects;

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


@Entity
@Table(name = "task")
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

    public Task() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (! (obj instanceof Task)) 
            return false;
        Task other = (Task) obj;
        if (this.id == null)
            if (other.id != null)
                return false;
            else if (!this.id.equals(other.id))
                return false;
        return Objects.equals(this.id, other.id) && Objects.equals(this.user, other.user)
            && Objects.equals(this.description, other.description);
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (this.id == null) ? 0 : this.id.hashCode() );
        return result;
    }
}