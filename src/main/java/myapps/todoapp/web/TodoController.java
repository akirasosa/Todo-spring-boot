package myapps.todoapp.web;

import myapps.todoapp.domain.Todo;
import myapps.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private static final Integer DEFAULT_PAGE_SIZE = 5;

    @Autowired
    private TodoService todoService;

    @RequestMapping
    public Page<Todo> list(@RequestParam(value = "page") Optional<Integer> page,
                           @RequestParam(value = "size") Optional<Integer> size,
                           HttpServletResponse response) {

        Page<Todo> todos = todoService.list(page.orElse(1) - 1, size.orElse(DEFAULT_PAGE_SIZE));
        response.setIntHeader("X-Total-Items", (int) todos.getTotalElements());
        response.setIntHeader("X-Items-Per-Page", todos.getSize());

        return todos;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Todo create(@RequestBody @Valid Todo todo) {
        return todoService.save(todo);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        todoService.delete(id);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAll(@RequestParam("ids") Set<Long> ids) {
        todoService.deleteAll(ids);
    }

}
