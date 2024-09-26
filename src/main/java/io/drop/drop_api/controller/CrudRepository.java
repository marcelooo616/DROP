package io.drop.drop_api.controller;

import io.drop.drop_api.models.entities.product.Products;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CrudRepository<T, ID> {

    // Método para salvar uma entidade
    T save(T entity);

    // Método para buscar uma entidade pelo ID

    T findById(ID id);

    // Método para buscar todas as entidades
    List<T> findAll();

    // Método para atualizar uma entidade
    void update(T entity);

    // Método para deletar uma entidade pelo ID
    void deleteById(ID id);
}

