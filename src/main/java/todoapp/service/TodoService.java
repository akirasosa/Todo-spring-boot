package todoapp.service;

import org.springframework.data.domain.Page;
import todoapp.domain.Todo;

import java.util.Collection;

public interface TodoService {

    public Page<Todo> list(int page, int size);

    Todo save(Todo todo);

    void delete(Long id);

    void deleteAll(Collection<Long> ids);

}
