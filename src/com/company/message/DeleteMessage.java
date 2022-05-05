package com.company.message;

import com.company.queryExecute.QueryExecute;

import java.text.SimpleDateFormat;
import java.util.List;

public class DeleteMessage {

    public static void remover(List<Message> messagesToRemove){
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        if (messagesToRemove.isEmpty()) {
            System.out.println("You do not have any messages to remove");
        } else {
            for (Message message : messagesToRemove) {
                QueryExecute.executeQueryMessages("DELETE FROM public.messages WHERE \"Receiver\"='" + message.getReceiver() +
                        "' AND \"Tittle\"='" + message.getTittle() + "' AND \"Date\"='" + sdf1.format(message.getDate())
                        + "' AND \"Viewed\"=" + true + ";");
            }
            System.out.println("Messages was removed");
        }
    }
}
