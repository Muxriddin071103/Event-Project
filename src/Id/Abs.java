package Id;

import java.util.UUID;

public class Abs {
    private final String id = UUID.randomUUID().toString();

    public String getId() {
        return id;
    }
}
