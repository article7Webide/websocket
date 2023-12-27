package article.WebIde.chat.controller;

import article.WebIde.chat.dto.ChatRoom;
import article.WebIde.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chatroom")
public class ChatRoomController {

    private final ChatService chatService;

    @GetMapping
    public String displayChatRoom(Model model) {
        model.addAttribute("list", chatService.getAllRooms());
        log.info("SHOW ALL ChatList {}", chatService.getAllRooms());

        return "roomlist";
    }

    @PostMapping
    public String createRoom(@RequestBody Map<String, String> requestBody, RedirectAttributes rttr) {
        String name = requestBody.get("name");
        ChatRoom room = chatService.createChatRoom(name);
        log.info("CREATE Chat Room {}", room);
        rttr.addFlashAttribute("roomName", room);

        return "redirect:/chatroom";
    }



    @GetMapping("/detail")
    public String getRoomDetail(Model model, String roomId) {
        log.info("roomId {}", roomId);
        model.addAttribute("room", chatService.getRoomById(roomId));

        return "chatroom";
    }
}