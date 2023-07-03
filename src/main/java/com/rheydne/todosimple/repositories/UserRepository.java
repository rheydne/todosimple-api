package com.rheydne.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rheydne.todosimple.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

// Código utilizado para fazer a comunicação do banco de dados com os models.

// Basicamente, a classe user herda todas as funcionalidades mais basicas do JpaRepository
// como busca por id, ou por username. Métodos esses já construidos para uso.

// Não requer código devido a sua herança na qual já possui os principais métodos já implementados

// PADRAO >         public interface UserRepository extends JpaRepository<"Classe", "Tipo do ID">
