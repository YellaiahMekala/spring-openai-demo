package com.springai.controller;

import com.springai.service.OrderSupportAISupportAssistantService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrderSupportAIAssistantController {

    private OrderSupportAISupportAssistantService aiAssistantService;

    public OrderSupportAIAssistantController(OrderSupportAISupportAssistantService aiAssistantService){
        this.aiAssistantService=aiAssistantService;
    }

    @GetMapping("/order-support")
    public String getOrderSupportResponse(@RequestParam String customerName,@RequestParam String orderId,@RequestParam String customerMessage){
        return  aiAssistantService.assistWithOrderSupport(customerName,orderId,customerMessage);
    }

    @GetMapping("/order-support")
    public String talkToOrderAISupport(@RequestParam String customerName,@RequestParam String orderId,@RequestParam String customerMessage){
        return  aiAssistantService.talkToAISupport(customerName,orderId,customerMessage);
    }

}
