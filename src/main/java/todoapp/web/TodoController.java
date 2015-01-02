package todoapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import todoapp.domain.Todo;
import todoapp.service.TodoService;

import javax.servlet.http.HttpServletResponse;
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
    public Todo create(@RequestParam("title") String title) {
        Todo todo = new Todo(title);
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
