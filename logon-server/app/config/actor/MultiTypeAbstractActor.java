package config.actor;

import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.base.Strings;
import play.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * NOTE: Document with usage (JsonTypeInfo required.. Socket type text)
 *
 * @author Michael Ruf
 * @since 2018-03-26
 */
public abstract class MultiTypeAbstractActor extends UntypedAbstractActor {

    protected final ActorRef out;
    protected final ObjectMapper objectMapper;

    public MultiTypeAbstractActor(ActorRef out, ObjectMapper objectMapper) {
        this.out = out;
        this.objectMapper = objectMapper;
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (!(message instanceof String)) {
            throw new IllegalArgumentException("String object for message required!");
        }

        HashMap<String, String> jsonAsMap = objectMapper.readValue(
                (String) message,
                TypeFactory.defaultInstance().constructMapType(HashMap.class, String.class, String.class));
        String typeString = jsonAsMap.get("@class");

        if (Strings.isNullOrEmpty(typeString)) {
            throw new IllegalArgumentException("Message must contain a \"@class\" attribute!");
        }

        Map<Class<?>, Consumer<Object>> typeDelegationMap = onReceiveTypeDelegation();

        for (Map.Entry<Class<?>, Consumer<Object>> classConsumerEntry : typeDelegationMap.entrySet()) {
            if (classConsumerEntry.getKey().getName().equals(typeString)) {
                classConsumerEntry.getValue().accept(
                        objectMapper.readValue((String) message, classConsumerEntry.getKey()));
                return;
            }
        }

        throw new IllegalArgumentException("Object for given \"@class\" attribute does not exist!");
    }

    public abstract Map<Class<?>, Consumer<Object>> onReceiveTypeDelegation();

    public void tell(Object message) {
        tell(message, self());
    }

    public void tell(Object message, ActorRef sender) {
        tell(out, message, sender);
    }

    public void tell(ActorRef receiver, Object message, ActorRef sender) {
        try {
            receiver.tell(objectMapper.writeValueAsString(message), sender);
        } catch (JsonProcessingException e) {
            Logger.error("Could not serialize message", e);
        }
    }
}
