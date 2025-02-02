package class101.foo.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    PostRepository postRepository;

    @RabbitListener(queues = "CREATE_POST_QUEUE")
    public void receiveMessage(String message) throws Exception {
        Post post = objectMapper.readValue(message, Post.class);
        postRepository.save(post);
    }
}
