package project.psa.QLDangVien.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.model.ChatRequest;
import project.psa.QLDangVien.model.ChatResponse;
import project.psa.QLDangVien.service.auth.GeminiService;

@RestController
@RequestMapping(constant.API.PREFIX_AUTH+"/chat")
public class chatController {

    @Autowired
    private GeminiService geminiService;


    @PostMapping("/gemini")
    public ChatResponse chatGemini(@RequestBody ChatRequest request) {
        String reply = geminiService.getGeminiResponse(request.getMessage());
        return new ChatResponse(reply);
    }
}
