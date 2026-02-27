package lk.ijse.taskservice.controller;


import lk.ijse.taskservice.model.ResponseDTO;
import lk.ijse.taskservice.model.Task;
import lk.ijse.taskservice.repo.TaskRepository;
import lk.ijse.taskservice.service.UserClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    private final TaskRepository taskRepository;
  private final UserClient userClient;
    public TaskController(TaskRepository taskRepository, UserClient userClient) {
        this.taskRepository = taskRepository;
        this.userClient = userClient;
    }


    @PostMapping("/saveTask")
    public ResponseEntity<ResponseDTO> saveTask(@RequestBody Task task){


        boolean check= userClient.checkUserExists(task.getEmail());
        System.out.println("hsdfghgdsjfhgkdjslphgkfpdh     "+check);
        if(userClient.checkUserExists(task.getEmail())){

            Task savedTask = taskRepository.save(task);

            return ResponseEntity
                    .status(201)
                    .body(new ResponseDTO("Task Saved Successfully", savedTask, 201));
        }

        return ResponseEntity
                .status(404)
                .body(new ResponseDTO("User Not Found", null, 404));
    }
    @GetMapping("/getAllTask")
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }
}
