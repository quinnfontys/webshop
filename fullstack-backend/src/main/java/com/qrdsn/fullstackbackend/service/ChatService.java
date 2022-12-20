package com.qrdsn.fullstackbackend.service;

import com.qrdsn.fullstackbackend.model.Message;
import com.qrdsn.fullstackbackend.model.Status;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class ChatService {
    public Message parseString(Message message) {
        List<String> logicaLeeruitkomst = new ArrayList<>();
        boolean correct = true;
        logicaLeeruitkomst.add("Admin ✔");
        if (message.getMessage().equalsIgnoreCase("oranje")){
            logicaLeeruitkomst.add("Oranje sokken ✔");
        } else {
            logicaLeeruitkomst.add("Oranje sokken ✘");
            correct = false;
        }
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.DAY_OF_WEEK) == 2) { // 2 == maandag
            logicaLeeruitkomst.add("Maandag ✔");
        } else {
            logicaLeeruitkomst.add("Maandag ✘");
            correct = false;
        }

        if (correct){
            logicaLeeruitkomst.add("je mag iets doen");
        }

        StringBuilder builder = new StringBuilder();
        for (String logica : logicaLeeruitkomst ) {
            builder.append(logica + "\n");
        }

        return new Message("System", builder.toString(), Status.MESSAGE);
    }

    public Message loggedInAsAdmin(){
        return new Message("System", "Welke kleur zijn je sokken?", Status.MESSAGE);
    }
}