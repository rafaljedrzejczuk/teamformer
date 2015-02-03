package pl.teamformer.mychat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.SessionContext;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import pl.teamformer.tools.DateFormatters;

@Named
@ApplicationScoped
public class MyChat {

        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @Getter
        private final List<String> messages;
        @Setter
        @Getter
        private volatile String message;

        private static final int SIZE = 100;

        @Inject
        private SessionContext sessionContext;
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public MyChat() {
                messages = new ArrayList();
                message = "";
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public synchronized void addMessage() {
                if (messages.size() >= SIZE)
                        messages.remove(messages.size() - 1);
                messages.add(0, "[" + DateFormatters.SDF_DATE.format(new Date())
                        + " at "+ DateFormatters.SDF_HOUR.format(new Date()) + "] " + getName() + ": " + message);
                message = "";
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        private String getName() {
                return sessionContext.getCallerPrincipal().getName();
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
}
