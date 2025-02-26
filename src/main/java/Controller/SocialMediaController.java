package Controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {

    AccountService accountService;
    MessageService messageService;
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {

        Javalin app = Javalin.create();
        app.post("/messages",               this::createMessageHandler);
        app.delete("/messages/{id}",        this::deleteMessageHandler);
        app.get("/accounts/{id}/messages",  this::getAllMessagesFromUserHandler);
        app.get("/messages",                this::getAllMessagesHandler);
        app.get("/messages/{id}",           this::getMessageHandler);
        app.patch("/messages/{id}",         this::updateMessageHandler);
        app.post("/login",                  this::loginHandler);
        app.post("/register",               this::registerHandler);
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     * @throws JsonProcessingException 
     * @throws JsonMappingException 
     */
    private void createMessageHandler(Context ctx) throws JsonMappingException, JsonProcessingException{
        ObjectMapper map = new ObjectMapper();
        Message message = map.readValue(ctx.body(), Message.class);
        Message added = messageService.createMessage(message);
        if(added != null){
            ctx.json(added);
            ctx.status(200);
        }
        else{
            ctx.status(400);
        }        
    }

    private void deleteMessageHandler(Context ctx){
        Message deleted = messageService.deleteMessage(Integer.parseInt(ctx.pathParam("id")));
        if(deleted != null){
            ctx.json(deleted);
            ctx.status(200);
        }
        else{
            ctx.status(200);
        }

    }

    private void getAllMessagesFromUserHandler(Context ctx){
        List<Message> list = messageService.getAllMessagesFromUser(Integer.parseInt(ctx.pathParam("id")));
        if(list != null){
            ctx.json(list);
            ctx.status(200);
        }
        else{
            ctx.status(200);
        }
    }

    private void getAllMessagesHandler(Context ctx){
        List<Message> list = messageService.getAllMessages();
        if(list != null){
            ctx.json(list);
            ctx.status(200);
        }
        else{
            ctx.status(200);
        }
    }

    private void getMessageHandler(Context ctx){
        Message message = messageService.getMessage(Integer.parseInt(ctx.pathParam("id")));
        if(message != null){
            ctx.json(message);
            ctx.status(200);
        }
        else{
            ctx.status(200);
        }
    }

    private void updateMessageHandler(Context ctx){
        Message updated = messageService.updateMessage(Integer.parseInt(ctx.pathParam("id")));
        if(updated != null){
            ctx.json(updated);
            ctx.status(200);
        }
        else{
            ctx.status(400);
        }
    }

    private void loginHandler(Context ctx) throws JsonMappingException, JsonProcessingException{
        ObjectMapper map = new ObjectMapper();
        Account user = map.readValue(ctx.body(), Account.class);
        Account match = accountService.login(user);
        if(match != null){
            ctx.json(match);
            ctx.status(200);
        }
        else{
            ctx.status(401);
        }
    }

    private void registerHandler(Context ctx) throws JsonMappingException, JsonProcessingException{
        ObjectMapper map = new ObjectMapper();
        Account user = map.readValue(ctx.body(), Account.class);
        Account success = accountService.register(user);
        if(success != null){
            ctx.json(success);
            ctx.status(200);
        }
        else{
            ctx.status(400);
        }

    }

}