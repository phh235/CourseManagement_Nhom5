package org.course.management.api;import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.stereotype.Controller;import org.springframework.web.bind.annotation.ExceptionHandler;import org.springframework.web.bind.annotation.PostMapping;import org.springframework.web.bind.annotation.RequestParam;import org.springframework.web.multipart.MultipartFile;import java.io.IOException;import java.nio.file.Files;import java.nio.file.Path;import java.nio.file.Paths;import java.util.HashMap;import java.util.Map;@Controllerpublic class UploadFileTopicApi {    @PostMapping("/uploadFile/topic")    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {        Map<String, Object> result = new HashMap<>();        try {            // Đường dẫn tuyệt đối tới thư mục lưu trữ file            String uploadDir = "D://CourseManagement/src/main/resources/static/assets/img/topic/";            // Tạo thư mục nếu chưa tồn tại            Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();            Files.createDirectories(uploadPath);            String fileName = file.getOriginalFilename();            // Đường dẫn file đầy đủ            Path filePath = uploadPath.resolve(fileName).normalize();            // Lưu file vào đường dẫn            Files.copy(file.getInputStream(), filePath);            result.put("success", true);            result.put("status", "File uploaded successfully");            result.put("data", fileName);        } catch (IOException e) {            result.put("error", true);            result.put("status", "File upload failed: " + e.getMessage());            result.put("data", null);            e.printStackTrace();        } catch (Exception e) {            result.put("error", true);            result.put("status", "Unexpected error: " + e.getMessage());            result.put("data", null);            e.printStackTrace();        }        return ResponseEntity.ok(result);    }    @ExceptionHandler(Exception.class)    public ResponseEntity<String> handleException(Exception e) {        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request");    }}