package org.course.management.entity;import lombok.AllArgsConstructor;import lombok.Data;import lombok.NoArgsConstructor;@Data@NoArgsConstructor@AllArgsConstructorpublic class ResponseObject {    private String status, message;    private Object data;}