package Service;

import java.util.List;

import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Message;

public class MessageService {

    private MessageDAO messageDAO;
    private AccountDAO accountDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
        accountDAO = new AccountDAO();
    }

    public Message createMessage(Message message) {
        System.out.println("!");
        if(message.getMessage_text() != "" && message.getMessage_text().length() < 255 && accountDAO.userExists(message.getPosted_by())){
            return messageDAO.createMessage(message);
        }
        else{
            return null;
        }
    }

    public Message deleteMessage(int id) {
        Message deleted = messageDAO.getMessage(id);
        messageDAO.deleteMessage(id);
        return deleted;
    }

    public List<Message> getAllMessagesFromUser(int id) {
        return messageDAO.getAllMessagesFromUser(id);
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message getMessage(int id) {
        return messageDAO.getMessage(id);
    }

    public Message updateMessage(int id, Message message) {
        if(message.getMessage_text() != "" && message.getMessage_text().length() < 255 && messageDAO.getMessage(id) != null){
            System.out.println(message);
            messageDAO.updateMessage(id, message);
            return messageDAO.getMessage(id);
        }
        else{
            return null;
        }
    }
}
