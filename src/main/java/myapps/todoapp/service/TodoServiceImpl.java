package myapps.todoapp.service;

import myapps.todoapp.domain.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Component("todoService")
@Transactional
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public Page<Todo> list(int page, int size) {
        return todoRepository.findAll(new PageRequest(page, size));
    }

    @Override
    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public void delete(Long id) {
        todoRepository.delete(id);
    }

    @Override
    public void deleteAll(Collection<Long> ids) {
        todoRepository.deleteAllByIds(ids);
    }

}
