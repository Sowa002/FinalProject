package org.binaracademy.finalproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.binaracademy.finalproject.model.Course;
import org.binaracademy.finalproject.model.Subject;
import org.binaracademy.finalproject.model.request.UpdateCourseRequest;
import org.binaracademy.finalproject.model.request.UpdateSubjectRequest;
import org.binaracademy.finalproject.model.response.ResponseController;
import org.binaracademy.finalproject.service.CourseService;
import org.binaracademy.finalproject.service.OrderService;
import org.binaracademy.finalproject.service.SubjectService;
import org.binaracademy.finalproject.service.UserService;
import org.binaracademy.finalproject.service.implement.CategoryAndRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private CategoryAndRoleService service;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/course/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addCourse(@RequestBody Course course){
        try {
            return ResponseController.statusResponse(HttpStatus.OK,
                    "Adding new course successful!", courseService.addNewCourse(course));
        } catch (Exception e) {
            return ResponseController.internalServerError(e.getMessage());
        }
    }

    @DeleteMapping(value = "/course/delete/{codeCourse}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteCourse(@PathVariable String codeCourse){
        try {
            courseService.deleteCourseByCode(codeCourse);
            return ResponseController.statusResponse(HttpStatus.OK,
                    "Deleting course successful!", null);
        } catch (RuntimeException runtimeException) {
            return ResponseController.statusResponse(HttpStatus.NOT_FOUND,
                    runtimeException.getMessage(), null);
        } catch (Exception e) {
            return ResponseController.internalServerError(e.getMessage());
        }
    }

    @PutMapping(value = "/course/update/{code}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateCourse(@PathVariable String code, @RequestBody UpdateCourseRequest course){
        try {
            return ResponseController.statusResponse(HttpStatus.OK,
                    "Update course successful!", courseService.updateCourse(course, code));
        } catch (RuntimeException runtimeException) {
            return ResponseController.statusResponse(HttpStatus.NOT_FOUND,
                    runtimeException.getMessage(), null);
        } catch (Exception e) {
            return ResponseController.internalServerError(e.getMessage());
        }
    }

    @PostMapping(value = "/subject/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addSubject(@RequestBody Subject subject){
        try {
            return ResponseController.statusResponse(HttpStatus.OK,
                    "Add new subject successful", subjectService.addSubject(subject));
        } catch (Exception e) {
            return ResponseController.internalServerError(e.getMessage());
        }
    }

    @PutMapping(value = "/subject/update/{code}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateSubject(@PathVariable String code, @RequestBody UpdateSubjectRequest subject){
        try {
            return ResponseController.statusResponse(HttpStatus.OK,
                    "Update subject successful!", subjectService.updateSubject(subject, code));
        } catch (RuntimeException runtimeException) {
            return ResponseController.statusResponse(HttpStatus.NOT_FOUND,
                    runtimeException.getMessage(), null);
        } catch (Exception e) {
            return ResponseController.internalServerError(e.getMessage());
        }
    }

    @DeleteMapping(value = "/subject/delete/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteSubject(@PathVariable String code){
        try {
            subjectService.deleteSubjectByCode(code);
            return ResponseController.statusResponse(HttpStatus.OK,
                    "Deleting subject successful!", null);
        } catch (RuntimeException runtimeException) {
            return ResponseController.statusResponse(HttpStatus.NOT_FOUND,
                    runtimeException.getMessage(), null);
        } catch (Exception e) {
            return ResponseController.internalServerError(e.getMessage());
        }
    }

    @GetMapping(value = "/category/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCategory() {
        try {
            return ResponseController.statusResponse(HttpStatus.OK,
                    "Success get all category", service.getCategory());
        } catch (Exception e) {
            return ResponseController.internalServerError(e.getMessage());
        }
    }

    @GetMapping(value = "/role/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getRoles() {
        try {
            return ResponseController.statusResponse(HttpStatus.OK,
                    "Success get all roles", service.getRoles());
        } catch (Exception e) {
            return ResponseController.internalServerError(e.getMessage());
        }
    }

    @GetMapping(value = "/user/getAllUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllCourse(){
        try {
            return ResponseController.statusResponse(HttpStatus.OK,
                    "Success get all users", userService.getAllUser());
        } catch (Exception e) {
            return ResponseController.internalServerError(e.getMessage());
        }
    }

    @GetMapping(value = "/course/getAllCourse", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get course yang ada ID-nya")
    public ResponseEntity<Object> getCourseAdmin() {
        try {
            return ResponseController.statusResponse(HttpStatus.OK,
                    "Success get all roles", courseService.getAllCourseAdmin());
        } catch (Exception e) {
            return ResponseController.internalServerError(e.getMessage());
        }
    }

    @GetMapping(value = "/subject/getAllSubject", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllSubject(){
        try {
            return ResponseController.statusResponse(HttpStatus.OK,
                    "Success get all subject", subjectService.getAllSubject());
        } catch (Exception e) {
            return ResponseController.internalServerError(e.getMessage());
        }
    }

    @GetMapping(value = "/subject/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllSubject(@RequestParam String code){
        try {
            return ResponseController.statusResponse(HttpStatus.OK,
                    "Success getting subject", subjectService.getSubject(code));
        } catch (RuntimeException rte) {
            return ResponseController.statusResponse(HttpStatus.NOT_FOUND,
                    rte.getMessage(), null);
        } catch (Exception e) {
            return ResponseController.internalServerError(e.getMessage());
        }
    }

    @GetMapping(value = "/order/getAllOrderTransactions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllOrder() {
        try {
            return ResponseController.statusResponse(HttpStatus.OK,
                    "Success getting all order transactions", orderService.getAllOrder());
        } catch (RuntimeException rte) {
            return ResponseController.statusResponse(HttpStatus.NOT_FOUND,
                    rte.getMessage(), null);
        } catch (Exception e) {
            return ResponseController.internalServerError(e.getMessage());
        }
    }

    @DeleteMapping(value = "/user/deleteUserForAdmin/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteUserForAdmin(@PathVariable String username) {
        try {
            userService.deleteUserForAdmin(username);
            return ResponseController.statusResponse(HttpStatus.OK,
                    "Deleting user successful!", null);
        } catch (RuntimeException runtimeException) {
            return ResponseController.statusResponse(HttpStatus.NOT_FOUND,
                    runtimeException.getMessage(), null);
        } catch (Exception e) {
            return ResponseController.internalServerError(e.getMessage());
        }
    }
}