package spring.boot.api.nger.controller;

import spring.boot.api.nger.domain.UserActivity;
import spring.boot.api.nger.repository.UserActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-activities")
public class UserActivityController {

    @Autowired
    private UserActivityRepository userActivityRepository;

    @GetMapping
    public List<UserActivity> getAllUserActivities(@RequestParam(required = false) String department) {
        if (department != null && !department.isEmpty()) {
            return userActivityRepository.findByDepartment(department);
        }
        return userActivityRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserActivity> getUserActivityById(@PathVariable Long id) {
        Optional<UserActivity> activity = userActivityRepository.findById(id);
        return activity.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserActivity> createUserActivity(@RequestBody UserActivity userActivity) {
        UserActivity savedActivity = userActivityRepository.save(userActivity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedActivity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserActivity> updateUserActivity(@PathVariable Long id, @RequestBody UserActivity userActivity) {
        if (!userActivityRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userActivity.setId(id);
        UserActivity updatedActivity = userActivityRepository.save(userActivity);
        return ResponseEntity.ok(updatedActivity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserActivity(@PathVariable Long id) {
        if (!userActivityRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userActivityRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}