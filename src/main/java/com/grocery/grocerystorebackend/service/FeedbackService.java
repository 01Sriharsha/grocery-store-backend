package com.grocery.grocerystorebackend.service;

import com.grocery.grocerystorebackend.entity.Customer;
import com.grocery.grocerystorebackend.entity.Feedback;
import com.grocery.grocerystorebackend.entity.Product;
import com.grocery.grocerystorebackend.repository.CustomerRepository;
import com.grocery.grocerystorebackend.repository.FeedbackRepository;
import com.grocery.grocerystorebackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    public Feedback createFeedback(Feedback feedback, Integer customerId, String productId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        feedback.setId(new Random().nextLong(9999,99999));
        feedback.setDate(LocalDate.now());
        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getAllFeedbacks(){
        return feedbackRepository.findAll();
    }

    public List<Feedback> getAllFeedbacksByCustomer(Integer customerId){
        return feedbackRepository.findByCustomerId(customerId);
    }

    public List<Feedback> getAllFeedbacksByProduct(String productId){
        return feedbackRepository.findByProductId(productId);
    }

    public Feedback getSingleFeedback(Long feedbackId){
        return feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
    }

    public Feedback updateFeedback(Long feedbackId , Feedback feedback){
        Feedback existingFeedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
        existingFeedback.setReply(feedback.getReply());
        existingFeedback.setDate(LocalDate.now());
        return feedbackRepository.save(existingFeedback);
    }

    public void deleteFeedback(Long feedbackId){
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
        if(feedback.getReply()!=null){
            throw new RuntimeException("Cannot perform action!! Feedback already got replied");
        }else{
            feedbackRepository.delete(feedback);
        }
    }
}
