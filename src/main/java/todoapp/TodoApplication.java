package todoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import todoapp.domain.Todo;
import todoapp.service.TodoRepository;

@SpringBootApplication
public class TodoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TodoApplication.class, args);
        TodoRepository repository = context.getBean(TodoRepository.class);

        repository.save(new Todo("Buy milk"));
        repository.save(new Todo("Play soccer"));
    }

}
