package com.grocery.grocerystorebackend.controller;

import com.grocery.grocerystorebackend.entity.Feedback;
import com.grocery.grocerystorebackend.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/customers/{customerId}/products/{productId}/feedbacks")
    public ResponseEntity<?> createNewFeedback(
            @RequestBody Feedback feedback,
            @PathVariable Integer customerId,
            @PathVariable String productId
    ) {
        return new ResponseEntity<>(
                feedbackService.createFeedback(feedback, customerId, productId),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/feedbacks")
    public ResponseEntity<?> getAllFeedbacks() {
        return new ResponseEntity<>(feedbackService.getAllFeedbacks(), HttpStatus.OK);
    }

    @GetMapping("/customers/{customerId}/feedbacks")
    public ResponseEntity<?> getAllFeedbacksByCustomer(@PathVariable Integer customerId) {
        return new ResponseEntity<>(feedbackService.getAllFeedbacksByCustomer(customerId), HttpStatus.OK);
    }

    @GetMapping("/products/{productId}/feedbacks")
    public ResponseEntity<?> getAllFeedbacksByProduct(@PathVariable String productId) {
        return new ResponseEntity<>(feedbackService.getAllFeedbacksByProduct(productId), HttpStatus.OK);
    }

    @GetMapping("/feedbacks/{feedbackId}")
    public ResponseEntity<?> getSingleFeedback(@PathVariable Long feedbackId) {
        return new ResponseEntity<>(feedbackService.getSingleFeedback(feedbackId), HttpStatus.OK);
    }

    @PutMapping("/feedbacks/{feedbackId}")
    public ResponseEntity<?> updateFeedback(@PathVariable Long feedbackId, @RequestBody Feedback feedback) {
        return new ResponseEntity<>(feedbackService.updateFeedback(feedbackId, feedback), HttpStatus.CREATED);
    }

    @DeleteMapping("/feedbacks/{feedbackId}")
    public ResponseEntity<?> deleteFeedback(@PathVariable Long feedbackId) {
        feedbackService.deleteFeedback(feedbackId);
        return new ResponseEntity<>("Feedback removed successfully!!", HttpStatus.OK);
    }

}
