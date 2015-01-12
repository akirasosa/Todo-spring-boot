package todoapp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification
import todoapp.TodoApplication
import todoapp.domain.Todo

@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = TodoApplication.class)
class TodoRepositorySpec extends Specification {
    @Autowired
    TodoRepository todoRepository

    @Transactional
    def "can delete all todos by ids"() {
        when:
        Todo todo1 = todoRepository.save(new Todo(null, "test1"))
        Todo todo2 = todoRepository.save(new Todo(null, "test2"))
        todoRepository.save(new Todo(null, "test3"))

        then:
        todoRepository.count() == 3

        when:
        todoRepository.deleteAllByIds([todo1.getId(), todo2.getId()] as Set) == null

        then:
        todoRepository.count() == 1
    }

}

