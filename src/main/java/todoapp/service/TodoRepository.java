package todoapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import todoapp.domain.Todo;

import java.util.Collection;

public interface TodoRepository extends Repository<Todo, Long> {

    Page<Todo> findAll(Pageable pageable);

    int count();

    Todo save(Todo todo);

    void delete(Long id);

    @Modifying
    @Query(name = "Todo.deleteAllByIds")
    void deleteAllByIds(@Param("ids") Collection<Long> ids);
}
