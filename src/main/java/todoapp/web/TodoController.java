package todoapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import todoapp.domain.Todo;
import todoapp.service.TodoService;

import java.util.Set;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @RequestMapping
    public Page<Todo> list() {
        return todoService.list();
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
