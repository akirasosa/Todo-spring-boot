package myapps.todoapp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import myapps.MyApplication
import myapps.todoapp.domain.Todo

@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = MyApplication.class)
@ActiveProfiles("test")
class TodoServiceTest extends Specification {

    @Autowired
    TodoRepository todoRepository

    @Autowired
    TodoService todoService

    def "can delete all todos by ids"() {
        when:
        Todo todo1 = todoRepository.save(new Todo(null, "test1"))
        Todo todo2 = todoRepository.save(new Todo(null, "test2"))
        todoRepository.save(new Todo(null, "test3"))

        then:
        todoRepository.count() == 3

        when:
        todoService.deleteAll([todo1.getId(), todo2.getId()] as Set)

        then:
        todoRepository.count() == 1
    }

}

